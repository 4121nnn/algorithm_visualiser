function runBFS() {


    const gridSize = pathFindResult.matrix.length;


    function generateGrid(matrix) {
    const main = document.getElementById('display-container');
        main.innerHtml = '';

        const grid = document.createElement('div')
        grid.className = "grid"
        grid.id = "grid"

        const startBtn =  document.createElement('button');
        startBtn.className="btn mb-2 pb-2 pt-2 pr-3 pl-3 fw-bold text-white";
        startBtn.id="start-btn";
        startBtn.textContent="Start"
        startBtn.addEventListener('click', () => handleStartButton());
        startBtn.style.display="block"

        const updateBtn =  document.createElement('button');
        updateBtn.setAttribute('hx-get', '/path-find?name=' + pathFindResult.type);
        updateBtn.setAttribute('hx-target', '#swap-container');
        updateBtn.setAttribute('hx-swap', 'outerHTML');
        updateBtn.className="btn mb-2 pb-2 pt-2 pr-3 pl-3 fw-bold text-white";
        updateBtn.id="update-btn";
        updateBtn.textContent="Update"
        updateBtn.addEventListener('click', () => handleStartButton());
        updateBtn.style.display="none"

        main.appendChild(startBtn)
        main.appendChild(updateBtn)
        main.appendChild(grid);
        grid.style.gridTemplateColumns = `repeat(${gridSize}, 1fr)`;

        for (let i = 0; i < gridSize; i++) {
            for (let j = 0; j < matrix[i].length; j++) {
                const cell = document.createElement('div');
                cell.className = 'cell';
                if(i == 0 && j == 0){
                    cell.className ='start'
                }
                if(i == gridSize - 1 && j == matrix[0].length-1){
                    cell.className ='end'
                }
                cell.dataset.index = i * gridSize + j;

                if (matrix[i][j] == 1) {
                    cell.classList.add('wall');
                }

                grid.appendChild(cell);
            }
        }
    }

    function animateMoves(result, delay) {
        const highlightCell = (move, className, index, revertDelay) => {
            setTimeout(() => {
                const cellIndex = move[0] * gridSize + move[1];
                const cell = grid.querySelector(`.cell[data-index='${cellIndex}']`);
                if (cell) {
                    cell.classList.add('cur');

                    setTimeout(() => {
                        cell.classList.remove('cur');
                        cell.classList.add(className);
                    }, revertDelay);
                }
            }, index * delay);
        };

        const revertDelay = 0;

        result.moves.forEach((move, index) => {
            highlightCell(move, 'highlight', index, revertDelay);
        });

        if (result.shortestPath) {
            result.shortestPath.forEach((move, index) => {
                highlightCell(move, 'path-style', result.moves.length + index, revertDelay);
            });
        }
    }


    function handleStartButton(){
        const startBtn =  document.getElementById('start-btn');
        const updateBtn =  document.getElementById('update-btn');
        if(started){
            updateBtn.style.display = 'none';
            startBtn.style.display = 'block';
            started = false;
        }else{
            started = true;
            startBtn.style.display = 'none';
            updateBtn.style.display = 'block';
            animateMoves(pathFindResult, delay)
        }

    }
    let started = false;

    generateGrid(pathFindResult.matrix)

    const startColors = ['#11FD00', '#0BA400'];
    const endColors = ['#FD0059', '#A11800'];

    let currentColorIndex = 0;

    function changeColor() {
        const startSignals = document.querySelectorAll('.start');
        const endSignals = document.querySelectorAll('.end');

        startSignals.forEach(signal => {
            signal.style.backgroundColor = startColors[currentColorIndex];
        });

        endSignals.forEach(signal => {
            signal.style.backgroundColor = endColors[currentColorIndex];
        });

        currentColorIndex = (currentColorIndex + 1) % startColors.length;
    }
    setInterval(changeColor, 300);

}

runBFS();

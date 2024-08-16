function runBFS() {
    const main = document.getElementById('display-container');
    main.innerHtml = '';

    const startBtn = document.createElement('button')
    startBtn.id = "start-btn"
    startBtn.className = "btn mb-2 pb-0 pt-0 pr-1 pl-1 fw-bold text-white"
    startBtn.textContent = "Start"

    const grid = document.createElement('div')
    grid.className = "grid"
    grid.id = "grid"

    main.appendChild(startBtn)
    main.appendChild(grid);

    const gridSize = pathFindResults[0].matrix.length;

    function draw() {
        pathFindResults.forEach(result => {
            generateGrid(result.matrix);
        });
    }

    function generateGrid(matrix) {
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

    draw();

    function startAnimation() {
        pathFindResults.forEach(result => {
            animateMoves(result, 20);
        });
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
    document.getElementById('start-btn').addEventListener('click', () => startAnimation());

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

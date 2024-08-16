function runBFS() {
    const main = document.getElementById('display-container');
    main.innerHtml = '';

    const startBtn = document.createElement('button')
    startBtn.id = "start-btn"
    startBtn.className = "btn mb-2 p-2"

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

}

runBFS();



function Sort(){
    const main = document.getElementById('display-container');
    main.innerHTML = '';

    const startBtn = document.createElement('button')
    startBtn.id = "start-btn"
    startBtn.className = "btn mb-2 pb-0 pt-0 pr-1 pl-1 fw-bold text-white d-inline mr-2"
    startBtn.textContent = "Start"
    const ps = document.createElement('p')
    ps.className = "text-muted d-inline small"
    ps.textContent = "Don't restart until animation ends."

    const container = document.createElement('div');
    container.id = "sort-container"
    container.className = 'row'


    main.appendChild(startBtn);
    main.appendChild(ps)
    main.appendChild(container)


function setupBars(results){
    results.forEach(result => {
        createBars( container,
                    result.nonSortedArray,
                    result.sortType,
                    getName(result.sortType),
                    result.timing,
                    null
        );
    })
}

function createBars(container, array, name, title, timing, info) {

    const colDiv = document.createElement('div');
    colDiv.className = 'mb-2 col-xl-6 col-sm-6 grid-margin stretch-card';
    colDiv.id = name + '-card';

    const cardDiv = document.createElement('div');
    cardDiv.className = 'rounded-3 card ';

    const cardBodyDiv = document.createElement('div');
    cardBodyDiv.className = '';

    const cardTitleDiv = document.createElement('div');
    cardTitleDiv.className = 'card-header pr-2 pl-2 pt-0 pb-0 d-flex align-items-center align-self-start';

    const titleP = document.createElement('p');
    titleP.className = 'mb-0 text-muted fw-bold fs-1';
    titleP.textContent = title;

    const timingP = document.createElement('p');
    timingP.className = 'text-success ml-2 mb-0 font-weight-medium';
    timingP.id = name + 'timing';
    timingP.textContent = timing + ' ms';
    timingP.style.textTransform = 'none';
    timingP.style.display = 'none';

    const infoP = document.createElement('p');
    infoP.className = 'text-muted small ml-2 mb-0 font-weight-normal ';
    infoP.textContent = '(without animation)';
    infoP.id = name + 'info';
    infoP.style.textTransform = 'none';
    infoP.style.display = 'none';

    cardTitleDiv.appendChild(titleP);
    cardTitleDiv.appendChild(timingP);
    cardTitleDiv.appendChild(infoP);

    const innerContainer = document.createElement('div');
    innerContainer.className="card-body pl-1 pr-1 pt-1 pb-0"
    array.forEach(value => {
        const bar = document.createElement('div');
        bar.className = 'bar ' + name;
        bar.style.height = value + 'px';
        innerContainer.appendChild(bar);
    });

    cardBodyDiv.appendChild(cardTitleDiv);
    cardBodyDiv.appendChild(innerContainer);

    cardDiv.appendChild(cardBodyDiv);

    colDiv.appendChild(cardDiv);

    container.appendChild(colDiv);
    const checkbox = document.getElementById(name);

    if(!checkbox.checked){
        colDiv.style.display = 'none'
    }

}

function startComparison(results){
    results.forEach((result, index) => {
        const checkbox = document.getElementById(result.sortType);
        if(checkbox.checked){
            const bars = document.getElementsByClassName(result.sortType);
            startAnimation(result.moves, bars, result.sortType);
        }
    })
}

function startAnimation(moves, bars, sortType) {

    moves.forEach((move, index) => {
        setTimeout(() => {
            bars[move.index].style.backgroundColor = 'red';
            bars[move.index].style.height = move.value + 'px';
             setTimeout(() => {
                bars[move.index].style.backgroundColor = '';

                if (index === moves.length - 1) {
                    document.getElementById(sortType + 'timing').style.display = 'block';
                    document.getElementById(sortType + 'info').style.display = 'block';
                }

             }, delay);
        }, index * delay); // Delay between steps
    });
}

function handleStartButtonCLick(){
    const button = document.getElementById('start-btn');

    if(!algorithmStarted){
         startComparison(results)
         algorithmStarted = true;
    }else{
        setupBars(results);
        button.textContent = 'Start';
        algorithmStarted = false;
    }
}

function getName(type){
    switch(type) {
        case "BUBBLE_SORT":
            return "Bubble Sort";
            break;
        case "SELECTION_SORT":
            return "Selection Sort";
            break;
        case "SELECTION_SORT":
            return "Selection Sort";
            break;
        case "INSERTION_SORT":
            return "Insertion Sort";
            break;
        case "MERGE_SORT":
            return "Merge Sort";
            break;
        case "QUICK_SORT":
            return "Quick Sort";
            break;
        case "RADIX_SORT":
            return "Radix Sort";
            break;
        case "HEAP_SORT":
            return "Heap Sort";
            break;
        default:
            return "";
    }
}




setupBars(results);

document.getElementById('start-btn').addEventListener('click', () => startComparison(results));

}
Sort()











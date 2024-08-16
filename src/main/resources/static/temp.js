delay = 20

function Sort(){

function setupBars(results){
    const main = document.getElementById('display-container');
    main.innerHTML = '';
    const container = document.createElement('div');
    container.className = "row"
    const startBtn = document.createElement('button');
    container.id = "sort-container"
    startBtn.id = "start-btn"
    startBtn.className = "btn mb-2 p-2"
    main.appendChild(startBtn);
    main.appendChild(container)

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
    colDiv.className = 'col-xl-6 col-sm-6 grid-margin stretch-card';

    const cardDiv = document.createElement('div');
    cardDiv.className = 'card';

    const cardBodyDiv = document.createElement('div');
    cardBodyDiv.className = 'card-body p-2';

    const cardTitleDiv = document.createElement('div');
    cardTitleDiv.className = 'card-title d-flex align-items-center align-self-start';

    const titleP = document.createElement('p');
    titleP.className = 'mb-0 text-muted';
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
}

function startComparison(results){
    results.forEach((result, index) => {
        const bars = document.getElementsByClassName(result.sortType);
        startAnimation(result.moves, bars, result.sortType);
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

             }, delay- 0.8);
        }, index * delay); // Delay between steps
    });
}

function handleStartButtonCLick(){
    const button = document.getElementById('start-btn');

    if(!algorithmStarted){
         startComparison(results)
         button.textContent = 'Update';

    }else{
        button.textContent = 'Start';
          const main = document.getElementById('display-container');
            main.innerHTML = '';
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

document.getElementById('start-btn').addEventListener('click', () => handleStartButtonCLick());



}
Sort()











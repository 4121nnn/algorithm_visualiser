
let delay = 10;
let steps = results[0].steps;
let performances = results[0].languagePerformances
let array = results[0].nonSortedArray
//console.log(results)
//console.log(steps)
//console.log(performances)
//console.log(array)
let algorithmStarted = false;

function setupBars(results){
    const container = document.getElementById('array-container');
    container.innerHTML = '';

    results.forEach(result => {
        createBars( container,
                    result.nonSortedArray,
                    result.sortType,
                    getName(result.sortType),
                    result.languagePerformances[0].time,
                    null
        );
    })
}

function createBars(container, array, name, title, timing, info) {
    console.log(name + " " + title);
    // Create the outermost div with the specified classes
    const colDiv = document.createElement('div');
    colDiv.className = 'col-xl-6 col-sm-6 grid-margin stretch-card';

    // Create the card div
    const cardDiv = document.createElement('div');
    cardDiv.className = 'card';

    // Create the card-body div
    const cardBodyDiv = document.createElement('div');
    cardBodyDiv.className = 'card-body p-2';

    // Create the card-title div
    const cardTitleDiv = document.createElement('div');
    cardTitleDiv.className = 'card-title d-flex align-items-center align-self-start';

    // Create the title paragraph
    const titleP = document.createElement('p');
    titleP.className = 'mb-0 text-muted';
    titleP.textContent = title;

    // Create the timing paragraph
    const timingP = document.createElement('p');
    timingP.className = 'text-success ml-2 mb-0 font-weight-medium';
    timingP.id = name + 'timing';
    timingP.style.textTransform = 'none';

    // Create the info paragraph
    const infoP = document.createElement('p');
    infoP.className = 'text-muted ml-2 mb-0 font-weight-normal ';
    infoP.textContent = '(no anim. ' + timing + ' ms)';
    infoP.id = name + 'info';
    infoP.style.textTransform = 'none';
    infoP.style.display = 'none';

    // Append the paragraphs to the card-title div
    cardTitleDiv.appendChild(titleP);
    cardTitleDiv.appendChild(timingP);
    cardTitleDiv.appendChild(infoP);

    // Create the inner container for bars
    const innerContainer = document.createElement('div');

    // Create bars and append them to the inner container
    array.forEach(value => {
        const bar = document.createElement('div');
        bar.className = 'bar ' + name;
        bar.style.height = value + 'px';
        innerContainer.appendChild(bar);
    });

    // Append the card-title and innerContainer to card-body
    cardBodyDiv.appendChild(cardTitleDiv);
    cardBodyDiv.appendChild(innerContainer);

    // Append card-body to the card
    cardDiv.appendChild(cardBodyDiv);

    // Append the card to the colDiv
    colDiv.appendChild(cardDiv);

    // Finally, append the entire structure to the provided container
    container.appendChild(colDiv);
}

function handleStartButtonCLick(){
    const button = document.getElementById('start-btn');

    if(!algorithmStarted){
        startComparison(results)
         button.textContent = 'Update';
         algorithmStarted = true;
    }else{
        setupBars(results);
        button.textContent = 'Start';
        algorithmStarted = false;
    }
}

function startComparison(results){
    results.forEach(result => {
        const bars = document.getElementsByClassName(result.sortType);
        startAnimation(result.steps, bars, result.sortType);
    })
}

function startAnimation(steps, bars, sortType) {
    const startTime = Date.now();
    steps.forEach((step, index) => {
        setTimeout(() => {
            bars[step.firstInd].style.backgroundColor = 'red'; // Change to desired color
            bars[step.secondInd].style.backgroundColor = 'red'; // Change to desired color
            if (step.swapped) {
                // Swap the heights
                [bars[step.firstInd].style.height, bars[step.secondInd].style.height] =
                [bars[step.secondInd].style.height, bars[step.firstInd].style.height];
            }

             setTimeout(() => {
                bars[step.firstInd].style.backgroundColor = ''; // Reset to original color
                bars[step.secondInd].style.backgroundColor = ''; // Reset to original color

                if (index === steps.length - 1) {
                    const endTime = Date.now(); // Record the end time
                    const totalTime = (endTime - startTime) / 1000;
                    document.getElementById(sortType + 'timing').innerText = `${totalTime} s`;
                    document.getElementById(sortType + 'info').style.display = 'block';
                }

             }, delay- 0.8);
        }, index * delay); // Delay between steps
    });
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
        default:
            return "";
    }
}




setupBars(results);

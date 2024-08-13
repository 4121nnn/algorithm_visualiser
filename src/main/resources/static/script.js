
let delay = 1;
let steps = results[0].steps;
let performances = results[0].languagePerformances
let array = results[0].nonSortedArray
//console.log(results)
//console.log(steps)
//console.log(performances)
//console.log(array)

function setupBars(results){
    const container = document.getElementById('array-container');
    container.innerHTML = '';
    results.forEach(result => {
        createBars(container, result.nonSortedArray, result.sortType);
    })
}

function createBars(container, array, name) {
    const innerContainer = document.createElement('div');
    array.forEach(value => {
        const bar = document.createElement('div');
        bar.className = 'bar ' + name;
        bar.style.height = value + 'px';
        innerContainer.appendChild(bar);
    });
    container.appendChild(innerContainer);
}

function startComparison(results){
    results.forEach(result => {
        const bars = document.getElementsByClassName(result.sortType);
        startAnimation(result.steps, bars);
    })
}

function startAnimation(steps, bars) {
    steps.forEach((step, index) => {
        setTimeout(() => {
            bars[step.firstInd].style.backgroundColor = 'red'; // Change to desired color
            bars[step.secondInd].style.backgroundColor = 'red'; // Change to desired color
            if (step.swapped) {
                // Swap the heights
                [bars[step.firstInd].style.height, bars[step.secondInd].style.height] =
                [bars[step.secondInd].style.height, bars[step.firstInd].style.height];
            }
            // Reset the background color after a short delay
            // Adjust the timing if necessary
             setTimeout(() => {
                bars[step.firstInd].style.backgroundColor = ''; // Reset to original color
                bars[step.secondInd].style.backgroundColor = ''; // Reset to original color
             }, delay- 0.8);
        }, index * delay); // Delay between steps
    });
}




setupBars(results);

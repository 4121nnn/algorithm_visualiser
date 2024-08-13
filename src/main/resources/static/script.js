console.log(nums);

function createBars(array) {
    const container = document.getElementById('array-container');
    container.innerHTML = ''; // Clear the container
    array.forEach(value => {
        const bar = document.createElement('div');
        bar.className = 'bar';
        bar.style.height = value + 'px';
        container.appendChild(bar);
    });
}

function startAnimation() {
    console.log("started");
    console.log(steps);
    const bars = document.getElementsByClassName('bar');
    steps.forEach((step, index) => {
        setTimeout(() => {
            if (step[2] === 1) { // Swap
                [bars[step[0]].style.height, bars[step[1]].style.height] = [bars[step[1]].style.height, bars[step[0]].style.height];
            }
        }, index * 500); // Delay between steps
    });
}

// Initial array (for demonstration)
createBars(nums);

window.onload = function () {
    // drawPointsOnCanvas()
    svgClick()
}



function svgClick(){

    let svg = document.getElementById("svg")
    let selectedR = null
    let rBool = false
    svg.addEventListener('click', (event) => {
        rButtons.forEach(rButton => {
            if (rButton.classList.contains("tmp")){
                selectedR = rButton.value
                rBool = true
            }
        })
        if(!rBool){
            rErrorElement.textContent = "Выберите Радиус R"

        }
        else {

            rErrorElement.textContent = ""
            const r = selectedR
            localStorage.setItem("rVal", r.toString())
            const rect = svg.getBoundingClientRect()
            const x = event.clientX - rect.left
            const y = event.clientY - rect.top

            send_intersection_rq(x, y, r)
        }
    })
}
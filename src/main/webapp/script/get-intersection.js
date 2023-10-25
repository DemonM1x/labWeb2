function send_intersection_rq(x,y,r) {

    const svg = document.getElementsByTagName("svg")[0]
    let row = document.createElementNS("http://www.w3.org/2000/svg", "circle")
    row.setAttribute("cx", x.toString())
    row.setAttribute("cy", y.toString())
    row.setAttribute("r", "2")
    row.setAttribute("stroke", "red")
    row.setAttribute("fill", "red")
    row.setAttribute("class", "click-circle")
    svg.appendChild(row)
    let scale = r
    let start = 150
    let rPosition = 100
    x = (x  - start)/rPosition * scale
    y = -(y - start)/rPosition * scale
    let xFloat = parseFloat(x)
    let yFloat = parseFloat(y)
    // send request

    let queryString = "x=" + xFloat + "&y=" + yFloat + "&r=" + r
    fetch(new URL("controller?" + queryString, window.location.href), {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
    }).then(responseCatched => {
        location.reload()
        if (responseCatched.ok) {
            return
        }
        throw new Error(responseCatched.statusText)
    })

}

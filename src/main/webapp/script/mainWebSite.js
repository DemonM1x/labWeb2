
const xRadios = document.querySelectorAll('input[name="x"]');
const yText = document.querySelector('input[name="y"]');
const rButtons = document.querySelectorAll('input[type="button"].btn');

const xErrorElement = document.getElementById("xError");
const yErrorElement = document.getElementById("yError");
const rErrorElement = document.getElementById("rError");
const rHidden = document.querySelectorAll('.value-button__input')[0]

yText.addEventListener('input', function(e){
    var inputValue = e.target.value;
    var sanitizerValue = inputValue.replace(/[^0-9.,\-]/g, '')
    e.target.value = sanitizerValue;
});
$(function(){
    $(".btn").click(function(){
        for (const rButton of rButtons){
            if (rButton.classList.contains("tmp") && $(this).val() != $(rButton).val()){
                $(rButton).toggleClass("tmp")
                $("label[for='"+ $(rButton).attr('id')+"']").toggleClass("iconize");
            }
        }
        $(this).toggleClass("tmp")
        $("label[for='"+ $(this).attr('id')+"']").toggleClass("iconize");

        return true;
    });
});


function sendFunction(){
    let selectedX;
    for(const xRadio of xRadios){
        if (xRadio.checked){
            selectedX = xRadio.value;
            break
        }
    }
    let selectedR;
    for(const rButton of rButtons){
        if (rButton.classList.contains("tmp")){
            selectedR = rButton.value;
        }
    }
    rHidden.value = selectedR

    xErrorElement.textContent = "";
    yErrorElement.textContent = "";
    rErrorElement.textContent = "";

    if (selectedX == null){
        xErrorElement.textContent = "Выберите координату X";
        return false;
    }
    const yValue = parseFloat(yText.value);

    if (isNaN(yValue)) {
        yErrorElement.textContent = "Неправильный формат ввода координаты Y";

        return false;
    }
    if (selectedR == null){
        rErrorElement.textContent = "Выберите радиус";

        return false;
    }
    selectedX = parseFloat(selectedX);

    if (![-2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2].includes(selectedX)) {
        xErrorElement.textContent = "Координата X должна быть одним из элементов массива: [-2, -1.5, -1, -0.5, 0, 0.5, 1, 1.5, 2]";

        return false; 
    }
    if (yText.value == '' ||  parseFloat( yText.value) >= 3 || parseFloat(yText.value) <= -5 ){
        yErrorElement.textContent = "Введите координату Y корректно: (-5 , 3)";
        return false;
    }
    for(const rButton of rButtons){
        selectedR = parseFloat(rButton.value);
        if (![1, 1.5, 2, 2.5, 3].includes(selectedR)){
            rErrorElement.textContent = "Радиус R должен быть одним из элементов массива: [1, 1.5, 2, 2.5, 3]";
            return false;
        }

    }
    localStorage.setItem("rVal", rHidden.value)
    return true;
    
}
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.time.format.DateTimeFormatter,java.util.List" %>
<%@ page import="beans.UserDataList,beans.UserData" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>

    <link rel="stylesheet" href="css/mainWebSite.css" />
    <title>Лабораторная 1</title>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
</head>

<header>
    <div class="box">
        <div class="fio">Подольский Вячеслав Ильич</div>
        <div class="group">Группа: P3220</div>
        <div class="variant">Вариант: 3007</div>
    </div>
</header>
<main class="container">
    <div class="svg" id = "svg">
        <svg height="300" width="300" xmlns="http://www.w3.org/2000/svg">

            <!-- Оси координат -->
            <line stroke="black" x1 = 0 x2 = 300 y1 = 150 y2 = 150></line>
            <line stroke="black" x1 = 150 x2 = 150 y1=0 y2 = 300></line>
            <polygon points="150,0 144,15 156,15" stoke = "white"></polygon>
            <polygon points="285,144 300,150 285,156"></polygon>

            <!-- Линии -->
            <line stroke="black" x1 = 50 y1 = 155 x2 = 50 y2 = 145></line>
            <line stroke="black" x1 = 100 y1 = 155 x2 = 100 y2 = 145></line>
            <line stroke="black" x1 = 200 y1 = 155 x2 = 200 y2 = 145></line>
            <line stroke="black" x1 = 250 y1 = 155 x2 = 250 y2 = 145></line>
            <line stroke="black" x1 = 145 y1 = 200 x2 = 155 y2 = 200></line>
            <line stroke="black" x1 = 145 y1 = 250 x2 = 155 y2 = 250></line>
            <line stroke="black" x1 = 145 y1 = 100 x2 = 155 y2 = 100></line>
            <line stroke="black" x1 = 145 y1 = 50 x2 = 155 y2 = 50></line>

            <!-- Фигуры -->

            <rect height="100" width="100" stroke="yellow" fill="skyblue"
                  x = "150" y = "50"></rect>

            <path d="M 100 150 A 50 50, 0, 0, 1, 150 100 L 150 150 Z"
                  fill="pink" stroke="black" fill-opacity="0.1"></path>
            
            <polygon points="150,150 150,200 250,150" fill="gold" stroke = "orange" fill-opacity="0.1"></polygon>

            <!-- Центр оси координат -->
            <circle cx="150" cy="150" id="target-dot" r="1" stroke="white" fill="white"></circle>

            <!-- Отметки -->

            <text fill="white" x="195" y="140">R/2</text>
            <text fill="white" x="248" y="140">R</text>

            <text fill="white" x="40" y="140">-R</text>
            <text fill="white" x="90" y="140">-R/2</text>

            <text fill="white" x="160" y="105">R/2</text>
            <text fill="white" x="160" y="55">R</text>

            <text fill="white" x="160" y="205">-R/2</text>
            <text fill="white" x="160" y="255">-R</text>

            <text fill="white" x="160" y="10">Y</text>
            <text fill="white" x="290" y="140">X</text>
        </svg>
    </div>
    <p class="p">Тестирование попадания точки на плоскость</p>
<div class="form" id = "form">
    <form action="controller" method="GET"  onsubmit=" return sendFunction()">
        <div class="form-container">
            <div class="field_x">
                <p>Координата X</p>
                <div class="radio_btn">
                    <input type="radio" id="x1" name="x" value="-2"/>
                    <label for="x1">-2</label>
                </div>

                <div class="radio_btn">
                    <input type="radio" id="x2" name="x" value="-1.5"/>
                    <label for="x2">-1.5</label>
                </div>

                <div class="radio_btn">
                    <input type="radio" id="x3" name="x" value="-1"/>
                    <label for="x3">-1</label>
                </div>

                <div class="radio_btn">
                    <input type="radio" id="x4" name="x" value="-0.5"/>
                    <label for="x4">-0.5</label>
                </div>

                <div class="radio_btn">
                    <input type="radio" id="x5" name="x" value="0"/>
                    <label for="x5">0</label>
                </div>

                <div class="radio_btn">
                    <input type="radio" id="x6" name="x" value="0.5"/>
                    <label for="x6">0.5</label>
                </div>

                <div class="radio_btn">
                    <input type="radio" id="x7" name="x" value="1"/>
                    <label for="x7">1</label>
                </div>

                <div class="radio_btn">
                    <input type="radio" id="x8" name="x" value="1.5"/>
                    <label for="x8">1.5</label>
                </div>

                <div class="radio_btn">
                    <input type="radio" id="x9" name="x" value="2"/>
                    <label for="x9">2</label>
                </div>
                <span class="error" id="xError"></span>
            </div>
            <div class="field_y">
                <p class="text_y">Координата Y</p>
                <input type="text" id="field_y" name="y" placeholder="(-3...5)" maxlength="16"/>
                <span class="error" id="yError"></span>
            </div>
            <div class="button_r">
                <p class="text_r">Выберите R</p>
                <input type="button" id="r1" name="r" value="1" class="btn"/>
                <label class="radius" for="r1">1</label>
                <input type="button" id="r2" name="r" value="1.5" class="btn"/>
                <label class="radius" for="r2">1.5</label>

                <input type="button" id="r3" name="r" value="2" class="btn"/>
                <label class="radius" for="r3">2</label>

                <input type="button" id="r4" name="r" value="2.5" class="btn"/>
                <label class="radius" for="r4">2.5</label>

                <input type="button" id="r5" name="r" value="3" class="btn"/>
                <label class="radius" for="r5">3</label>
                <input name="r" class="value-button__input save-value" type="hidden">
                <span class="error" id="rError"></span>
            </div>
            <input type="submit" class="submit" value="Подтвердить"/>
        </div>
    </form>
    </div>
    <div class="table-main">
    <table class="table" border = '1'>
        <thead>
        <tr>
            <th>X</th>
            <th>Y</th>
            <th>R</th>
            <th>Результат</th>
            <th>Время выполнения</th>
            <th>Время скрипта</th>
        </tr>
        </thead>
        <tbody class="table-body">
        <% UserDataList userDataListObj = (UserDataList) application.getAttribute("UserDataList");
            if (userDataListObj != null) {
                List<UserData> userDataList = userDataListObj.getUserDataList();
                for (int i = 0; i < (userDataList == null ? 0 : userDataList.size()); i++) { %>
        <tr>
            <td class="xTable" ><%=userDataList.get(i).getxVal()%></td>
            <td class="yTable" ><%=userDataList.get(i).getyVal()%></td>
            <td class="rTable"><%=userDataList.get(i).getrVal()%></td>
            <td><%=userDataList.get(i).isHit() ? "HIT" : "MISS" %></td>
            <td><%=userDataList.get(i).getCurrentTime()%></td>
            <td><%=userDataList.get(i).getExecutionTime()%></td>
        </tr>
        <% }
        } %>
        </tbody>
    </table>
        <button class="glow-on-hover btn-clear" id='submit' type='button'>Clear</button>
    </div>
</main>
<script src="script/mainWebSite.js"></script>
<script src="script/canvas.js"></script>
<script src="script/get-intersection.js"></script>
<script src="script/btn-event.js"></script>
<script type="text/javascript">



        <% if (userDataListObj != null) {
           List<UserData> userDataList = userDataListObj.getUserDataList(); %>
        setPreviousRValue()
        <% if (userDataList != null && userDataList.size() > 0) { %>
        let rShowingValue = getRValue()
        <% for (int index = 0; index < userDataList.size(); index++) { %>
            drawSvgPoint(<%= userDataList.get(index).getxVal() %>, <%= userDataList.get(index).getyVal() %>,
                rShowingValue)

        <% }
    }
} %>

    function setPreviousRValue() {
        let rValue = getRValue()
        $(function(){
            for(const rButton of rButtons) {
                if (rButton.value == rValue) {
                    $(rButton).toggleClass("tmp")
                    $("label[for='" + $(rButton).attr('id') + "']").toggleClass("iconize");
                }
            }

        });
        }



    function setRValue() {
        let newRVal = null
        for(const rButton of rButtons){
            if (rButton.classList.contains("tmp")){
                newRVal = rButton.value
            }
        }
        localStorage.setItem("rVal", newRVal)
        return newRVal
    }

    function getRValue() {
        let rValue = parseFloat(localStorage.getItem("rVal"))
        if (!rValue) {
            rValue = parseFloat(setRValue())
        }
        console.log(rValue)
        return rValue
    }

    jQuery(function(){
        let max = 1;
        let buttons = $('input[type="button"]')

        buttons.change(function(){
            let current = buttons.filter('.tmp').length
            if (!current) {

            } else {
                setRValue()
                location.reload()
            }
        });
    });
    function drawSvgPoint(x, y, r){
        const svg = document.getElementsByTagName("svg")[0]
        let row = document.createElementNS("http://www.w3.org/2000/svg", "circle")
        let scale = r
        let start = 150
        let rPosition = 100
        x = x * rPosition / scale + start
        y = (-y *rPosition / scale + start)
        row.setAttribute("cx", x.toString())
        row.setAttribute("cy", y.toString())
        row.setAttribute("r", "2")
        row.setAttribute("stroke", "red")
        row.setAttribute("fill", "red")
        row.setAttribute("class", "click-circle")
        svg.appendChild(row)


    }

</script>
</body>
</html>

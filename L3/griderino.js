/**
 * Created by Fredrik on 2/4/2015.
 */

var shotsFired = 0, hits = 0;

var chips = new Array(10);
for (var i = 0; i < 10; i++) {
    chips[i] = new Array(10);
}

for(var i=0; i<10; i++) {
    var x = Math.floor((Math.random() * 9) + 1);
    var y = Math.floor((Math.random() * 9) + 1);

    chips[x][y] = true;
}

function resetDaBoard() {
    for (var i = 0; i < 10; i++) {
        chips[i] = new Array(10);
    }
    var buttons = document.getElementsByName("sinkedShip");
    for (var i = 0; i < buttons.length; i++) {
        if(buttons[i].style.color == "blue" || buttons[i].style.color == "yellow") {
            var x = buttons[i].value.charAt(0);
            var y = buttons[i].value.charAt(2);
            chips[x][y] = true;
        }
        buttons[i].style.color = "";
        buttons[i].style.background = "";
    }
    shotsFired = 0;
    hits = 0;
    document.getElementsByName("pressed")[0].value = "";
}

function clickedDaButton(e) {
    var x = e.value.charAt(0);
    var y = e.value.charAt(2);
    shotsFired++;
    if(chips[x][y]){
        e.style.color = "yellow";
        e.style.background = "green";
        hits++;
    } else {
        e.style.color = "blue";
        e.style.background = "red";
    }
    document.getElementsByName("pressed")[0].value = e.value + " hits: " + hits + " shots fired: " + shotsFired;
}

for (var i=0; i<9; i++) {
    for(var j=0; j<9; j++) {
        document.write("<button onclick='clickedDaButton(this)'s id=" + (i*10 + j) + " type='button' name='sinkedShip' value='" + i + "," + j + "'>Sink dis</button>");
    }
    document.write("<br>");
}

document.write("<br><input type='text' name='pressed'>");
document.write("<br><button onclick='resetDaBoard()' type='button'>Reset da board</button>");
/**
 * Created by Fredrik on 2/4/2015.
 */

var shotsFired = 0, hits = 0;

var ships = new Array(10);
for (var i = 0; i < 10; i++) {
    ships[i] = new Array(10);
}

function init() {
    var x = Math.floor((Math.random() * 8));
    var y = 0;

    ships[x][y] = true;
    ships[x+1][y] = true;

    x = Math.floor((Math.random() * 7));
    y = Math.floor((Math.random() * 2) + 1);

    ships[x][y] = true;
    ships[x+1][y] = true;
    ships[x+2][y] = true;

    x = Math.floor((Math.random() * 3));
    y = Math.floor((Math.random() * 4) + 3);

    ships[x][y] = true;
    ships[x][y+1] = true;
    ships[x][y+2] = true;

    x = Math.floor((Math.random() * 3) + 3);
    y = Math.floor((Math.random() * 3) + 3);

    ships[x][y] = true;
    ships[x+1][y] = true;
    ships[x+2][y] = true;
    ships[x+3][y] = true;

    x = Math.floor((Math.random() * 2) + 3);
    y = Math.floor((Math.random() * 3) + 6);

    ships[x][y] = true;
    ships[x+1][y] = true;
    ships[x+2][y] = true;
    ships[x+3][y] = true;
    ships[x+4][y] = true;

}

function resetTheBoard() {
    for (var i = 0; i < 10; i++) {
        ships[i] = new Array(10);
    }
    var buttons = document.getElementsByName("sinkedShip");
    for (var i = 0; i < buttons.length; i++) {
        buttons[i].style.color = "";
        buttons[i].style.background = "";
        buttons[i].innerHTML = "shoot";
    }
    shotsFired = 0;
    hits = 0;
    init();
    document.getElementsByName("pressed")[0].value = "";
}

function clickedButton(e) {
    var x = e.value.charAt(0);
    var y = e.value.charAt(2);
    shotsFired++;
    if(ships[x][y]){
        e.style.color = "yellow";
        e.style.background = "green";
        e.innerHTML = "hit";
        hits++;
    } else {
        e.style.color = "blue";
        e.style.background = "red";
        e.innerHTML = "missed";
    }
    document.getElementsByName("pressed")[0].value = e.value + " hits: " + hits + " shots fired: " + shotsFired;
}

for (var i=0; i<9; i++) {
    for(var j=0; j<9; j++) {
        document.write("<button style='width:60px;' onclick='clickedButton(this)' id=" + (i*10 + j) + " type='button' name='sinkedShip' value='" + i + "," + j + "'>shoot</button>");
    }
    document.write("<br>");
}

document.write("<br><input type='text' name='pressed'>");
document.write("<br><button onclick='resetTheBoard()' type='button'>Reset the board</button>");
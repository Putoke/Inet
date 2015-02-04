/**
 * Created by Fredrik on 2/4/2015.
 */

function clickedDaButton(e) {
    e.style.color = "blue";
    e.style.background = "red";
    document.getElementsByName("pressed")[0].value = e.value;
}

for (var i=0; i<9; i++) {
    for(var j=0; j<9; j++) {
        document.write("<button onclick='clickedDaButton(this)'s id=" + (i*10 + j) + " type='button' value='" + i + "," + j + "'>Sink dis</button>");
    }
    document.write("<br>");
}

document.write("<br><input type='text' name='pressed'>");
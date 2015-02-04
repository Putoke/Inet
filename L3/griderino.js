/**
 * Created by Fredrik on 2/4/2015.
 */

var chips = new Array(10);
for (var i = 0; i < 10; i++) {
    chips[i] = new Array(10);
}

for(var i=0; i<10; i++) {
    var x = Math.floor((Math.random() * 9) + 1);
    var y = Math.floor((Math.random() * 9) + 1);

    chips[x][y] = true;
}

function clickedDaButton(e) {
    var x = e.value.charAt(0);
    var y = e.value.charAt(2);
    if(chips[x][y]){
        e.style.color = "yellow";
        e.style.background = "green";
    } else {
        e.style.color = "blue";
        e.style.background = "red";
    }
    document.getElementsByName("pressed")[0].value = e.value;
}

for (var i=0; i<9; i++) {
    for(var j=0; j<9; j++) {
        document.write("<button onclick='clickedDaButton(this)'s id=" + (i*10 + j) + " type='button' value='" + i + "," + j + "'>Sink dis</button>");
    }
    document.write("<br>");
}

document.write("<br><input type='text' name='pressed'>");

<?php

$mysqli = new mysqli("mysql-vt2015.csc.kth.se", "frebernuser", "itn6Fy9E", "frebern");
if ($mysqli->connect_errno) {
    echo "Failed to connect to MySQL: (" . $mysqli->connect_errno . ") " . $mysqli->connect_error;
}

$area = $mysqli->query("SELECT DISTINCT lan FROM bostader");
$type = $mysqli->query("SELECT DISTINCT objekttyp FROM bostader");

$cookieValues = explode(":", $_COOKIE["values"]);

$lan = utf8_decode($cookieValues[0]);
$typ = utf8_decode($cookieValues[1]);
$adress = utf8_decode($cookieValues[2]);
$sort = $cookieValues[3];
$asc = $cookieValues[4];

?>

<html>
<head>
	<script>

	var asc = 1;
	var sort = "pris";

	function clickerino(str) {
		asc ^= 1;
		sort = str;
		showTable();
	}

	function showTable() {
		var str = document.getElementsByName("adress")[0].value;
	    if (str.length == 0) { 
	        document.getElementById("table").innerHTML = "";
	        return;
	    } else {
	        var xmlhttp = new XMLHttpRequest();
	        xmlhttp.onreadystatechange = function() {
	            if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
	                document.getElementById("table").innerHTML = xmlhttp.responseText;
	            }
	        }
	        var lan = document.getElementsByName("lan")[0].value;
	        var typ = document.getElementsByName("typ")[0].value;


	        var query = "result.php?lan="+lan+"&typ="+typ+"&adress=" + str + "&sort=" + sort + "&asc=" + asc;
			var cookie = "values="+lan+":"+typ+":"+str+":"+sort+":"+asc;
			document.cookie = cookie;
	        xmlhttp.open("GET", query, true);
	        xmlhttp.send();
	    }
	}

	</script>

</head>

<body>

	<form type="post" action="result.php">

		L&auml;n: <select onchange="showTable()" name="lan"> <?php
		for ($row_no = 0; $row_no < $area->num_rows; $row_no++) {
			$area->data_seek($row_no);
			$row = $area->fetch_assoc();
			if($row['lan'] == $lan)
				echo "<option value='" . $row['lan'] . "' selected>" . $row['lan'] . "</option>";
			else
				echo "<option value='" . $row['lan'] . "'>" . $row['lan'] . "</option>";
		}
		 ?> </select>

		 Typ: <select onchange="showTable()" name="typ"> <?php
		for ($row_no = 0; $row_no < $type->num_rows; $row_no++) {
			$type->data_seek($row_no);
			$row = $type->fetch_assoc();
			if($row['objekttyp'] === $typ)
				echo "<option value='" . $row['objekttyp'] . " ' selected>" . $row['objekttyp'] . "</option>";
			else
				echo "<option value='" . $row['objekttyp'] . "'>" . $row['objekttyp'] . "</option>";
		}
		 ?> </select>

		 Adress: <input onkeyup="showTable()" type="text" name="adress" value="<?php echo $adress ?>">
		 <input type="hidden" name="sort" value="pris">
		 <input type="hidden" name="asc" value="true">

	</form>

	<br>
	<span id="table"></span>

</body>

</html>

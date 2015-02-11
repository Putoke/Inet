<?php 
$mysqli = new mysqli("mysql-vt2015.csc.kth.se", "frebernuser", "itn6Fy9E", "frebern");
$mysqli->set_charset( "utf8" );
if ($mysqli->connect_errno) {
    echo "Failed to connect to MySQL: (" . $mysqli->connect_errno . ") " . $mysqli->connect_error;
}

$lan = utf8_encode($_GET["lan"]);
$typ = utf8_encode($_GET["typ"]);
$adress = utf8_encode($_GET["adress"]);
$sort = utf8_encode($_GET["sort"]);
$asc = utf8_encode($_GET["asc"]);
$order = "ASC";
if(!$asc) {
	$order = "DESC";
}

$query = "SELECT * FROM bostader WHERE lan = '" . $lan . "' AND objekttyp = '" . $typ . "' AND adress LIKE '%" . $adress . "%' ORDER BY " . $sort . " " . $order . ";";
$res = $mysqli->query($query);

$lan = $_GET["lan"];
$typ = $_GET["typ"];
$adress = $_GET["adress"];
$sort = $_GET["sort"];
$asc = $_GET["asc"];

?>

<table border=1>
	<tr>
		<form type="post" action="result.php">
			<?php
				$print = "<input type='hidden' name='";
				$print2 = "' value='";
				$print3 = "'>";
				echo $print . "lan" . $print2 . $lan . $print3;
				echo $print . "typ" . $print2 . $typ . $print3;
				echo $print . "adress" . $print2 . $adress . $print3;
				$temp = !$asc;
				echo $print . "asc" . $print2 . $temp . $print3;
			?>
		<td><input onclick="clickerino(this.value)" type="submit" name="sort" value="lan"></input></td>
		<td><input onclick="clickerino(this.value)" type="submit" name="sort" value="objekttyp"></td>
		<td><input onclick="clickerino(this.value)" type="submit" name="sort" value="adress"></td>
		<td><input onclick="clickerino(this.value)" type="submit" name="sort" value="area"></td>
		<td><input onclick="clickerino(this.value)" type="submit" name="sort" value="rum"></td>
		<td><input onclick="clickerino(this.value)" type="submit" name="sort" value="pris"></td>
		<td><input onclick="clickerino(this.value)" type="submit" name="sort" value="avgift"></td>
		</form>
	</tr>
<?php
		for ($row_no = 0; $row_no < $res->num_rows; $row_no++) {
			$res->data_seek($row_no);
			$row = $res->fetch_assoc();
			echo "<tr><td>" . $row['lan'] . "</td><td>" . $row['objekttyp'] . "</td><td>" . $row['adress'] . "</td><td>" . $row['area'] . "</td><td>" . $row['rum'] . "</td><td>" . $row['pris'] . "</td><td>" . $row['avgift'] . "</td></tr>";
		}
?> 
</table>
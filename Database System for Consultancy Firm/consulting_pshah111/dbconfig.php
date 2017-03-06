<?php
$host = "localhost";
$user = "root";
$password = "";
$datbase = "consulting_pshah111";
$db = mysqli_connect($host,$user,$password);
mysqli_select_db($datbase,$db);
if( ! $db = mysqli_connect($host,$user,$password) ) {
    die('No connection: ' . mysqli_connect_error());
}
else
	echo "Database Connection is successful";
?>

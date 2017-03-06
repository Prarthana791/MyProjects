<html>
<body>
<h2 align = 'center'>Welcome to Topper Consultancy Firm</h2>
<form action= "Login.php">
<br><br><br><br><br><br>
<table align ='center'><tr><td>
<img src = 'Image.jpg'></td>
<td>
User ID:&nbsp&nbsp <input type = "text" name="username"><br><br>
Password: <input type = "password" name="password"><br><br>
<input type ="submit">
</td></tr></table>

</form>
</html>
<?php
$connection = mysqli_connect('localhost', 'root', ''); 
mysqli_select_db($connection,'consulting_pshah111');

if(isset($_REQUEST['username']) && isset($_REQUEST['password']) )
{
$id=$_GET['username']; 
$pwd=$_GET['password'];

$query = "SELECT * FROM login"; 
$result = mysqli_query($connection,$query);
while($row = mysqli_fetch_array($result))
{
if($row['id']==($id) && $row['password']==($pwd) )
{
header('Location:FinalProjectDB.php');
}
}
echo "<h2 align = 'center'>Wrong credentials!</h2>";

}

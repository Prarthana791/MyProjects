<html>
<head>
<title>Consulting Firm Sample</title>
<script src="//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<link rel='stylesheet' type='text/css' href='//maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css'>
<style>
  .bs-example{
    	margin: 20px;
    }
	.table {
		width:60%;
	}
</style>
</head>
<body>


    <nav role="navigation" class="navbar navbar-default">

        <!-- Brand and toggle get grouped for better mobile display -->

        <div class="navbar-header">

            <button type="button" data-target="#navbarCollapse" data-toggle="collapse" class="navbar-toggle">

                <span class="sr-only">Toggle navigation</span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>

            </button>

            <a href="displaymentor.php" class="navbar-brand">Consulting_pshah111</a>

        </div>

        <!-- Collection of nav links and other content for toggling -->

        <div id="navbarCollapse" class="collapse navbar-collapse">

            <ul class="nav navbar-nav">

                <li class="active"><a name="display" href="FinalProjectDB.php?mentor=mentor">Mentors</a></li>

                <li><a href="FinalProjectDB.php?client=client">Clients</a></li>

                <li><a href="FinalProjectDB.php?agent=agent">Agents</a></li>
                 <li><a href="FinalProjectDB.php?contract=contract">Contract Summary</a></li>
<li><a href="FinalProjectDB.php?contractreport=contractreport">Contract Report</a></li>
<li><a href="FinalProjectDB.php?contractreportg=contractreportg">Contract Report Graph</a></li>


            </ul>

            <ul class="nav navbar-nav navbar-right">

                <li><a href="Login.php">Logout</a></li>

            </ul>

        </div>

    </nav>


</body>
</html>


<?php
$connection = mysqli_connect('localhost', 'root', ''); 
mysqli_select_db($connection,'consulting_pshah111');

if(isset($_REQUEST['mentor']))
{
$dis=($_GET['mentor']); 

$query = "SELECT * FROM mentor_dbfp"; 
$result = mysqli_query($connection,$query);

echo "<div class='bs-example'>";
echo "<table class='table table-striped'>"; // start a table tag in the HTML
echo "<tr><th>mentorId</th><th>name</th><th>gender</th><th>email</th><th>contact_no</th><th>joining_dt</th><th>location</th><th>education_level</th></td>";
while($row = mysqli_fetch_array($result)){   //Creates a loop to loop through results
echo "<tr><td>" . $row['mentorId'] . "</td><td >" . $row['name'] . "</td><td >" . $row['gender'] . "</td><td >" . $row['email'] . "</td><td >" . $row['contact_no'] . "</td><td >" . $row['joining_dt'] . "</td><td >" . $row['location'] . "</td><td >" . $row['education_level'] . "</td></tr>";  //$row['index'] the index here is a field name
}

echo "</table>"; //Close the table in HTML
echo "</div>";
}
if(isset($_REQUEST['client']))
{
$dis=($_GET['client']); 

$query = "SELECT * FROM client_dbfp"; 
$result = mysqli_query($connection,$query);
echo "<div class='bs-example'>";
echo "<table class='table table-striped'>"; // start a table tag in the HTML
echo "<tr><th>clientId</th><th>name</th><th>university</th><th>location</th><th>degree_level</th></td>";
while($row = mysqli_fetch_array($result)){   //Creates a loop to loop through results
echo "<tr><td>" . $row['clientId'] . "</td><td >" . $row['name'] . "</td><td >" . $row['university'] . "</td><td >" . $row['location'] . "</td><td >" . $row['degree_level'] . "</td></tr>";  //$row['index'] the index here is a field name
}

echo "</table>"; //Close the table in HTML
echo "</div>";
}

if(isset($_REQUEST['agent']))
{
$dis=($_GET['agent']); 

$query = "SELECT * FROM agent_dbfp"; 
$result = mysqli_query($connection,$query);

echo "<div class='bs-example'>";
echo "<table class='table table-striped'>"; // start a table tag in the HTML
echo "<tr><th>agentId</th><th>name</th><th>email</th><th>contact_no</th><th>joining_dt</th></td>";
while($row = mysqli_fetch_array($result)){   //Creates a loop to loop through results
echo "<tr><td>" . $row['agentId'] . "</td><td >" . $row['name'] . "</td><td >" . $row['email'] . "</td><td >" . $row['contact_no'] . "</td><td >" . $row['joining_dt'] . "</td></tr>";  //$row['index'] the index here is a field name
}

echo "</table>"; //Close the table in HTML
echo "</div>";
}

if(isset($_REQUEST['contract']))
{
$dis=($_GET['contract']); 

$query = "SELECT * FROM contract_dbfp"; 
$result = mysqli_query($connection,$query);

echo "<div class='bs-example'>";
echo "<table class='table table-striped'>"; // start a table tag in the HTML
echo "<tr><th>contractId</th><th>clientId</th><th>agentId</th><th>fees</th><th>start_dt</th><th>end_dt</th></td>";
while($row = mysqli_fetch_array($result)){   //Creates a loop to loop through results
echo "<tr><td>" . $row['contractId'] . "</td><td >" . $row['clientId'] . "</td><td >" . $row['agentId'] . "</td><td >" . $row['fees'] . "</td><td >" . $row['start_dt'] . "</td><td >" . $row['end_dt'] . "</td></tr>";  //$row['index'] the index here is a field name
}

echo "</table>"; //Close the table in HTML
echo "</div>";
}
if(isset($_REQUEST['contractreport']))
{
$dis=($_GET['contractreport']); 

$query = "select count(contractid) as Contracts_Count,DATE_FORMAT(start_dt,'%Y-%m') as Period from CONTRACT_DBFP group by DATE_FORMAT(start_dt,'%Y-%m') order by DATE_FORMAT(start_dt,'%Y-%m')"; 
$result = mysqli_query($connection,$query);

echo "<div class='bs-example'>";
echo "<table class='table table-striped'>"; // start a table tag in the HTML
echo "<tr><th>Contracts_Count</th><th>Period</th></td>";
while($row = mysqli_fetch_array($result)){   //Creates a loop to loop through results
echo "<tr><td>" . $row['Contracts_Count'] . "</td><td >" . $row['Period'] . "</td></tr>";  //$row['index'] the index here is a field name
}

echo "</table>"; //Close the table in HTML
echo "</div>";
}
if(isset($_REQUEST['contractreportg']))
{
$dis=($_GET['contractreportg']); 
include 'BarGraph.html';
}


mysqli_close($connection); //Make sure to close out the database connection

?>

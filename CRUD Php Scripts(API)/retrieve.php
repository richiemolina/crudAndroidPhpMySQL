<?php 

	$connection = mysqli_connect("localhost","id15172686_ricardoandroid","09#aR&CFW+vS&}F0","id15172686_crudandroid");
	
	$result = array();
	$result['datos'] = array();
	$select= "SELECT *from datos";
	$responce = mysqli_query($connection,$select);
	
	while($row = mysqli_fetch_array($responce))
		{
			$index['id']      = $row['0'];
			$index['nombre']    = $row['1'];
			$index['email']   = $row['2'];
			$index['contacto'] = $row['3'];
			
			array_push($result['datos'], $index);
		}
			
			$result["success"]="1";
			echo json_encode($result);
			mysqli_close($connection);

 ?>


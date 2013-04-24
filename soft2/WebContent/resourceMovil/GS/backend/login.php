<?php
	include("utils/conexion.php");
	$link=Conectarse(); 
	$email = $_POST['email'];
	$pass =   $_POST['password'];
	//echo $email;
	$query = "SELECT * FROM Usuario WHERE (usuario_email = '$email' and usuario_password = '$pass')";
	//echo $query;
	$result = mysql_query($query) or die(mysql_error());
	$num=mysql_numrows($result);
	if ($num == 1) {
		$row = mysql_fetch_array( $result );
		$iduser = $row['usuario_id'];
		$nombreUser = $row['usuario_nombre'];
		echo "{\"success\":true, \"me\":\"\", \"idUser\":\"$iduser\", \"nombreUser\":\"$nombreUser\"}";
	
	}else{
		echo "{\"success\":true, \"me\":\"Usuario o clave incorrecto\"}";
	}
	mysql_close($link);
?>
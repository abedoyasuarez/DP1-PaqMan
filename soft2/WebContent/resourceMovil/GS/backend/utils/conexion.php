<?php
	function Conectarse() 
		{ 
		   if (!($link=mysql_connect("quilla.lab.inf.pucp.edu.pe:3306","inf2260882g2usr","lectura"))) 
		   { 
		      echo "Error conectando a la base de datos."; 
		      exit(); 
		   } 
		   if (!mysql_select_db("inf2260882g2",$link)) 
		   { 
		      echo "Error seleccionando la base de datos."; 
		      exit(); 
		   } 
		   return $link; 
		} 
?>
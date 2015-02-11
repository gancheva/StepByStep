<?php

	try {
		$host = "dibbit.nl";
		$dbname = "stepbystep";
		$user = "stepbystep";
		$pass = "henkie";
		
		$db = new PDO("mysql:host=$host;dbname=$dbname", "$user", "$pass");
		return $db;
	}
	catch(PDOException $e) {
		echo $e->getMessage();
	}
?>
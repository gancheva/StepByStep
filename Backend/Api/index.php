<?php
	require_once("VideoHandler.php");
	require 'Slim/Slim.php';
	\Slim\Slim::registerAutoloader();
	
	$app = new \Slim\Slim();
	
	$app->response->headers->set('Content-Type', 'application/json');
	$app->get('/videos', 'getVideos');
	$app->get('/videoForID/:id', 'getVideoForID');
	$app->post('/videos/add', 'addVideo');
	$app->post('/videos/update/:id', 'updateVideo');
	$app->get('/videosForType/:type', 'getVideosForType');
	$app->get('/videosForName/:name', 'getVideosForName');
	$app->post('/videos/delete/:id', 'deleteVideoForID');
	
 
	$app->run();
	
	function getVideos() {
		$vh = new VideoHandler();
		$vh->getVideos();
		echo $vh->getResponse();
	}
	
	function getVideoForID($id) {
		$vh = new VideoHandler();
		$vh->getVideoForID($id);
		echo $vh->getResponse();
	}
	
	function addVideo() {
		$vh = new VideoHandler();
		if(!isset($_POST["key"]) || !isset($_POST["name"]) || !isset($_POST["url"]) || !isset($_POST["type"])) {
				$message = "Missing fields";
				$vh->setResponse(array('message' => $message), 1);
		} else {
			if(checkKey($_POST["key"])) {
				$name = $_POST["name"];
				$url = $_POST["url"];
				$type = $_POST["type"];
				$vh->addVideo($name, $url, $type);
			}
			else {
				$message = "Invalid key";
				$vh->setResponse(array('message' => $message), 1);
			}
		}
		echo $vh->getResponse();
	}
	
	function updateVideo($id) {
		$vh = new VideoHandler();
		if(!isset($_POST["key"]) || !isset($_POST["name"]) || !isset($_POST["url"]) || !isset($_POST["type"])) {
				$message = "Missing fields";
				$vh->setResponse(array('message' => $message), 1);
		} else {
			if(checkKey($_POST["key"])) {
				$name = $_POST["name"];
				$url = $_POST["url"];
				$type = $_POST["type"];
				$vh->updateVideo($id, $name, $url, $type);
			}
			else {
				$message = "Invalid key";
				$vh->setResponse(array('message' => $message), 1);
			}
		}
		echo $vh->getResponse();
	}
	
	function getVideosForType($type) {
		$vh = new VideoHandler();
		$vh->getVideosForType($type);
		echo $vh->getResponse();
	}
	
	function getVideosForName($name) {
		$vh = new VideoHandler();
		$vh->getVideosForName($name);
		echo $vh->getResponse();
	}
	
	function deleteVideoForID($id) {
		$vh = new VideoHandler();
		if(!isset($_POST["key"])) {
			$message = "Missing fields";
			$vh->setResponse(array('message' => $message), 1);
		}
		else {
			if(checkKey($_POST["key"])) {
				$vh = new VideoHandler();
				$vh->deleteVideo($id);
			}
			else {
				$message = "Invalid key";
				$vh->setResponse(array('message' => $message), 1);
			}
		}
		echo $vh->getResponse();
	}
	
	function checkKey($key) {
		$localKey = "henkie";
		echo $key;
		if(strcmp($key, $localKey) == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
?>
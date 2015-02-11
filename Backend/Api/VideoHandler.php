<?php
	
	require_once("DatabaseConnector.php");
	require_once("AbstractResponse.php");
	
	class VideoHandler extends AbstractResponse {
		
		public function getVideos() {
			global $db;
			$query = $db->prepare("select * from videos");
			$query->setFetchMode(PDO::FETCH_ASSOC);
			$query->execute();
			$result = $query->fetchAll();
			
			$this->setResponse(array("videos" => $result), 0);
		}

		public function getVideoForID($id) {
			if(!empty($id)) {
				global $db;
				$query = $db->prepare("select * from videos where videoID = '$id' ");
				$query->setFetchMode(PDO::FETCH_ASSOC);
				$query->execute();
				$result = $query->fetch();
				
				$this->setResponse(array("video" => $result), 0);
			}
			else {
				$message = "Invalid ID";
				$this->setResponse(array("message" => $mesage), 1);
			}	
		}
	
		public function addVideo($name, $url, $type) {
			global $db;
			$db->beginTransaction();
			$query = $db->prepare("insert into videos (name, url, type) values ('$name', '$url', '$type')");
			$query->execute();
			if($db->commit()) {
				$message = "Video added";
				$this->setResponse(array("message" => $message), 0);
			}
			else {
				$message = "Failed to add video";
				$this->setResponse(array("message" => $message), 1);
			}
		}
		
		public function updateVideo($id, $name, $url, $type) {
			global $db;
			$db->beginTransaction();
			$query = $db->prepare("update videos set name='$name', url='$url', type='$type' where videoID='$id' ");
			$query->execute();
			if($db->commit()) {
				$message = "Video updated";
				$this->setResponse(array("message" => $message), 0);
			}
			else {
				$message = "Failed to update video";
				$this->setResponse(array("message" => $message), 1);
			}
		}
		
		public function getVideosForType($type) {
			if(!empty($type)) {
				global $db;
				$query = $db->prepare("select * from videos where type = '$type' ");
				$query->setFetchMode(PDO::FETCH_ASSOC);
				$query->execute();
				$result = $query->fetchAll();
				
				$this->setResponse(array("videos" => $result), 0);
			}
			else {
				$message = "Invalid type";
				$this->setResponse(array("message" => $message), 1);
			}
		}
		
		public function getVideosForName($name) {
			global $db;
			$db->quote($name);
			if(!empty($name)) {
				$query = $db->prepare("select * from videos where name = '$name' ");
				$query->setFetchMode(PDO::FETCH_ASSOC);
				$query->execute();
				$result = $query->fetchAll();
				
				$this->setResponse(array("videos" => $result), 0);
			}
			else {
				$message = "Invalid name";
				$this->setResponse(array("message" => $message), 1);
			}
		}
		
		public function deleteVideo($id) {
			if(!empty($id)) {
				global $db;
				$db->beginTransaction();
				$query = $db->prepare("delete from videos where videoID='$id' ");
				$query->execute();
				if($db->commit()) {
					$message = "Video deleted";
					$this->setResponse(array("message" => $message), 0);
				}
				else {
					$message = "Failed to delete video";
					$this->setResponse(array("message" => $message), 1);
				}
			}
			else {
				$message = "Invalid ID";
				$this->setResponse(array("message" => $message), 1);
			}
		}
	}

	
?>
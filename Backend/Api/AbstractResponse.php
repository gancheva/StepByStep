<?php
	abstract class AbstractResponse {
		private $response;
		
		public function getResponse() {
			if(!isset($this->response)) {
				$message = array('message' => 'No response set');
				$this->setResponse($message, 1);
			}
			return $this->response;
		}
		
		public function setResponse($response, $error) {
			if($error == 0) {
				$error = array('status' => 200, 'error' => 0);
			}
			else {
				$error = array('status' => 400, 'error' => 1);
			}
			$this->response = json_encode($error + $response);
		}
	}	
?>
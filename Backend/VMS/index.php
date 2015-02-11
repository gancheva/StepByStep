<?php
	# include this file at the very top of your script
	require_once('preheader.php');

	# the code for the class
	include ('ajaxCRUD.class.php');

	# this one line of code is how you implement the class
	$tblVideos = new ajaxCRUD("Videos",
								 "videos", "videoID");

	# don't show the primary key in the table
	$tblVideos->omitPrimaryKey();

	# my db fields all have prefixes;
	# display headers as reasonable titles
	$tblVideos->displayAs("name", "Name");
	$tblVideos->displayAs("url", "Video link");
	$tblVideos->displayAs("type", "Video Type");

	# add the filter box (above the table)
	$tblVideos->addAjaxFilterBox("name");

	# actually show to the table
	$tblVideos->showTable();

?>
package com.ovapp.stepbystep.test;

import android.test.ActivityInstrumentationTestCase2;

import com.StepByStep.Main.TravelByTrain;
import com.StepByStep.Main.TreinTutorialActivity;
import com.jayway.android.robotium.solo.Solo;

public class TrainTutorialTest extends
		ActivityInstrumentationTestCase2<TravelByTrain> {
	
	private Solo solo;
	public TrainTutorialTest() {
		super("com.StepByStep.Main.TravelByTrain", TravelByTrain.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testTrainTutorial() {
		solo.assertCurrentActivity("check on train tutorial menu", TravelByTrain.class);
		solo.clickInList(2);
		solo.assertCurrentActivity("Check on train tutorial running", TreinTutorialActivity.class);
		assertTrue(solo.searchText("Wat is uw bestemming?"));
		solo.clickOnImageButton(2);
		solo.sleep(1000);
		assertTrue(solo.searchText("Ook op het station"));
		solo.clickOnImageButton(2);
		solo.sleep(1000);
		assertTrue(solo.searchText("Inchecken of kaartje kopen")); 
		solo.clickOnImageButton(2);
		solo.sleep(1000);
		assertTrue(solo.searchText("De treinreis"));
		solo.clickOnImageButton(2);
		solo.sleep(1000);
		assertTrue(solo.searchText("Uitchecken"));
		solo.clickOnImageButton(2);
		solo.sleep(1000);
		assertTrue(solo.searchText("De terugreis"));
		solo.clickOnImageButton(0);
		assertTrue(solo.searchText("Reizen met de trein"));
	}

}

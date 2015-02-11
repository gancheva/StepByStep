package com.ovapp.stepbystep.test;

import android.test.ActivityInstrumentationTestCase2;

import com.StepByStep.Main.BusTutorialActivity;
import com.StepByStep.Main.Travelbybus;
import com.jayway.android.robotium.solo.Solo;

public class BusTutorialTest extends
		ActivityInstrumentationTestCase2<Travelbybus> {
	
	private Solo solo;
	public BusTutorialTest() {
		super("com.StepByStep.Main.Travelbybus", Travelbybus.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testBusTutorial() {
		solo.assertCurrentActivity("check on bus tutorial menu", Travelbybus.class);
		solo.clickInList(1);
		solo.assertCurrentActivity("Check on bus tutorial running", BusTutorialActivity.class);
		assertTrue(solo.searchText("bushalte?"));
		solo.scrollToSide(Solo.RIGHT);
		solo.sleep(1000);
		assertTrue(solo.searchText("Eurokaartje"));
		solo.scrollToSide(Solo.RIGHT);
		solo.sleep(1000);
		assertTrue(solo.searchText("Naar de bushalte")); 
		solo.clickOnImageButton(2);
		solo.sleep(1000);
		assertTrue(solo.searchText("Overstappen"));
		solo.scrollToSide(Solo.RIGHT);
		solo.sleep(1000);
		assertTrue(solo.searchText("De terugreis"));
		solo.clickOnImageButton(0);
		assertTrue(solo.searchText("5"));
	}

}

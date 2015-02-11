package com.ovapp.stepbystep.test;

import android.test.ActivityInstrumentationTestCase2;

import com.StepByStep.Main.Travelbybus;
import com.jayway.android.robotium.solo.Solo;
import com.ovapp.stepbystep.R;

public class DialogTest extends
		ActivityInstrumentationTestCase2<Travelbybus> {
	
	private Solo solo;
	public DialogTest() {
		super("com.StepByStep.Main.BusTutorialActivity", Travelbybus.class);
	}
	
	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}
	
	public void testBusDialog() {
		
		solo.assertCurrentActivity("check on bus tutorial menu", Travelbybus.class);
		solo.clickInList(1);
		solo.clickOnView(solo.getView(R.id.bus_dialog_internet));
		solo.waitForDialogToOpen();
		assertTrue(solo.searchText("Op de website van Qbuzz"));
		solo.clickOnView(solo.getView(R.id.exitDialogButton));
		solo.waitForDialogToClose();
		assertTrue(solo.searchText("Voordat u op reis gaat"));
	}

}

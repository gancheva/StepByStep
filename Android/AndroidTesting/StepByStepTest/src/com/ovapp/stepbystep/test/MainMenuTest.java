package com.ovapp.stepbystep.test;

import android.test.ActivityInstrumentationTestCase2;

import com.StepByStep.Main.ApplicationExplanation;
import com.StepByStep.Main.MainMenu;
import com.StepByStep.Main.OVchipkaart;
import com.StepByStep.Main.TravelByTrain;
import com.StepByStep.Main.Travelbybus;
import com.jayway.android.robotium.solo.Solo;

public class MainMenuTest extends ActivityInstrumentationTestCase2<MainMenu> {

	private Solo solo;
	/**
	 * 
	 */
	public MainMenuTest() {
		super("com.StepByStep.Main.MainMenu", MainMenu.class);
	}

	protected void setUp() throws Exception {
		super.setUp();
		solo = new Solo(getInstrumentation(), getActivity());
	}

	public void testMenu() {
		solo.clickInList(1);
		solo.assertCurrentActivity("Check on bus tutorial running", Travelbybus.class);
		solo.goBack();
		solo.clickInList(2);
		solo.assertCurrentActivity("Check on train tutorial running", TravelByTrain.class);
		solo.goBack();
		solo.clickInList(3);
		solo.assertCurrentActivity("Check on ovchipkaart tutorial running", OVchipkaart.class);
		solo.goBack();
		solo.clickInList(4);
		solo.assertCurrentActivity("Check on ovchipkaart tutorial running", ApplicationExplanation.class);
	}
}

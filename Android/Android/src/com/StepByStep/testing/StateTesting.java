/*package com.StepByStep.testing;

import junit.framework.Assert;
import android.test.ActivityInstrumentationTestCase2;

import com.StepByStep.Main.MainActivity;
import com.StepByStep.Main.OVChipKaartTutorialActivity;
import com.actionbarsherlock.app.SherlockActivity;
import com.jayway.android.robotium.solo.Solo;


public class StateTesting extends OVChipKaartTutorialActivity {

  private Solo solo;

  public StateTesting() {
    super();
  }

  public void setUp() throws Exception {
    solo = new Solo(getInstrumentation(), getActivity());
  }

  public void testPreferenceIsSaved() throws Exception {
    // check that we have the right activity
    solo.assertCurrentActivity("wrong activiy", SherlockActivity.class);

    
  }
  
  @Override
  public void tearDown() throws Exception {
    solo.finishOpenedActivities();
  }
}*/
package com.StepByStep.Main;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.ovapp.stepbystep.R;

public class OpenDialog {
	
	public OpenDialog(int layout, Context context) {
		final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(layout);
        dialog.show();
        ImageButton dialogButton = (ImageButton) dialog.findViewById(R.id.exitDialogButton);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new OnClickListener() {
                        @Override
                        public void onClick(View v) {
                                dialog.dismiss();
                        }
                });
	}
}

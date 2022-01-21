package com.liba_module_update.view;

import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.liba_module_update.R;

/**
 * <b>Create Date:</b> 16/9/20<br>
 * <b>Author:</b> ldc <br>
 * <b>Description:</b>
 * <br>
 */
public class DownLoadOptionDialog {


    public DownLoadOptionDialog(final IDialogCallBack dialogCallBack,
                                final Context context, String title,String displayText,
                                String negativeText, String postiveText) {

        final android.app.AlertDialog ad = new android.app.AlertDialog.Builder(context).create();
        ad.show();
        Window window = ad.getWindow();
        window.setContentView(R.layout.base_dialog);
        window.setBackgroundDrawableResource(android.R.color.transparent);

        TextView titleView = (TextView) window.findViewById(R.id.title);
        titleView.setText(title);

        TextView messageView = (TextView) window.findViewById(R.id.message);
        messageView.setText(displayText);

        TextView negativeButton = (TextView) window.findViewById(R.id.negativeButton);
        negativeButton.setText(negativeText);
        negativeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.dismiss();
                dialogCallBack.negativeFunction();
            }
        });

        TextView positiveButton = (TextView) window.findViewById(R.id.positiveButton);
        positiveButton.setText(postiveText);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.dismiss();
                dialogCallBack.positiveFunction();
            }
        });
    }
}

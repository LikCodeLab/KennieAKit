package com.want.base.sdk.framework.app.dialog;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.want.base.sdk.R;
import com.want.base.sdk.framework.Constants;

/**
 * <b>Project:</b> HollyWant<br>
 * <b>Create Date:</b> 15/9/30<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class LoadingDialogFragment extends BaseDialogFragment {


    private TextView mMessageText;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.sdk_dialog_loading, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDialog().supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        mMessageText = (TextView) view.findViewById(android.R.id.text1);
        final Bundle arguments = getArguments();
        if (null != arguments) {
            final String message = arguments.getString(Constants.Extras.DATA);
            mMessageText.setText(message);
        }
        setCancelable(false);
    }

//    public LoadingDialogFragment setMessage(int messageId) {
//        return setMessage(getString(messageId));
//    }

    public LoadingDialogFragment setMessage(String message) {
        if (isAdded() && null != mMessageText) {
            mMessageText.setText(message);
        } else {
            Bundle bundle = new Bundle();
            bundle.putString(Constants.Extras.DATA, message);
            setArguments(bundle);
        }
        return this;
    }
}

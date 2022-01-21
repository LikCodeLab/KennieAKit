package com.want.base.sdk.framework.ptr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * <b>Project:</b> hollywant<br>
 * <b>Create Date:</b> 16/1/28<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public interface IptrView extends IPtrable {

    View onCreateView(LayoutInflater inflater, View child, Bundle savedInstanceState);

    void onViewCreated(View view, Bundle savedInstanceState);
}

package com.leon.support.base.model.ptr;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 16/10/15<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 *
 * <br>
 */
public interface IptrView extends IPtrable {

    View onCreateView(LayoutInflater inflater, View child, Bundle savedInstanceState);

    void onViewCreated(View view, Bundle savedInstanceState);
}

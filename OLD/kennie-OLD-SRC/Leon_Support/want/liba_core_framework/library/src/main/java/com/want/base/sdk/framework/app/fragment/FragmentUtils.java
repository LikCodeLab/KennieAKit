package com.want.base.sdk.framework.app.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 16/5/12<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
public class FragmentUtils {

    private FragmentUtils() {
        // hide
    }

    /**
     * Restore all the fragment status after memory restored.
     *
     * @param fm  {@link FragmentManager}
     * @param tag target fragment tag that should show.
     */
    public static void restoreFragments(final FragmentManager fm, final String tag) {
        final List<Fragment> fragments = fm.getFragments();
        if (null == fragments || fragments.size() == 0) {
            return;
        }

        final FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment;
        for (int i = 0; i < fragments.size(); i++) {
            fragment = fragments.get(i);
            if (!fragment.getTag().equals(tag)) {
                ft.hide(fragment);
            }
        }
        ft.commit();
    }

}

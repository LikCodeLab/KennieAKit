package com.leon.project.ui;


import com.leon.support.base.model.ability.ToolbarAbility;

/**
 * <b>Project:</b> project_template<br>
 * <b>Create Date:</b> 16/3/21<br>
 * <b>Author:</b> leon<br>
 * <b>Description:</b>
 * 支持{@link android.support.v7.widget.Toolbar}的{@link android.support.v4.app.Fragment}
 * <br>
 */
public class PToolbarFragment extends PFragment {

    public PToolbarFragment() {
        addAbility(new ToolbarAbility(this));
    }
}

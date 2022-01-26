package com.kennie.base.project.config;

import com.kennie.base.project.interfaces.IGlobalConfig;

public class GlobalConfig implements IGlobalConfig {
    @Override
    public int getTitleHeight() {
        return 0;
    }

    @Override
    public int getTitleBackIcon() {
        return 0;
    }

    @Override
    public int getTitleBackground() {
        return 0;
    }

    @Override
    public int getTitleTextColor() {
        return 0;
    }

    @Override
    public boolean isShowTitleLine() {
        return true;
    }

    @Override
    public int getTitleLineColor() {
        return 0;
    }

    @Override
    public int getPageSize() {
        return 16;
    }

    @Override
    public int getStartPageIndex() {
        return 1;
    }
}

package com.leon.support.base.model.ability.core;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 16/10/15<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * Default ability manage implements.
 * <br>
 */
public class MDefaultAbilityAble implements IAbilityAble {

    private final List<IAbility> mAbilities = new ArrayList<>();


    @Override
    public void addAbility(IAbility ability) {
        synchronized (mAbilities) {
            this.mAbilities.add(ability);
        }
    }

    @Override
    public void removeAbility(IAbility ability) {
        synchronized (mAbilities) {
            if (mAbilities.contains(ability)) {
                mAbilities.remove(ability);
            }
        }
    }

    @Override
    public List<IAbility> getAllAbility() {
        return mAbilities;
    }

    @Override
    public void clearAbility() {
        synchronized (mAbilities) {
            mAbilities.clear();
        }
    }
}

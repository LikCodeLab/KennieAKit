package com.want.ability;

import java.util.ArrayList;
import java.util.List;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/1/27<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Default ability manage implements.
 * <br>
 */
public class DefaultAbilitable implements IAbilitable {

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

package com.leon.support.base.model.ability.core;

import java.util.List;


/**
 * <b>Project:</b> liba_support<br>
 * <b>Create Date:</b> 16/10/15<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * Ability manage interface
 * <br>
 */
public interface IAbilityAble {

    /**
     * Add an ability
     *
     * @param ability {@link IAbility}
     */
    void addAbility(IAbility ability);

    /**
     * Remove an ability
     *
     * @param ability {@link IAbility}
     */
    void removeAbility(IAbility ability);

    /**
     * Get the all ability.
     *
     * @return
     */
    List<IAbility> getAllAbility();

    /**
     * Remove all ability
     */
    void clearAbility();
}

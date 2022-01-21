package com.want.ability;

import java.util.List;

/**
 * <b>Project:</b> android<br>
 * <b>Create Date:</b> 16/1/27<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * Ability manage interface
 * <br>
 */
public interface IAbilitable {

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

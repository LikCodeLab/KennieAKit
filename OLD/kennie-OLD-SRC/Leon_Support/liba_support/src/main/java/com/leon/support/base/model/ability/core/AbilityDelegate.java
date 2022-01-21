package com.leon.support.base.model.ability.core;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.List;

/**
 * <b>Project:</b> LibA_Support<br>
 * <b>Create Date:</b> 16/10/15<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 * Delegatr for ability manage.
 * <br>
 */
public class AbilityDelegate implements IAbilityAble, IAbility {

    private IAbilityAble mAbilitable;

    public AbilityDelegate() {
        this(new MDefaultAbilityAble());
    }

    public AbilityDelegate(IAbilityAble abilitable) {
        this.mAbilitable = abilitable;
    }

    @Override
    public void addAbility(IAbility ability) {
        mAbilitable.addAbility(ability);
    }

    @Override
    public void removeAbility(IAbility ability) {
        mAbilitable.removeAbility(ability);
    }

    @Override
    public List<IAbility> getAllAbility() {
        return mAbilitable.getAllAbility();
    }

    @Override
    public void clearAbility() {
        mAbilitable.clearAbility();
    }

    /**
     * {@link Activity#onCreate(Bundle)}、{@link Fragment#onCreate(Bundle)}
     *
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        IAbility ability;
        final List<IAbility> abilities = mAbilitable.getAllAbility();
        for (int i = 0; i < abilities.size(); i++) {
            ability = abilities.get(i);
            ability.onCreate(savedInstanceState);
        }
    }

    /**
     * {@link Activity#onAttachedToWindow()}、{@link Fragment#onAttach(Context context)}
     */
    @Override
    public void onAttach() {
        IAbility ability;
        final List<IAbility> abilities = mAbilitable.getAllAbility();
        for (int i = 0; i < abilities.size(); i++) {
            ability = abilities.get(i);
            ability.onAttach();
        }
    }

    /**
     * {@link Activity#onDetachedFromWindow()}、{@link Fragment#onDetach()}
     */
    @Override
    public void onDetach() {
        IAbility ability;
        final List<IAbility> abilities = mAbilitable.getAllAbility();
        for (int i = 0; i < abilities.size(); i++) {
            ability = abilities.get(i);
            ability.onDetach();
        }
    }

    /**
     * {@link Activity#onStart()}、{@link Fragment#onStart()}
     */
    @Override
    public void onStart() {
        IAbility ability;
        final List<IAbility> abilities = mAbilitable.getAllAbility();
        for (int i = 0; i < abilities.size(); i++) {
            ability = abilities.get(i);
            ability.onStart();
        }
    }

    /**
     * {@link Activity#onResume()}、{@link Fragment#onResume()}
     */
    @Override
    public void onResume() {
        IAbility ability;
        final List<IAbility> abilities = mAbilitable.getAllAbility();
        for (int i = 0; i < abilities.size(); i++) {
            ability = abilities.get(i);
            ability.onResume();
        }
    }

    /**
     * {@link Activity#onPause()}、{@link Fragment#onPause()}
     */
    @Override
    public void onPause() {
        IAbility ability;
        final List<IAbility> abilities = mAbilitable.getAllAbility();
        for (int i = 0; i < abilities.size(); i++) {
            ability = abilities.get(i);
            ability.onPause();
        }
    }

    /**
     * {@link Activity#onSaveInstanceState(Bundle)}、{@link Fragment#onSaveInstanceState(Bundle)}
     *
     * @param outState
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        IAbility ability;
        final List<IAbility> abilities = mAbilitable.getAllAbility();
        for (int i = 0; i < abilities.size(); i++) {
            ability = abilities.get(i);
            ability.onSaveInstanceState(outState);
        }
    }

    /**
     * {@link Activity#onPostCreate(Bundle)}
     *
     * @param savedInstanceState
     */
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        IAbility ability;
        final List<IAbility> abilities = mAbilitable.getAllAbility();
        for (int i = 0; i < abilities.size(); i++) {
            ability = abilities.get(i);
            ability.onPostCreate(savedInstanceState);
        }
    }

    /**
     * {@link Activity#onStop()}、{@link Fragment#onStop()}
     */
    @Override
    public void onStop() {
        IAbility ability;
        final List<IAbility> abilities = mAbilitable.getAllAbility();
        for (int i = 0; i < abilities.size(); i++) {
            ability = abilities.get(i);
            ability.onStop();
        }
    }

    /**
     * {@link Activity#onDestroy()}、{@link Fragment#onDestroy()}
     */
    @Override
    public void onDestroy() {
        IAbility ability;
        final List<IAbility> abilities = mAbilitable.getAllAbility();
        for (int i = 0; i < abilities.size(); i++) {
            ability = abilities.get(i);
            ability.onDestroy();
        }
    }

    /**
     * {@link Fragment#onHiddenChanged(boolean)}
     *
     * @param hidden
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        IAbility ability;
        final List<IAbility> abilities = mAbilitable.getAllAbility();
        for (int i = 0; i < abilities.size(); i++) {
            ability = abilities.get(i);
            ability.onHiddenChanged(hidden);
        }
    }
}

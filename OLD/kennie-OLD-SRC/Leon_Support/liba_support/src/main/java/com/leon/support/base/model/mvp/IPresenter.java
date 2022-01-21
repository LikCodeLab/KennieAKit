package com.leon.support.base.model.mvp;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 16/5/9<br>
 * <b>Author:</b> leon<br>
 * <b>Description:</b>
 * Base MVP Presenter.
 * <br>
 */
public interface IPresenter {

    void onCreate();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();


}

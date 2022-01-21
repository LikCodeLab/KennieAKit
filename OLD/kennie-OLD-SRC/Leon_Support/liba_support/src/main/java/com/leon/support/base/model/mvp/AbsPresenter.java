package com.leon.support.base.model.mvp;

/**
 * <b>Project:</b> project_hotclub<br>
 * <b>Create Date:</b> 16/5/9<br>
 * <b>Author:</b> leon<br>
 * <b>Description:</b>
 * Presenter that implement empty life cycle method.
 * <br>
 */
public abstract class AbsPresenter implements IPresenter {

    private IView mView;

    @SuppressWarnings("unchecked")
    public AbsPresenter(IView view) {
        this.mView = view;
        view.setPresenter(this);
    }

    public AbsPresenter() {

    }

    @SuppressWarnings("unchecked")
    protected <T> T getView() {
        return (T) mView;
    }

    @Override
    public void onCreate() {
        // empty
    }

    @Override
    public void onStart() {
        // empty
    }

    @Override
    public void onResume() {
        // empty
    }

    @Override
    public void onPause() {
        // empty
    }

    @Override
    public void onStop() {
        // empty
    }

    @Override
    public void onDestroy() {
        // empty
    }
}

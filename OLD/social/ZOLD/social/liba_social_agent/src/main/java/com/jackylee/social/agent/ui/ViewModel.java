package com.jackylee.social.agent.ui;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.widget.TextView;

import com.jackylee.social.agent.IPlatformProxy;
import com.jackylee.social.core.Configuration;
import com.jackylee.social.core.PlatformFactory;
import com.jackylee.social.core.SocialAgent;
import com.jackylee.social.core.platform.IPlatform;
import com.jackylee.social.core.shareable.ShareActionListener;
import com.jackylee.social.core.shareable.ShareParams;
import com.jackylee.social.core.shareable.SimpleShareListener;


/**
 * <b>Project:</b> liba_social_agent<br>
 * <b>Create Date:</b> 16/9/19<br>
 * <b>Author:</b> LearKoo<br>
 * <b>Description:</b>
 *
 * <br>
 */
public class ViewModel extends BaseObservable {

    private DialogFragment mFragment;
    private Context mContext;
    private Configuration mConfiguration;
    private IPlatformProxy mPlatformProxy;
    private ShareParams mShareParams;
    private ShareActionListener mShareActionListener;

    public ViewModel(DialogFragment fragment,
                     Configuration configuration,
                     IPlatformProxy proxy,
                     ShareParams params,
                     ShareActionListener listener) {
        this.mFragment = fragment;
        this.mContext = mFragment.getActivity();
        this.mConfiguration = configuration;
        this.mPlatformProxy = proxy;
        this.mShareParams = params;
        this.mShareActionListener = listener;
    }

    public void onClick(View view) {
        final String name = mConfiguration.getPlatform();
        IPlatform platform = PlatformFactory.create(mContext, IPlatform.Name.nameOf(name));

        if (null == platform && null != mPlatformProxy) {
            platform = mPlatformProxy.onCreatePlatform(mConfiguration);
        }

        final ShareActionListener listener =
                null != mShareActionListener ? mShareActionListener
                                             : new SimpleShareListener(mContext);
        SocialAgent.newInstance()
                   .platform(platform)
                   .shareParams(mShareParams)
                   .shareActionListener(listener)
                   .share();
        mFragment.dismiss();
    }

    @Bindable
    public int getItemImage() {
        return mContext.getResources()
                       .getIdentifier("social_platform_" + mConfiguration.getPlatform(),
                               "drawable",
                                      mContext.getPackageName());
    }

    @Bindable
    public int getItemName() {
        return mContext.getResources()
                       .getIdentifier(mConfiguration.getPlatform(),
                                      "string",
                                      mContext.getPackageName());
    }


    @BindingAdapter(value = {"itemText"}, requireAll = false)
    public static void itemName(TextView view, int res) {
        view.setText(res);
    }

    @BindingAdapter(value = {"itemImage"}, requireAll = false)
    public static void itemImage(final TextView view, int res) {
        final Context context = view.getContext();
        final Drawable drawable = ResourcesCompat.getDrawable(context.getResources(),
                                                              res,
                                                              context.getTheme());
        view.post(new Runnable() {
            @Override
            public void run() {
                final int width = (int) (view.getWidth() * 0.6f + 0.5f);
                drawable.setBounds(0, 0, width, width);
                view.setCompoundDrawables(null, drawable, null, null);
            }
        });
    }
}

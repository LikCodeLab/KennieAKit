package com.want.base.sdk.framework.app.databinding;

import android.database.DataSetObserver;
import android.databinding.BindingAdapter;
import android.databinding.BindingMethod;
import android.databinding.BindingMethods;
import android.databinding.InverseBindingListener;
import android.databinding.InverseBindingMethod;
import android.databinding.InverseBindingMethods;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.want.base.sdk.R;
import com.want.base.sdk.widget.MViewPager;
import com.want.base.sdk.widget.QuantityPickerView;
import com.want.core.log.lg;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * <b>Project:</b> project_vmc<br>
 * <b>Create Date:</b> 8/10/16<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b> <br>
 */
@BindingMethods(value = {
        @BindingMethod(type = QuantityPickerView.class,
                       attribute = "onQuantityPicker",
                       method = "setOnQuantityPickerListener"),
})
@InverseBindingMethods(
        {
                @InverseBindingMethod(type = QuantityPickerView.class,
                                      attribute = "quantity"),
        }
)
@SuppressWarnings("unused")
public class DataBindingAdapter {
    private static final boolean DEBUG = false;
    private static final String TAG = "DataBindAdapter";

    /**
     * 格式化日期, 默认格式: yyyy-MM-dd HH:mm:ss
     *
     * @param textView textview
     * @param times    date
     * @param format   format
     */
    @BindingAdapter(value = {"date", "format"}, requireAll = false)
    public static void date(TextView textView, long times, String format) {
        if (TextUtils.isEmpty(format)) {
            format = "yyyy-MM-dd HH:mm:ss";
        }

        if (0L == times) {
            times = System.currentTimeMillis();
        }
        textView.setText(new SimpleDateFormat(format, Locale.getDefault()).format(new Date(times)));
    }

    ///////////////////////////////////////////////////////////////////////////
    // QuantityPickerView
    ///////////////////////////////////////////////////////////////////////////

    @BindingAdapter(value = {"quantity"}, requireAll = false)
    @SuppressWarnings("unused")
    public static void setQuantity(QuantityPickerView view, float quantity) {
        if (view.getQuantity() != quantity) {
            view.setQuantity(quantity);
        }
    }

    @BindingAdapter(value = {"onQuantityPicker", "quantityAttrChanged"}, requireAll = false)
    @SuppressWarnings("unused")
    public static void setOnQuantityChangedListener(QuantityPickerView view,
                                                    final QuantityPickerView.OnQuantityPickerListener listener,
                                                    final InverseBindingListener bindingListener) {
        if (null == bindingListener) {
            view.setOnQuantityPickerListener(listener);
        } else {
            view.setOnQuantityPickerListener(new QuantityPickerView.OnQuantityPickerListener() {
                @Override
                public void onQuantityPicked(float quantity, String formatedValue) {
                    if (null != listener) {
                        listener.onQuantityPicked(quantity, formatedValue);
                    }
                    bindingListener.onChange();
                }
            });
        }
    }

    ///////////////////////////////////////////////////////////////////////////
    // Android widgets
    ///////////////////////////////////////////////////////////////////////////

    /**
     * ImageView设置int类型的图片资源
     *
     * @param image imageview
     * @param src   int src
     */
    @BindingAdapter(value = {"imageSrc"})
    @SuppressWarnings("unused")
    public static void setImageResource(ImageView image, int src) {
        image.setImageResource(src);
    }

    /**
     * 防止View被快速点击
     *
     * @param view     View
     * @param listener click listener
     */
    @BindingAdapter(value = {"onClick"})
    @SuppressWarnings("unused")
    public static void setOnClickListener(View view, final View.OnClickListener listener) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if (null != listener) {
                    listener.onClick(v);
                }

                // 防止快速点击
                v.setClickable(false);
                v.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        v.setClickable(true);
                    }
                }, 300);
            }
        });
    }


    private static final long DEFAULT_DELAY = 3200;

    /**
     * ViewPager自动滚动
     *
     * @param viewPager viewpager
     * @param auto      'autoScroll'
     * @param delay     'scrollDelay'
     */
    @BindingAdapter(value = {"autoScroll", "scrollDelay"}, requireAll = false)
    @SuppressWarnings("unused")
    public static void setViewPagerAutoScroll(final ViewPager viewPager, boolean auto, long delay) {
        if (!(viewPager instanceof MViewPager)) {
            lg.w(TAG, "only 'MViewPager' is supported.");
            return;
        }

        if (null != viewPager.getTag(R.id.viewpager)) {
            lg.w(TAG, "already setup.");
            return;
        }
        viewPager.setTag(R.id.viewpager, true);

        if (0 == delay) {
            delay = DEFAULT_DELAY;
        }

        if (DEBUG) {
            lg.v(TAG, "auto scroll start, " + "viewpager: " + viewPager);
        }

        final long scrollDelay = delay;
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                viewPager.removeCallbacks(this);

                if (DEBUG) {
                    lg.v(TAG, "begin auto scroll");
                }
                final PagerAdapter adapter = viewPager.getAdapter();
                if (null == adapter) {
                    lg.d(TAG, "adapter is null");
                    return;
                }

                final int count = adapter.getCount();
                if (1 >= count) {
//                    viewPager.postDelayed(this, scrollDelay);
                    lg.d(TAG, "count is less than 1, skip scroll");
                    return;
                }

                final int current = viewPager.getCurrentItem();
                int next = current + 1;
                if (next >= count) {
                    next = 0;
                }
                viewPager.setCurrentItem(next, true);

                viewPager.postDelayed(this, scrollDelay);
                if (DEBUG) {
                    lg.v(TAG, "end auto scroll");
                }
            }
        };

        viewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
                if (DEBUG) {
                    lg.v(TAG, "page scroll state changed, state: " + state);
                }

                if (ViewPager.SCROLL_STATE_IDLE == state) {
                    viewPager.postDelayed(runnable, scrollDelay);
                } else {
                    viewPager.removeCallbacks(runnable);
                }
            }
        });

        viewPager.addOnAdapterChangeListener(new ViewPager.OnAdapterChangeListener() {
            @Override
            public void onAdapterChanged(@NonNull final ViewPager viewPager,
                                         @Nullable PagerAdapter oldAdapter,
                                         @Nullable final PagerAdapter newAdapter) {
                if (DEBUG) {
                    lg.v(TAG, "adapter changed");
                }

                if (null != newAdapter) {
                    viewPager.postDelayed(runnable, scrollDelay);
                    newAdapter.registerDataSetObserver(new DataSetObserver() {
                        @Override
                        public void onChanged() {
                            super.onChanged();
                            checkAndPost();
                        }

                        @Override
                        public void onInvalidated() {
                            super.onInvalidated();
                            checkAndPost();
                        }

                        private void checkAndPost() {
                            final int count = newAdapter.getCount();
                            if (DEBUG) {
                                lg.v(TAG, "dataset changed, post runnable.");
                            }
                            if (count > 1) {
                                viewPager.postDelayed(runnable, scrollDelay);
                            }
                        }
                    });
                }
            }
        });

        ((MViewPager) viewPager).setOnVisibilityChangedListener(new MViewPager.OnVisibilityChangedListener() {
            @Override
            public void onVisibilityChanged(int visibility) {
                final boolean visible = View.VISIBLE == visibility;
                lg.d(TAG, "attached ? " + visible);

                viewPager.removeCallbacks(runnable);
                if (visible) {
                    viewPager.postDelayed(runnable, scrollDelay);
                }
            }
        });
    }
}

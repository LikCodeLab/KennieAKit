package com.kennie.views.search;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.kennie.widgets.R;


/**
 * 自定义搜索控件SearchView
 */
public class SearchBoxView extends LinearLayout implements ISearchBoxView {


    private TextWatcher textWatcher;
    private OnSearchTextViewClickListener onSearchTextViewClickListener;
    private OnImageViewClickListener onOnClearImageViewClickListener;


    private AppCompatEditText mSearchBoxEt; // 搜索框
    private AppCompatImageView mSearchClearIv; //清除图标
    private AppCompatTextView mSearchTv; // 搜索

    private String hint; // 输入框的hint提示
    private boolean enableAutoHideSoftKey; // 点击搜索后是否自动隐藏虚拟键盘
    private boolean enableClearFocusAfterSearch;  // 点击了搜索(不是搜索图标)后是否清除EditText的焦点

    public SearchBoxView(Context context) {
        this(context, null);
    }

    public SearchBoxView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }


    public SearchBoxView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTextWatcher();
        initViews();
        initParams(context, attrs);
    }

    private void initViews() {
        View rootView = LayoutInflater.from(getContext()).inflate(R.layout._widget_searchbox_layout, this);
        mSearchBoxEt = rootView.findViewById(R.id.et_search_box);
        mSearchClearIv = rootView.findViewById(R.id.iv_clear_all);
        mSearchTv = rootView.findViewById(R.id.tv_search);


        mSearchBoxEt.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                mSearchBoxEt.setHint("");
                    if (mSearchBoxEt.getText().length() > 0) {
                        mSearchClearIv.setVisibility(View.VISIBLE);
                    } else {
                        mSearchClearIv.setSelected(false);
                        mSearchClearIv.setVisibility(View.GONE);
                    }
                } else {
                    if (TextUtils.isEmpty(hint)) {
                        mSearchBoxEt.setHint(R.string._library_select_layout_search_hint_text);
                    } else {
                        mSearchBoxEt.setHint(hint);
                    }
                }
            }
        });

        if (textWatcher != null) {
            mSearchBoxEt.addTextChangedListener(textWatcher);
        }
        // 清除
        mSearchClearIv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mSearchBoxEt.setText("");
                if (onOnClearImageViewClickListener != null) {
                    onOnClearImageViewClickListener.onImageViewClick(mSearchBoxEt, mSearchClearIv);
                }
            }
        });

        //搜索
        mSearchTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enableClearFocusAfterSearch) {
                    mSearchBoxEt.clearFocus();
                }
                Context context = getContext();
                if (context != null && context instanceof Activity && enableAutoHideSoftKey) {
                    //收起软键盘
                    Activity activity = (Activity) context;
                    InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                    if (imm != null && activity.getCurrentFocus() != null) {
                        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0); //强制隐藏键盘
                    }
                }

                if (onSearchTextViewClickListener != null) {
                    onSearchTextViewClickListener.onSearchClick(mSearchBoxEt, getSearchContent());
                }
            }
        });


    }

    /**
     * 初始化自定义属性
     *
     * @param context 上下文对象
     * @param attrs   属性集
     */
    private void initParams(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.SearchBoxView);
        if (null != typedArray) {
            // 输入框的hint属性
            String hint = typedArray.getString(R.styleable.SearchBoxView_sbv_hint);
            if (!TextUtils.isEmpty(hint)) mSearchBoxEt.setHint(hint);

            // 清空图标
            Drawable clearIcon = typedArray.getDrawable(R.styleable.SearchBoxView_sbv_ClearIcon);
            if (null != clearIcon) mSearchClearIv.setImageDrawable(clearIcon);

            // 点击搜索后是否自动隐藏虚拟键盘
            this.enableAutoHideSoftKey = typedArray.getBoolean(R.styleable.SearchBoxView_sbv_EnableAutoHideSoftKey, true);

            // 点击了清除按钮后是否清除EditText的焦点
            this.enableClearFocusAfterSearch = typedArray.getBoolean(R.styleable.SearchBoxView_sbv_EnableClearFocusAfterSearch, true);

            typedArray.recycle();
        }
    }


    /**
     * "搜索"点击事件
     *
     * @param listener 回调接口listener
     */
    @Override
    public void setOnSearchTextViewClickListener(OnSearchTextViewClickListener listener) {
        this.onSearchTextViewClickListener = listener;
    }

    /**
     * 清除图标点击事件
     *
     * @param listener 回调接口listener
     */
    @Override
    public void setOnClearImageViewClickListener(OnImageViewClickListener listener) {
        this.onOnClearImageViewClickListener = listener;
    }


    /**
     * 设置点击搜索时是否自动隐藏虚拟键盘
     *
     * @param enableAutoHideSoftKey 点击搜索时是否自动隐藏虚拟键盘
     */
    public void setEnableAutoHideSoftKey(boolean enableAutoHideSoftKey) {
        this.enableAutoHideSoftKey = enableAutoHideSoftKey;
    }

    /**
     * 点击了搜索(不是搜索图标)后是否清除EditText的焦点
     *
     * @param enableClearFocusAfterSearch 点击了搜索(不是搜索图标)后是否清除EditText的焦点
     */
    public void setEnableClearFocusAfterSearch(boolean enableClearFocusAfterSearch) {
        this.enableClearFocusAfterSearch = enableClearFocusAfterSearch;
    }

    /**
     * 获取EditText的内容
     *
     * @return EditText的内容
     */
    @Override
    public String getSearchContent() {
        return mSearchBoxEt.getText().toString().trim();
    }

    /**
     * 设置EditText的内容
     *
     * @param content content
     */
    @Override
    public void setSearchContent(String content) {
        mSearchBoxEt.setText(content);
    }

    @Override
    public EditText getEditText() {
        return mSearchBoxEt;
    }


    /**
     * 初始化textWatcher
     */
    private void initTextWatcher() {
        textWatcher = new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int length = s.length();
                if (length > 0 && mSearchBoxEt.isFocused()) {
                    mSearchClearIv.setVisibility(View.VISIBLE);
                } else {
                    mSearchClearIv.setVisibility(View.GONE);

                }
            }
        };
    }
}

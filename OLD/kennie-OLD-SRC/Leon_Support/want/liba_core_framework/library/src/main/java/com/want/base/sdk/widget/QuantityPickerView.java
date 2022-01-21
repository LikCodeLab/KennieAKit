package com.want.base.sdk.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.want.base.sdk.R;

import java.math.BigDecimal;
import java.math.RoundingMode;


/**
 * <b>Project:</b> HollyWant<br>
 * <b>Create Date:</b> 15/10/14<br>
 * <b>Author:</b> Gordon<br>
 * <b>Description:</b>
 * 自定义数量选择器
 * <br>
 */
@SuppressWarnings("unused")
public class QuantityPickerView extends LinearLayout implements View.OnClickListener {
    private static final float DEFAULT_INIT = 1f;
    private static final float DEFAULT_MINIMUM = 0f;
    private static final float DEFAULT_MAX = Integer.MAX_VALUE;
    private static final float DEFAULT_INCREMENT = 1f;

    /**
     * 选择的数量发生变化回调
     */
    public interface OnQuantityPickerListener {

        /**
         * 选择的数量发生变化时回调。
         *
         * @param quantity      选择的数量
         * @param formatedValue
         */
        void onQuantityPicked(float quantity, String formatedValue);
    }

    private TextView mReduceText;
    private EditText mQuantityEdit;
    private TextView mIncreaseText;

    private float mInitCount;
    private float mMinimumNumber = DEFAULT_MINIMUM;
    private float mMaxNumber = DEFAULT_MAX;
    private OnQuantityPickerListener mOnQuantityPickerListener;
    private float mQuantity;
    private boolean isSettingQuantity = false;
    private float mIncrement = DEFAULT_INCREMENT;

    private Runnable mTextChangedCallback;


    public QuantityPickerView(Context context) {
        this(context, null);
    }

    public QuantityPickerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public QuantityPickerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs,
                                                      R.styleable.QuantityPickerView,
                                                      defStyleAttr,
                                                      0);
        mInitCount = a.getFloat(R.styleable.QuantityPickerView_initCount, DEFAULT_INIT);
        mMinimumNumber = a.getFloat(R.styleable.QuantityPickerView_minimum, DEFAULT_MINIMUM);
        mMaxNumber = a.getFloat(R.styleable.QuantityPickerView_max, DEFAULT_MAX);
        a.recycle();
        mQuantity = mInitCount;
        inflate(context, R.layout.sdk_quantity_picker_layout, this);
    }

    public void setOnQuantityPickerListener(OnQuantityPickerListener listener) {
        this.mOnQuantityPickerListener = listener;
    }

    /**
     * 获取初始化的数量
     *
     * @return
     */
    public float getInitNumber() {
        return this.mInitCount;
    }

    /**
     * 设置初始化的数量
     *
     * @param init
     */
    public void setInitNumber(float init) {
        this.mInitCount = init;
        setQuantity(init);
    }

    /**
     * 获取允许的最小数量
     *
     * @return
     */
    public float getMinimumNumber() {
        return this.mMinimumNumber;
    }

    /**
     * 设置允许的最小数量
     *
     * @param minimum
     */
    public void setMinimum(float minimum) {
        this.mMinimumNumber = minimum;
    }

    /**
     * 获取允许的最大数量
     *
     * @return
     */
    public float getMaxNumber() {
        return this.mMaxNumber;
    }

    /**
     * 设置允许的最大树立数量
     *
     * @param max
     */
    public void setMaxNumber(float max) {
        this.mMaxNumber = max;
    }


    /**
     * 获取数量增加、减少的增量
     *
     * @return
     */
    public float getIncrement() {
        return this.mIncrement;
    }

    /**
     * 设置数量增加、减少的增量
     *
     * @param increment
     */
    public void setIncrement(float increment) {
        this.mIncrement = increment;
    }

    /**
     * 获取选择的数量
     *
     * @return
     */
    public float getQuantity() {
        return mQuantity;
    }

    /**
     * 设置选择数量
     *
     * @param quantity 数量
     */
    public void setQuantity(float quantity) {
        isSettingQuantity = true;

        BigDecimal number = new BigDecimal(quantity);
        final String formatedValue;
        if (number.intValue() == number.floatValue()) {
            formatedValue = String.valueOf(number.intValue());
        } else {
            formatedValue = String.valueOf(number.floatValue());
        }

        mQuantityEdit.setText(formatedValue);
        mReduceText.setEnabled(quantity > mMinimumNumber);
        mIncreaseText.setEnabled(quantity < mMaxNumber);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        this.mReduceText = (TextView) findViewById(R.id.quantity_picker_reduce_text);
        this.mQuantityEdit = (EditText) findViewById(R.id.quantity_picker_quantity_edit);
        this.mIncreaseText = (TextView) findViewById(R.id.quantity_picker_increase_text);

        mQuantityEdit.setText(getFormatedValue(mInitCount));
        //光标末尾
        mQuantityEdit.postInvalidate();
        CharSequence charSequence = mQuantityEdit.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
        this.mReduceText.setOnClickListener(this);
        this.mIncreaseText.setOnClickListener(this);
        mQuantityEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (null != mTextChangedCallback) {
                    removeCallbacks(mTextChangedCallback);
                }

                if (isSettingQuantity) {
                    isSettingQuantity = false;
                    mQuantity = new BigDecimal(s.toString()).floatValue();
                    return;
                }

                postDelayed(mTextChangedCallback = new TextChangedCallback(s.toString()), 500);
            }
        });
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        final int id = v.getId();

        final BigDecimal number = new BigDecimal(mQuantityEdit.getText().toString());
        final BigDecimal increment = new BigDecimal(String.valueOf(mIncrement));
        if (R.id.quantity_picker_reduce_text == id) {
            float quantity = number.subtract(increment).floatValue();
            if (quantity <= mMinimumNumber) {
                quantity = mMinimumNumber;
                mReduceText.setEnabled(false);
            }
            mIncreaseText.setEnabled(true);
            mQuantityEdit.setText(getFormatedValue(quantity));
        } else if (R.id.quantity_picker_increase_text == id) {
            float quantity = number.add(increment).floatValue();
            if (quantity >= mMaxNumber) {
                quantity = mMaxNumber;
                mIncreaseText.setEnabled(false);
            }
            mReduceText.setEnabled(true);
            mQuantityEdit.setText(getFormatedValue(quantity));
        }
    }

    private String getFormatedValue(float num) {
        BigDecimal number = new BigDecimal(num);
        return number.floatValue() == number.intValue()
               ? String.valueOf(number.intValue())
               : String.valueOf(number.floatValue());
    }

    private class TextChangedCallback implements Runnable {

        private String mNumber;

        TextChangedCallback(String number) {
            this.mNumber = number;
        }

        /**
         * Starts executing the active part of the class' code. This method is
         * called when a thread is started that has been created with a class which
         * implements {@code Runnable}.
         */
        @Override
        public void run() {
            final float quantity;
            if (TextUtils.isEmpty(mNumber)) {
                quantity = mMinimumNumber;
                mQuantityEdit.setText(getFormatedValue(quantity));
            } else {
                BigDecimal data = new BigDecimal(mNumber);
                data = data.divide(new BigDecimal(mIncrement), 2, RoundingMode.HALF_EVEN);
                if (data.intValue() != data.floatValue()) {
                    setQuantity(mQuantity);
                    Toast.makeText(getContext(),
                                   String.format("输入的数量必须是%.1f的倍数", mIncrement),
                                   Toast.LENGTH_SHORT).show();
                    return;
                }

                final float number = Float.valueOf(mNumber);
                mReduceText.setEnabled(number > mMinimumNumber);
                mIncreaseText.setEnabled(number < mMaxNumber);

                if (number > mMaxNumber) {
                    quantity = mMaxNumber;
                    mQuantityEdit.setText(getFormatedValue(quantity));
                } else if (number < mMinimumNumber) {
                    quantity = mMinimumNumber;
                    mQuantityEdit.setText(getFormatedValue(quantity));
                } else {
                    quantity = number;
                }
            }

            mQuantity = quantity;

            if (null != mOnQuantityPickerListener) {
                mOnQuantityPickerListener.onQuantityPicked(mQuantity, getFormatedValue(quantity));
            }
        }
    }

    /*设置加减按钮是否可点击*/
    public void setViewEnable(boolean isEnable) {
        if (isEnable) {
            mIncreaseText.setEnabled(true);
            mQuantityEdit.setEnabled(true);
            if (Float.parseFloat(mQuantityEdit.getText().toString().trim()) >= mIncrement) {
                mReduceText.setEnabled(true);
            }
        } else {
            mIncreaseText.setEnabled(false);
            mReduceText.setEnabled(false);
            mQuantityEdit.setEnabled(false);
        }

    }
}

package com.kennie.views.badge;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * Author：Kennie
 * Project：KennieWidget
 * Class：BadgeImageView
 * Date：2021/12/12 23:15
 * Desc：带角标的ImageView
 */
public class BadgeImageView extends AppCompatImageView implements IBadge {
    private final Badge mBadge;

    public BadgeImageView(Context context) {
        super(context);
        mBadge = new Badge(this, null);
    }

    public BadgeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mBadge = new Badge(this, attrs);
    }

    public BadgeImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBadge = new Badge(this, attrs);
    }

    @Override
    public void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        mBadge.drawBadge(canvas);
    }

    @Override
    public Badge getBadge() {
        return mBadge;
    }

}

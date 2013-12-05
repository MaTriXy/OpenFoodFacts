package com.flavienlaurent.openfoodfacts.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.RotateDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.flavienlaurent.openfoodfacts.R;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorListenerAdapter;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;
import com.nineoldandroids.view.ViewHelper;

public class ExpandableView extends LinearLayout {

    public static final int MAX_LEVEL = 10000;
    private TextView mHandle;
    private View mContent;

    private boolean mPendingToggle;

    private boolean mExpanded = false;
    private int mContentHeight;
    private CharSequence mTitle;

    private RotateDrawable mExpandCollapseDrawable;

    private int mHeaderHeight;

    private OnToggleListener mOnToggleListener = new DefaultOnToggleListener();

    public ExpandableView(Context context) {
        this(context, null);
    }

    public ExpandableView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setOrientation(VERTICAL);
        readStyles(context, attrs);
        readDimens(context);

        initDrawables();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mContent = getChildAt(0);
        if (mContent == null || getChildCount() != 1) {
            throw new IllegalArgumentException("Content missing. Only one child.");
        }
        setupView();
        registerToggler();
    }

    private void initDrawables() {
        mExpandCollapseDrawable = (RotateDrawable) getResources().getDrawable(R.drawable.rotate_ic_navigation_expand);
    }

    private void readDimens(Context context) {
        mHeaderHeight = context.getResources().getDimensionPixelSize(R.dimen.expandable_view_header_height);
    }

    private void readStyles(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ExpandableView, 0, 0);

        mTitle = a.getText(R.styleable.ExpandableView_handleTitle);

        a.recycle();
    }

    private void setupView() {
        mHandle = buildHandleView();
        mHandle.setCompoundDrawablesWithIntrinsicBounds(null, null, mExpandCollapseDrawable, null);
        toggleNoAnimation();
    }

    public void toggleNoAnimation() {
        if(mContentHeight <= 0 && mExpanded) {
            mPendingToggle = true;
            return;
        }
        mPendingToggle = false;
        if(mExpanded) {
            mContent.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
            mExpandCollapseDrawable.setLevel(MAX_LEVEL);
        } else {
            mContent.getLayoutParams().height = 0;
            mExpandCollapseDrawable.setLevel(MAX_LEVEL / 2);
        }
        requestLayout();
    }

    private TextView buildHandleView() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        UnderlineTextView handleTextView = (UnderlineTextView) layoutInflater.inflate(R.layout.view_expandable_handle, null);
        handleTextView.setText(mTitle);
        handleTextView.setLineColor(getResources().getColor(R.color.expandable_handle_textcolor));
        addView(handleTextView, 0, new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, mHeaderHeight));
        return handleTextView;
    }

    private void registerToggler() {
        mHandle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                toggle();
            }
        });
    }

    private void toggle() {
        toggle(true);
    }

    public void toggle(final boolean fire) {
        Runnable onDone = new Runnable() {
            @Override
            public void run() {
                mOnToggleListener.onToggle(mExpanded);
            }
        };
        if(mExpanded) {
            collapse(fire ? onDone : null);
        } else {
            expand(fire ? onDone : null);
        }
    }

    public void expand(Runnable onDone) {
        mExpanded = true;
        startExpandCollapseAnimation(true, onDone);
    }

    public void collapse(Runnable onDone) {
        mExpanded = false;
        startExpandCollapseAnimation(false, onDone);
    }

    private void startExpandCollapseAnimation(final boolean expand, final Runnable onDone) {
        ObjectAnimator expandCollapseAnimator = ObjectAnimator.ofInt(this, "contentHeight", mContent.getHeight(), expand ? mContentHeight : 0);
        expandCollapseAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            int midLevel = MAX_LEVEL / 2;

            public void onAnimationUpdate(ValueAnimator animation) {
                Integer value = (Integer) animation.getAnimatedValue();
                mContent.getLayoutParams().height = value.intValue();
                mContent.requestLayout();

                int level = (int) (midLevel * animation.getAnimatedFraction()) + (expand ? midLevel : 0);
                mExpandCollapseDrawable.setLevel(level);

            }
        });
        expandCollapseAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                if (expand) {
                    mContent.getLayoutParams().height = ViewGroup.LayoutParams.WRAP_CONTENT;
                    mContent.requestLayout();
                }
                if (onDone != null) {
                    onDone.run();
                }
            }
        });

        ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(mContent, "alpha", ViewHelper.getAlpha(mContent), expand ? 1.0f : 0.0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(
                expandCollapseAnimator,
                alphaAnimator
        );
        animatorSet.setDuration(200);
        animatorSet.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mContent.measure(widthMeasureSpec, MeasureSpec.UNSPECIFIED);
        int newContentHeight = mContent.getMeasuredHeight();
        mContentHeight = newContentHeight;

        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        if(mPendingToggle) {
            toggleNoAnimation();
        }
        super.onLayout(changed, l, t, r, b);
    }

    public interface OnToggleListener {
        public void onToggle(boolean expanded);
    }

    private class DefaultOnToggleListener implements OnToggleListener {
        @Override
        public void onToggle(boolean expanded) {}
    }
}
package cn.oywj.newscenter.widget.navigation;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RadioGroup;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.widget.navigation
 * date:2016/11/28
 * author：欧阳维骏
 * instructions:*可进行图片、颜色渐变的导航组*
 */
public class GradientGroup extends RadioGroup implements ViewPager.OnPageChangeListener {
    private ViewPager mViewPager;

    public GradientGroup(Context context) {
        super(context);
    }

    public GradientGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        for (int i = 0; i < getChildCount(); i++) {
            final int position = i;
            getChildAt(i).setOnClickListener(v -> {
                setClickedViewChecked(position);
                if (mViewPager != null) {
                    mViewPager.setCurrentItem(position, false);
                }
            });
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        updateGradient(position, positionOffset);
    }

    private void updateGradient(int position, float offset) {
        Log.d("updateGradient", "position == " + position + ",offset == " + offset);
        if (offset > 0f && offset <= 1f) {
            ((GradientButton) getChildAt(position)).updateAlpha(255 * (1 - offset));
            ((GradientButton) getChildAt(position + 1)).updateAlpha(255 * offset);
        }
    }

    @Override
    public void onPageSelected(int position) {
        setSelectedViewChecked(position);
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setSelectedViewChecked(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            ((GradientButton) getChildAt(i)).setButtonChecked(false);
        }
        ((GradientButton) getChildAt(position)).setButtonChecked(true);
    }

    private void setClickedViewChecked(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            ((GradientButton) getChildAt(i)).setButtonChecked(false);
        }
        ((GradientButton) getChildAt(position)).setButtonChecked(true);
    }

    /**
     * 设置依附的ViewPager
     *
     * @param viewPager ViewPager
     */
    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(this);
    }

}

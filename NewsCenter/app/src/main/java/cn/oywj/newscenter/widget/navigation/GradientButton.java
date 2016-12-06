package cn.oywj.newscenter.widget.navigation;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.RadioButton;

import cn.oywj.newscenter.R;


/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.widget.navigation
 * date:2016/11/28
 * author：欧阳维骏
 * instructions:
 * *1.GradientButton 实现了滑动页面同时让其内容实现了颜色渐变效果，
 * *2.GradientButton 能够展示通知效果并且显示通知数。
 */
public class GradientButton extends RadioButton {
    protected static final int FLAGS =
            Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.FILTER_BITMAP_FLAG;

    protected static final int MAX_ALPHA = 255;
    protected static final float BADGE_HORIZONTAL_FRACTION = .5f;
    protected static final float BADGE_VERTICAL_FRACTION = .75f;
    protected static final float BADGE_HORIZONTAL_OFFSET_FRACTION = .55f;
    protected static final float BADGE_VERTICAL_OFFSET_FRACTION = .1f;
    protected static final float DEFAULT_TEXT_SIZE = 12f;
    private static final int BADGE_ALONE_MSG = 1;
    private static final int BADGE_MAX_MSG_NUM = 100;
    private static final int ZERO = 0;

    private Paint mPaint = new Paint(FLAGS) {
        {
            setStyle(Style.FILL);
        }
    };
    private TextPaint mTextPaint = new TextPaint(FLAGS) {
        {
            setTextAlign(Paint.Align.CENTER);
        }
    };
    private Paint mBadgePaint = new TextPaint(FLAGS) {
        {
            setTextAlign(Align.CENTER);
            setFakeBoldText(true);
        }
    };
    private float mBadgeTextSize;
    private String mBadgeText = "";
    private Rect mBadgeTextBounds = new Rect();
    private RectF mBadgeBounds = new RectF();
    private boolean mShowBadge;

    private Bitmap mSelectBitmap;
    private Bitmap mGeneralBitmap;
    private int mTextColorSelect;

    private int mIconWidth;
    private int mIconPadding;
    private int mIconHeight;
    private int mAlpha;
    private float mFontHeight;
    private float mTextWidth;

    public GradientButton(Context context) {
        this(context, null);
    }

    public GradientButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray t = context.obtainStyledAttributes(attrs, R.styleable.GradientButton);
        mTextColorSelect = t.getColor(R.styleable.GradientButton_textColor_select, Color.BLACK);
        mBadgeTextSize = t.getDimension(R.styleable.GradientButton_textSize_badge, DEFAULT_TEXT_SIZE);
        Drawable generalDw = t.getDrawable(R.styleable.GradientButton_drawable_general);
        Drawable selectDw = t.getDrawable(R.styleable.GradientButton_drawable_select);
        t.recycle();
        setButtonDrawable(null);
        if (generalDw == null || selectDw == null) {
            throw new IllegalArgumentException("both 'drawable_general' and 'drawable_general' should be defined.");
        }

        if (generalDw instanceof BitmapDrawable)
            mGeneralBitmap = ((BitmapDrawable) generalDw).getBitmap();
        else {
            mGeneralBitmap = Bitmap.createBitmap(generalDw.getIntrinsicWidth(), generalDw.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(mGeneralBitmap);
            generalDw.draw(canvas);
        }

        if (selectDw instanceof BitmapDrawable)
            mSelectBitmap = ((BitmapDrawable) selectDw).getBitmap();
        else {
            mSelectBitmap = Bitmap.createBitmap(selectDw.getIntrinsicWidth(), selectDw.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(mSelectBitmap);
            selectDw.draw(canvas);
        }

        mIconWidth = mGeneralBitmap.getWidth();
        mIconHeight = mGeneralBitmap.getHeight();
        mIconPadding = getCompoundDrawablePadding();

        Paint.FontMetrics fontMetrics = getPaint().getFontMetrics();
        mFontHeight = (float) Math.ceil(fontMetrics.descent - fontMetrics.ascent);
        mTextWidth = StaticLayout.getDesiredWidth(getText(), getPaint());

        if (isChecked()) {
            mAlpha = MAX_ALPHA;
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        float width = 0;
        float height = 0;
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                width = Math.max(mTextWidth, mIconWidth) * 1.5f
                        + getPaddingLeft()
                        + getPaddingRight();
                break;
        }
        switch (heightMode) {
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                height = mIconHeight * 1.5f
                        + getPaddingTop()
                        + getPaddingLeft()
                        + mIconPadding
                        + mFontHeight;
                break;
        }
        setMeasuredDimension(width, height);
    }

    protected void setMeasuredDimension(float width, float height) {
        int w = (int) width;
        int h = (int) height;
        setMeasuredDimension(w, h);
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        float leftOffset = (getWidth() - mIconWidth) * .5f + .5f;
        float topOffset = (getHeight() - mIconHeight - mIconPadding - mFontHeight) * .5f + .5f;
        float textX = getWidth() * .5f + .5f;
        float textY = topOffset + mIconHeight + mFontHeight;

        // draw icon
        mPaint.setAlpha(MAX_ALPHA - mAlpha);
        canvas.drawBitmap(mGeneralBitmap, leftOffset, topOffset, mPaint);
        mPaint.setAlpha(mAlpha);
        canvas.drawBitmap(mSelectBitmap, leftOffset, topOffset, mPaint);

        // draw normal text
        mTextPaint.setColor(getCurrentTextColor());
        mTextPaint.setAlpha(MAX_ALPHA - mAlpha);
        mTextPaint.setTextSize(getTextSize());
        mTextPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(
                getText().toString(),
                textX,
                textY,
                mTextPaint
        );
        // draw checked text
        mTextPaint.setColor(mTextColorSelect);
        mTextPaint.setAlpha(mAlpha);
        mTextPaint.setTextSize(getTextSize());
        canvas.drawText(
                getText().toString(),
                textX,
                textY,
                mTextPaint
        );

        // draw badge
        if (mShowBadge && !TextUtils.isEmpty(mBadgeText)) {
            canvas.save();
            mBadgePaint.setTextSize(mBadgeTextSize);
            mBadgePaint.getTextBounds(mBadgeText, 0, mBadgeText.length(), mBadgeTextBounds);
            final float horizontalPadding = mBadgeTextSize * BADGE_HORIZONTAL_FRACTION;
            final float verticalPadding = horizontalPadding * BADGE_VERTICAL_FRACTION;
            final float horizontalOffset = getWidth() * BADGE_HORIZONTAL_OFFSET_FRACTION;
            final float verticalOffset = getHeight() * BADGE_VERTICAL_OFFSET_FRACTION + getPaddingTop();
            if (mBadgeText.length() == BADGE_ALONE_MSG) {
                // draw round
                final float diameter = Math.max(mBadgeTextBounds.width(), mBadgeTextBounds.height());
                mBadgeBounds.set(
                        horizontalOffset,
                        verticalOffset,
                        horizontalOffset + diameter + horizontalPadding,
                        verticalOffset + diameter + horizontalPadding
                );
                mBadgePaint.setColor(Color.RED);
                canvas.drawRoundRect(mBadgeBounds, mBadgeBounds.centerX(), mBadgeBounds.centerY(), mBadgePaint);
            } else if (mBadgeText.length() > BADGE_ALONE_MSG) {
                // draw round rect
                mBadgeBounds.set(
                        horizontalOffset,
                        verticalOffset,
                        horizontalOffset + mBadgeTextBounds.width() + horizontalPadding * 2f,
                        verticalOffset + mBadgeTextBounds.height() + verticalPadding * 1.3f
                );
                mBadgePaint.setColor(Color.RED);
                final float radius = mBadgeBounds.height() * .5f;
                canvas.drawRoundRect(mBadgeBounds, radius, radius, mBadgePaint);
            }
            // centers the text
            Paint.FontMetricsInt fontMetrics = mBadgePaint.getFontMetricsInt();
            final float baselineY = (mBadgeBounds.bottom + mBadgeBounds.top - fontMetrics.bottom - fontMetrics.top) * .5f;
            mBadgePaint.setColor(Color.WHITE);
            canvas.drawText(mBadgeText, mBadgeBounds.centerX(), baselineY, mBadgePaint);
            canvas.restore();
        }
    }

    /**
     * update alpha value.
     *
     * @param alpha value(0 - 255)
     */
    public void updateAlpha(float alpha) {
        mAlpha = (int) alpha;
        invalidate();
    }

    /**
     * Sets whether the Button is selected
     *
     * @param isChecked if true,the button will be selected,otherwise false
     */
    public void setButtonChecked(boolean isChecked) {
        setChecked(isChecked);
        mAlpha = isChecked ? 255 : 0;
        invalidate();
    }

    /**
     * Displays the number of badge messages.
     *
     * @param number messages num.
     */
    public void showBadgeNumber(int number) {
        if (number <= ZERO) return;
        if (number > BADGE_MAX_MSG_NUM) mBadgeText = "99+";
        else mBadgeText = String.valueOf(number);
        mShowBadge = true;
        invalidate();
    }

    /**
     * Hide the number of badge messages.
     */
    public void hideBadgeNumber() {
        mShowBadge = false;
        mBadgeText = "";
        invalidate();
    }
}

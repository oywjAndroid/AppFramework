package cn.oywj.newscenter.stu.view.navigate;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewPager;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Interpolator;

import java.util.ArrayList;

import cn.oywj.newscenter.R;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.stu.view.navigate
 * date:2016/11/24
 * author：欧阳维骏
 * instructions:**
 */
public class NavigateView extends View implements INavigate, ViewPager.OnPageChangeListener {
    protected static final int FLAGS = Paint.ANTI_ALIAS_FLAG
            | Paint.DITHER_FLAG
            | Paint.FILTER_BITMAP_FLAG;

    private final Drawable normal;
    private final Drawable select;
    private Bitmap normalBitmap;
    private Bitmap selectBitmap;

    private ArrayList<Model> mModels = new ArrayList<>();

    private Paint mIconPaint = new Paint(FLAGS) {
        {
            setStyle(Style.FILL);
        }
    };

    private Paint mBadgePaint = new TextPaint(FLAGS) {
        {
            setTextAlign(Align.CENTER);
            setFakeBoldText(true);
        }
    };

    private Paint mSelectIconPaint = new Paint(FLAGS) {
        {
            setStyle(Style.FILL);
            //setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        }
    };

    private float mModelSize;
    private float mIconSize;
    private static final float DEFAULT_ICON_FRACTION = 0.5f;
    private Bitmap mIconBitmap;
    private Canvas mIconCanvas = new Canvas();
    private Bitmap mSelectBitmap;
    private Canvas mSelectCanvas = new Canvas();

//    private RectF mBound = new RectF();

    private Matrix mMatrix = new Matrix();
    private RectF mBgBadgeBound = new RectF();

    public NavigateView(Context context) {
        this(context, null);
    }

    public NavigateView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigateView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray typed = context.obtainStyledAttributes(attrs, R.styleable.NavigateView);
        normal = typed.getDrawable(R.styleable.NavigateView_normal_icons);
        select = typed.getDrawable(R.styleable.NavigateView_select_icons);
        typed.recycle();

        if (normal == null || select == null) {
            return;
        }

        // 将指定Drawable对象转换成Bitmap对象
        if (normal instanceof BitmapDrawable) {
            normalBitmap = ((BitmapDrawable) normal).getBitmap();
        } else {
            normalBitmap = Bitmap.createBitmap(normal.getIntrinsicWidth(), normal.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(normalBitmap);
            normal.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            normal.draw(canvas);
        }

        if (select instanceof BitmapDrawable) {
            selectBitmap = ((BitmapDrawable) select).getBitmap();
        } else {
            selectBitmap = Bitmap.createBitmap(select.getIntrinsicWidth(), select.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(selectBitmap);
            select.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            select.draw(canvas);
        }

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        mIconSize = height * DEFAULT_ICON_FRACTION;

//        if (mModels.isEmpty() || width == 0 || height == 0 || width < height) {
//            return;
//        }
//
//        mModelSize = (float) width / (float) mModels.size();
//        float slide = mModelSize > height ? height : mModelSize;
//        mIconSize = slide * DEFAULT_ICON_FRACTION;
//        mBound.set(0, 0, width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        if (mIconBitmap == null || mIconBitmap.isRecycled()) {
//            mIconBitmap = Bitmap.createBitmap((int) mIconSize, (int) mIconSize, Bitmap.Config.ARGB_8888);
//            mIconCanvas.setBitmap(mIconBitmap);
//        }
//
//        if (mSelectBitmap == null || mSelectBitmap.isRecycled()) {
//            mSelectBitmap = Bitmap.createBitmap((int) mIconSize, (int) mIconSize, Bitmap.Config.ARGB_8888);
//            mSelectCanvas.setBitmap(mSelectBitmap);
//        }
//
//        mIconCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
//        mSelectCanvas.drawColor(0, PorterDuff.Mode.CLEAR);
//
//        // 将普通的图片填充到mIconBitmap可变位图中
//        mIconPaint.setColor(getResources().getColor(R.color.colorAccent));
//        mIconCanvas.drawRect(0, 0, mIconCanvas.getWidth(), mIconCanvas.getHeight(), mIconPaint);
//        float srcLeftOffset = (mIconCanvas.getWidth() - normalBitmap.getWidth()) * .5f;
//        float srcTopOffset = (mIconCanvas.getHeight() - normalBitmap.getHeight()) * .5f;
//        mIconCanvas.drawBitmap(normalBitmap, srcLeftOffset, srcTopOffset, mIconPaint);
//
//        // 将选中图片填充到mSelectBitmap中
//        mSelectCanvas.drawBitmap(selectBitmap, srcLeftOffset, srcTopOffset, mSelectIconPaint);
//
//        float leftOffset = (getMeasuredWidth() - mIconSize) * .5f;
//        float topOffset = (getMeasuredHeight() - mIconSize) * 0.5f;
//        canvas.drawBitmap(mIconBitmap, leftOffset, topOffset, mSelectIconPaint);
//        canvas.drawBitmap(mSelectBitmap, leftOffset, topOffset, mSelectIconPaint);

//        float leftOffset = canvas.getWidth() * .5f;
//        float topOffset = canvas.getHeight() * .3f;
//
//        mMatrix.setScale(3f, 3f);
//        mMatrix.postTranslate(leftOffset - (normalBitmap.getWidth() * 3) * .5f, topOffset - (normalBitmap.getWidth() * 3) * .5f);
//        canvas.drawBitmap(normalBitmap, mMatrix, mSelectIconPaint);
//        //canvas.drawBitmap(normalBitmap, leftOffset, topOffset, mSelectIconPaint);
//        canvas.drawBitmap(selectBitmap, leftOffset, topOffset * 2, mSelectIconPaint);

        mIconPaint.setColor(Color.RED);
        //RectF rect = new RectF(50, 50, 200, 200);
        mBgBadgeBound.set(getWidth() * .5f, getHeight() * .3f, getWidth() * .5f + 40, getHeight() * .3f + 40);
        canvas.drawRoundRect(mBgBadgeBound, getWidth() * .5f + 20, getHeight() * .3f + 20, mIconPaint);
//        canvas.drawRoundRect(rect,
//                30, //x轴的半径
//                30, //y轴的半径
//                mIconPaint);

    }

    @Override
    public void switchPages(int position) {

    }

    @Override
    public void hide() {

    }

    @Override
    public void show() {

    }

    @Override
    public void isSlideSwitch(boolean isSlide) {

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    /**
     * The prototype of the tab in the navigation bar
     */
    public static class Model {
        private int mColorNormal;
        private int mColorSelect;
        private Bitmap mIconNormal;
        private Bitmap mIconSelect;
        private int mPointColorNormal;
        private int mPointColorSelect;
        private String mPointTitle;

        public int getColorNormal() {
            return mColorNormal;
        }

        public void setColorNormal(int colorNormal) {
            mColorNormal = colorNormal;
        }

        public int getColorSelect() {
            return mColorSelect;
        }

        public void setColorSelect(int colorSelect) {
            mColorSelect = colorSelect;
        }

        public Bitmap getIconNormal() {
            return mIconNormal;
        }

        public void setIconNormal(Bitmap iconNormal) {
            mIconNormal = iconNormal;
        }

        public Bitmap getIconSelect() {
            return mIconSelect;
        }

        public void setIconSelect(Bitmap iconSelect) {
            mIconSelect = iconSelect;
        }

        public String getPointTitle() {
            return mPointTitle;
        }

        public void setPointTitle(String pointTitle) {
            mPointTitle = pointTitle;
        }

        public int getPointColorNormal() {
            return mPointColorNormal;
        }

        public void setPointColorNormal(int pointColorNormal) {
            mPointColorNormal = pointColorNormal;
        }

        public int getPointColorSelect() {
            return mPointColorSelect;
        }

        public void setPointColorSelect(int pointColorSelect) {
            mPointColorSelect = pointColorSelect;
        }

        public Model(Builder builder) {
            mColorNormal = builder.mColorNormal;
            mColorSelect = builder.mColorSelect;
            mIconNormal = builder.mIconNormal;
            mIconSelect = builder.mIconSelect;
            mPointTitle = builder.mPointTitle;
            mPointColorNormal = builder.mPointColorNormal;
            mPointColorSelect = builder.mPointColorSelect;
        }

        public static class Builder {
            private int mColorNormal;
            private int mColorSelect;
            private Bitmap mIconNormal;
            private Bitmap mIconSelect;
            private String mPointTitle;
            private int mPointColorNormal;
            private int mPointColorSelect;

            public Builder setColorNormal(int colorNormal) {
                mColorNormal = colorNormal;
                return this;
            }

            public Builder setColorSelect(int colorSelect) {
                mColorSelect = colorSelect;
                return this;
            }

            public Builder setIconNormal(Bitmap iconNormal) {
                mIconNormal = iconNormal;
                return this;
            }

            public Builder setIconSelect(Bitmap selectNormal) {
                mIconSelect = selectNormal;
                return this;
            }

            public Builder setPointTitle(String pointTitle) {
                mPointTitle = pointTitle;
                return this;
            }

            public Builder setPointColorNormal(int pointColorNormal) {
                mPointColorNormal = pointColorNormal;
                return this;
            }

            public Builder setPointColorSelect(int pointColorSelect) {
                mPointColorSelect = pointColorSelect;
                return this;
            }

            public Model build() {
                return new Model(this);
            }
        }
    }
}

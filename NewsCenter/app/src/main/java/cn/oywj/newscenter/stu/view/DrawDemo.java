package cn.oywj.newscenter.stu.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.stu.view
 * date:2016/11/23
 * author：欧阳维骏
 * instructions:**
 */
public class DrawDemo extends View {

    Paint mPaint;

    public DrawDemo(Context context) {
        this(context, null);
    }

    public DrawDemo(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawDemo(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int canvasWidth = canvas.getWidth();
        int canvasHeight = canvas.getHeight();

        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(6);
        mPaint.setAntiAlias(true);

        //drawAxis(canvas, canvasWidth, canvasHeight);

        // 绘制ARGB颜色：ARGB为(0 - 255)，由浅入深。
        //canvas.drawARGB(123, 123, 123, 0);

        //drawText(canvas, canvasWidth, canvasHeight);

        //drawPoint(canvas, canvasWidth, canvasHeight);

        //drawLine(canvas, canvasWidth, canvasHeight);

        drawRect(canvas, canvasWidth, canvasHeight);
    }

    // 理解绘图的坐标轴的概念：绘图坐标圆点会随着canvas的平移、旋转从而发生变化
    private void drawAxis(Canvas canvas, int canvasWidth, int canvasHeight) {
        // 绘制X轴
        mPaint.setColor(Color.parseColor("#ff0000"));
        canvas.drawLine(0, 0, canvasWidth, 0, mPaint);
        mPaint.setColor(Color.parseColor("#00ff00"));
        canvas.drawLine(0, 3, 0, canvasHeight, mPaint);

        //平移之后
        canvas.translate(canvasWidth / 4, canvasHeight / 4);
        mPaint.setColor(Color.parseColor("#ff0000"));
        canvas.drawLine(0, 0, canvasWidth, 0, mPaint);
        mPaint.setColor(Color.parseColor("#00ff00"));
        canvas.drawLine(0, 3, 0, canvasHeight, mPaint);

        // 旋转之后
        canvas.rotate(45);
        mPaint.setColor(Color.parseColor("#ff0000"));
        canvas.drawLine(0, 0, canvasWidth, 0, mPaint);
        mPaint.setColor(Color.parseColor("#00ff00"));
        canvas.drawLine(0, 3, 0, canvasHeight, mPaint);
    }

    // 绘制文本
    private void drawText(Canvas canvas, int canvasWidth, int canvasHeight) {
        mPaint.setColor(Color.parseColor("#fff000"));
        mPaint.setTextSize(30);

        // drawText()中的x,y表示距<绘图坐标的原点>某个点的位置开始绘制文本
        // 下面两种方式是相同的
        //canvas.drawText("绘制文本", 0, 30, mPaint);
        canvas.save();
        canvas.translate(0, 30);
        canvas.drawText("绘制文本", 0, 0, mPaint);
        canvas.restore();

        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setColor(Color.BLACK);
        canvas.save();
        canvas.translate(0, 30 * 2);
        canvas.drawText("左对齐文本", 0, 0, mPaint);
        canvas.restore();

        // 判断这个左右对齐是以X轴为中心，正X轴为左，负X轴为右
        mPaint.setTextAlign(Paint.Align.RIGHT);
        mPaint.setColor(Color.RED);
        //canvas.translate(canvasWidth / 2, 30 * 2);
        canvas.save();
        canvas.drawText("右对齐文本", canvasWidth / 2, 30 * 4, mPaint);
        canvas.restore();
        // 下划线
        mPaint.setTextAlign(Paint.Align.LEFT);
        mPaint.setUnderlineText(true);
        canvas.save();
        canvas.drawText("下划线文本", 0, 30 * 6, mPaint);
        canvas.restore();
        // 字体加粗
        mPaint.setFakeBoldText(true);
        mPaint.setUnderlineText(false);
        canvas.save();
        canvas.drawText("加粗文本", 0, 30 * 8, mPaint);
        canvas.restore();
        // 字体加粗
        canvas.rotate(20);
        canvas.save();
        canvas.drawText("加粗文本被旋转", 180, 30 * 10, mPaint);
        canvas.restore();

        //canvas.save()调用时，将当前坐标系保存下来，将当前坐标系的矩阵Matrix入栈保存，
        // 然后通过translate或rotate等对坐标系进行变换，然后进行绘图，绘图完成后，
        // 我们通过调用canvas.restore()将之前保存的Matrix出栈，这样就将当前绘图坐标系恢复到了canvas.save()执行的时候状态。
    }

    // 绘制圆点
    private void drawPoint(Canvas canvas, int canvasWidth, int canvasHeight) {
        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setStrokeWidth(25);
        canvas.drawPoint(canvasWidth / 2, canvasHeight / 3, mPaint);

        mPaint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawPoint(canvasWidth / 2, canvasHeight / 2, mPaint);

        mPaint.setStrokeCap(Paint.Cap.SQUARE);
        canvas.drawPoint(canvasWidth / 2, canvasHeight / 2 + 80, mPaint);
    }

    // 绘制线条
    private void drawLine(Canvas canvas, int canvasWidth, int canvasHeight) {
        mPaint.setColor(Color.CYAN);
        canvas.drawLine(0, 0, canvasWidth / 2, canvasHeight / 2, mPaint);
        canvas.drawLine(canvasWidth / 2, canvasHeight / 2, canvasWidth, 0, mPaint);
    }

    // 绘制矩形
    private void drawRect(Canvas canvas, int canvasWidth, int canvasHeight) {
        mPaint.setColor(Color.CYAN);
        Rect leftRect = new Rect(10, 10, canvasWidth / 2 - 10, canvasHeight / 2 - 10);
        canvas.drawRect(leftRect, mPaint);

        mPaint.setColor(Color.RED);
        Rect rightRect = new Rect(canvasWidth / 2 + 10, 10, canvasWidth - 10, canvasHeight / 2 - 10);
        canvas.drawRect(rightRect, mPaint);
    }

    private void drawCircle(Canvas canvas, int canvasWidth, int canvasHeight){

    }



}

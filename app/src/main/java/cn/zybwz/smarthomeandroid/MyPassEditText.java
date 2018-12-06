package cn.zybwz.smarthomeandroid;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Editable;
import android.util.AttributeSet;

public class MyPassEditText extends android.support.v7.widget.AppCompatEditText{
    private final int PWD_SPACING = 5;
    /**
     * 密码大小
     */
    private final int PWD_SIZE =10;
    /**
     * 密码长度
     */
    private final int PWD_LENGTH = 6;
    /**
     * 上下文
     */
    private Context mContext;
    /**
     * 宽度
     */
    private int mWidth;
    /**
     * 高度
     */
    private int mHeight;
    /**
     * 密码框
     */
    private RectF mRectf;

    /**
     * 密码画笔
     */
    private Paint mPwdPaint;

    private Paint mPwdPaintText;


    /**
     * 密码框画笔
     */
    private Paint mRectPaint,mRectPaint1;
    /**
     * 输入的密码长度
     */
    private int mInputLength;

    /**
     * 输入结束监听
     */
    private OnInputFinishListener mOnInputFinishListener;
    private String text;
    private boolean isPassword=true;

    /**
     * 构造方法
     *
     * @param context
     * @param attrs
     */
    public MyPassEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        // 初始化密码画笔
        mPwdPaint = new Paint();
        mPwdPaint.setColor(Color.parseColor("#6699ff"));
        mPwdPaint.setStyle(Paint.Style.FILL);
        mPwdPaint.setAntiAlias(true);

        // 初始化文字画笔
        mPwdPaintText = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPwdPaintText.setColor(Color.parseColor("#6699ff"));
        mPwdPaintText.setTextSize(60f);
        // 初始化密码框
        mRectPaint = new Paint();
        mRectPaint.setStyle(Paint.Style.STROKE);
        mRectPaint.setStrokeWidth(4);
        mRectPaint.setColor(Color.parseColor("#cccccc"));
        mRectPaint.setAntiAlias(true);
        mRectPaint1 = new Paint();
        mRectPaint1.setStrokeWidth(4);
        mRectPaint1.setStyle(Paint.Style.STROKE);
        mRectPaint1.setColor(Color.parseColor("#6699ff"));
        mRectPaint1.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mWidth = getWidth();
        mHeight = getHeight();

        // 这三行代码非常关键，大家可以注释点在看看效果
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        canvas.drawRect(0, 0, mWidth, mHeight, paint);

        // 计算每个密码框宽度
        int rectWidth = (mWidth - PWD_SPACING * (PWD_LENGTH - 1)) / PWD_LENGTH;
        // 绘制密码框
        for (int i = 0; i < PWD_LENGTH; i++) {
            int left = (rectWidth + PWD_SPACING) * i;
            int top = 2;
            int right = left + rectWidth;
            int bottom = mHeight - top;
            mRectf = new RectF(left, top, right, bottom);
            canvas.drawRoundRect(mRectf,10,10, mRectPaint);
        }

        // 绘制密码
        for (int i = 0; i < mInputLength; i++) {
            int cx = rectWidth / 2 + (rectWidth + PWD_SPACING) * i-15;
            int cy = mHeight / 2+15;
            int left = (rectWidth + PWD_SPACING) * i;
            int top = 2;
            int right = left + rectWidth;
            int bottom = mHeight - top;
            mRectPaint1.setColor(Color.parseColor("#6699ff"));
            mRectf = new RectF(left, top, right, bottom);
            canvas.drawRoundRect(mRectf,10,10, mRectPaint1);
//            if (!isPassword){
//                String[] c=text.split("");
//                canvas.drawText(c[i+1],cx, cy, mPwdPaintText);
//            }else {
                canvas.drawCircle(cx, cy, PWD_SIZE, mPwdPaint);
//            }

        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start,
                                 int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        if (text.toString().length()<7){
            this.mInputLength = text.toString().length();
            invalidate();
            if (mInputLength == PWD_LENGTH && mOnInputFinishListener != null) {
                this.text=text.toString();
                mOnInputFinishListener.onIsFinish(true);
            }
            else if (mOnInputFinishListener!=null)
                mOnInputFinishListener.onIsFinish(false);
        }
    }


    public String getPass() {
        return text;
    }

    public interface OnInputFinishListener {
        void onIsFinish(boolean isFinish);
    }

    /**
     * 设置输入完成监听
     *
     * @param onInputFinishListener
     */
    public void setOnInputFinishListener(
            OnInputFinishListener onInputFinishListener) {
        this.mOnInputFinishListener = onInputFinishListener;
    }


}

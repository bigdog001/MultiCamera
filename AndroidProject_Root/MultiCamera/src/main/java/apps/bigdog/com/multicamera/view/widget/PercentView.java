package apps.bigdog.com.multicamera.view.widget;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.tool.mytool.lib.util.DisplayUtil;
import com.tool.mytool.lib.util.LogUtil;
import apps.bigdog.com.multicamera.R;

public class PercentView  extends View {
    private final static String TAG = PercentView.class.getSimpleName();
    private Paint mPaint;
    private int backgroundColor = Color.GRAY;
    private int progressColor = Color.BLUE;
    private float radius;
    private int progress;
    private float centerX = 0;
    private float centerY = 0;
    public static final int LEFT = 0;
    public static final int TOP = 1;
    public static final int CENTER = 2;
    public static final int RIGHT = 3;
    public static final int BOTTOM = 4;
    private int gravity = CENTER;
    private RectF rectF;  //用于定义的圆弧的形状和大小的界限

    public PercentView(Context context) {
        super(context);
        init();
    }

    public PercentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initParams(context, attrs);
        init();
    }

    public PercentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initParams(context, attrs);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);//抗锯齿方法
        rectF = new RectF();
    }

    private void initParams(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
//        rectF = new RectF();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PercentView);
        if (typedArray != null) {
            backgroundColor = typedArray.getColor(R.styleable.PercentView_percent_background_color, Color.GRAY);
            progressColor = typedArray.getColor(R.styleable.PercentView_percent_progress_color, Color.BLUE);
            radius = typedArray.getDimension(R.styleable.PercentView_percent_circle_radius, 0);
            progress = typedArray.getInt(R.styleable.PercentView_percent_circle_progress, 0);
            gravity = typedArray.getInt(R.styleable.PercentView_percent_circle_gravity, CENTER);
            LogUtil.log("backgroundColor:"+backgroundColor+",progressColor:"+progressColor+",radius:"+radius+",progress:"+progress+",gravity:"+gravity);
            typedArray.recycle();
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        LogUtil.log(TAG, "onMeasure--widthMode-->" + widthMode);
        LogUtil.log("onMeasure--widthMode:"+widthMode+",onMeasure--widthSize:"+widthSize+",onMeasure--heightMode:"+heightMode+",onMeasure--heightSize:"+heightSize);
        switch (widthMode) {
            case MeasureSpec.EXACTLY://
                break;
            case MeasureSpec.AT_MOST:
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }

       /* int with = getWidth();
        int height = getHeight();*/
        int with = getMeasuredWidth();
        int height = getMeasuredHeight();

        LogUtil.log("the with:"+with+",the height:"+height);
        centerX = with / 2;
        centerY = with / 2;
        LogUtil.log("the centerX:"+centerX+",the centerY:"+centerY);
        switch (gravity) {
            case LEFT:
                centerX = radius + getPaddingLeft();
                centerY = radius + getPaddingTop();
                break;
            case TOP:
                centerX = with/2;
                centerY = radius ;
                break;
            case CENTER:
                centerX = with/2 ;
                centerY = height/2 ;
                break;
            case RIGHT:
                centerX = with - radius - getPaddingRight();
                centerY =  radius;
                break;
            case BOTTOM:
                centerX = with/2 ;
                centerY = height -  radius;
                break;
        }
        LogUtil.log("the PaddingLeft:"+getPaddingLeft()+",the PaddingRight:"+getPaddingRight()+",PaddingTop:"+getPaddingTop()+",PaddingBottom:"+getPaddingBottom());
        LogUtil.log("the centerX1:"+centerX+",the centerY1:"+centerY);
        LogUtil.log("the dip to px is::"+ DisplayUtil.dip2px(getContext(),30));
        float left1 = centerX - radius;
        float top1 = centerY - radius;
        float right1 = centerX + radius;
        float bottom1 = centerY + radius;
        LogUtil.log("the left:"+left1+",the right:"+right1+",top:"+top1+",bottom:"+bottom1);
        rectF.set(left1, top1, right1, bottom1);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        LogUtil.log(TAG, "onLayout");

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(backgroundColor);
        // FILL填充, STROKE描边,FILL_AND_STROKE填充和描边
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(centerX, centerY, radius, mPaint);
        mPaint.setColor(progressColor);

        double percent = progress * 1.0 / 100;
        int angle = (int) (percent * 360);
        canvas.drawArc(rectF, 270, angle, true, mPaint);  //根据进度画圆弧
    }
}
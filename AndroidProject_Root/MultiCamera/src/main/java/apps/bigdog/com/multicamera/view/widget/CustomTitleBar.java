package apps.bigdog.com.multicamera.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tool.mytool.lib.util.LogUtil;

import apps.bigdog.com.multicamera.R;
/**
 * Created by jw362j on 1/1/2017.
 */
public class CustomTitleBar extends RelativeLayout{
    private Button titleBarLeftBtn;
    private Button titleBarRightBtn;
    private TextView titleBarTitle;
    private Context mContext;
    private AttributeSet mAttrs;
    public CustomTitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        LayoutInflater.from(context).inflate(R.layout.custom_title_bar, this, true);
        mContext = context;
        mAttrs = attrs;
        initItems();
    }

    private void initItems() {
        titleBarLeftBtn = (Button) findViewById(R.id.title_bar_left);
        titleBarRightBtn = (Button) findViewById(R.id.title_bar_right);
        titleBarTitle = (TextView) findViewById(R.id.title_bar_title);

        TypedArray attributes = mContext.obtainStyledAttributes(mAttrs, R.styleable.CustomTitleBar);
        if (attributes != null) {
            //处理titleBar背景色
            int titleBarBackGround = attributes.getResourceId(R.styleable.CustomTitleBar_title_background_color, Color.GREEN);
            setBackgroundResource(titleBarBackGround);
            //先处理左边按钮
            //获取是否要显示左边按钮
            boolean leftButtonVisible = attributes.getBoolean(R.styleable.CustomTitleBar_left_button_visible, true);
            if (leftButtonVisible) {
                titleBarLeftBtn.setVisibility(View.VISIBLE);
            } else {
                titleBarLeftBtn.setVisibility(View.INVISIBLE);
            }
            //设置左边按钮的文字
            String leftButtonText = attributes.getString(R.styleable.CustomTitleBar_left_button_text);
            if (!TextUtils.isEmpty(leftButtonText)) {
                titleBarLeftBtn.setText(leftButtonText);
                //设置左边按钮文字颜色
                int leftButtonTextColor = attributes.getColor(R.styleable.CustomTitleBar_left_button_text_color, Color.WHITE);
                titleBarLeftBtn.setTextColor(leftButtonTextColor);
            } else {
                //设置左边图片icon 这里是二选一 要么只能是文字 要么只能是图片
                int leftButtonDrawable = attributes.getResourceId(R.styleable.CustomTitleBar_left_button_drawable, R.drawable.titlebar_back_icon);
                if (leftButtonDrawable != -1) {
                    titleBarLeftBtn.setBackgroundResource(leftButtonDrawable);
                }
            }

            //处理标题
            //先获取标题是否要显示图片icon
            int titleTextDrawable = attributes.getResourceId(R.styleable.CustomTitleBar_title_text_drawable, -1);
            if (titleTextDrawable != -1) {
                titleBarTitle.setBackgroundResource(titleTextDrawable);
            } else {
                //如果不是图片标题 则获取文字标题
                String titleText = attributes.getString(R.styleable.CustomTitleBar_title_text);
                if (!TextUtils.isEmpty(titleText)) {
                    titleBarTitle.setText(titleText);
                }
                //获取标题显示颜色
                int titleTextColor = attributes.getColor(R.styleable.CustomTitleBar_title_text_color, Color.WHITE);
                titleBarTitle.setTextColor(titleTextColor);
            }

            //先处理右边按钮
            //获取是否要显示右边按钮
            boolean rightButtonVisible = attributes.getBoolean(R.styleable.CustomTitleBar_right_button_visible, true);
            if (rightButtonVisible) {
                titleBarRightBtn.setVisibility(View.VISIBLE);
            } else {
                titleBarRightBtn.setVisibility(View.INVISIBLE);
            }
            //设置右边按钮的文字
            String rightButtonText = attributes.getString(R.styleable.CustomTitleBar_right_button_text);
            if (!TextUtils.isEmpty(rightButtonText)) {
                titleBarRightBtn.setText(rightButtonText);
                //设置右边按钮文字颜色
                int rightButtonTextColor = attributes.getColor(R.styleable.CustomTitleBar_right_button_text_color, Color.WHITE);
                titleBarRightBtn.setTextColor(rightButtonTextColor);
            } else {
                //设置右边图片icon 这里是二选一 要么只能是文字 要么只能是图片
                int rightButtonDrawable = attributes.getResourceId(R.styleable.CustomTitleBar_right_button_drawable, -1);
                if (rightButtonDrawable != -1) {
                    titleBarRightBtn.setBackgroundResource(rightButtonDrawable);
                }
            }
            attributes.recycle();
        }
    }

    private Paint mPaint;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.RED);
        // FILL填充, STROKE描边,FILL_AND_STROKE填充和描边
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(15, 15, 15, mPaint);
    }

    public void setTitleClickListener(OnClickListener onClickListener) {
        if (onClickListener != null) {
            titleBarLeftBtn.setOnClickListener(onClickListener);
            titleBarRightBtn.setOnClickListener(onClickListener);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        LogUtil.log("in CustomTitleBar.onLayout,parentHeight:"+getMeasuredHeight()+",parentWidth:"+getMeasuredWidth());
        LogUtil.log("in CustomTitleBar.onLayout,left:"+l+",top:"+t+",right:"+r+",bottom:"+b);
        int childCount = getChildCount();
        LogUtil.log("in CustomTitleBar.childCount:"+childCount);
        // 设置一个变量保存到父View左侧的距离
        int mLeft = 0;
        // 遍历子View
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if(childView instanceof TextView){
                LogUtil.log("in CustomTitleBar.TextView("+i+"),"+((TextView)childView).getText());
            }
        }
        super.onLayout(changed,l,t,r,b);
    }

    public Button getTitleBarLeftBtn() {
        return titleBarLeftBtn;
    }

    public Button getTitleBarRightBtn() {
        return titleBarRightBtn;
    }

    public TextView getTitleBarTitle() {
        return titleBarTitle;
    }
}

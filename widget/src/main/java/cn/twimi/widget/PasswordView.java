package cn.twimi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.InputFilter;
import android.text.InputType;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

public class PasswordView extends AppCompatEditText {

    private final int DEFAULT_BORDER_WIDTH = 3;
    private final int DEFAULT_CIRCLE_WIDTH = 6;
    private final int DEFAULT_MAX_COUNT = 6;
    private final int DEFAULT_BORDER_COLOR = -10066330;
    private final int DEFAULT_FORE_COLOR = -13421773;

    private int width;
    private int height;
    private int divideLineX;
    private int circleX;
    private int maxCount;
    private int inputLength;
    private int borderWidth;
    private int circleWidth;
    private Paint paintLine;
    private Paint paintCircle;
    private Context context;

    private OnCompleted onCompleted;

    public PasswordView(Context context) {
        this(context, null);
        maxCount = DEFAULT_MAX_COUNT;
        borderWidth = DEFAULT_BORDER_WIDTH;
        circleWidth = DEFAULT_CIRCLE_WIDTH;
        initPaint(DEFAULT_BORDER_COLOR, DEFAULT_FORE_COLOR);
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxCount)});
    }

    public PasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PasswordView);
        int borderColor = typedArray.getColor(R.styleable.PasswordView_border_color, DEFAULT_BORDER_COLOR);
        int foreColor = typedArray.getColor(R.styleable.PasswordView_fore_color, DEFAULT_FORE_COLOR);
        maxCount = typedArray.getInteger(R.styleable.PasswordView_max_length, DEFAULT_MAX_COUNT);
        borderWidth = typedArray.getInteger(R.styleable.PasswordView_border_width, DEFAULT_BORDER_WIDTH);
        circleWidth = typedArray.getInteger(R.styleable.PasswordView_circle_width, DEFAULT_CIRCLE_WIDTH);
        typedArray.recycle();

        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxCount)});
        this.setBackgroundColor(Color.TRANSPARENT);
        this.setCursorVisible(false);
        this.setInputType(InputType.TYPE_CLASS_NUMBER);
        initPaint(borderColor, foreColor);
    }

    public void setOnCompleted(OnCompleted onCompleted) {
        this.onCompleted = onCompleted;
    }

    public void setMaxCount(int maxCount) {
        this.maxCount = maxCount;
        this.setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxCount)});
    }

    private void initPaint(int borderColor, int foreColor) {
        paintLine = new Paint();
        paintLine.setStrokeWidth(borderWidth);
        paintLine.setColor(borderColor);
        paintLine.setAntiAlias(true);
        paintLine.setStyle(Paint.Style.STROKE);

        paintCircle = new Paint();
        paintCircle.setColor(foreColor);
        paintCircle.setAntiAlias(true);
        paintCircle.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        height = h;
        width = w;
        divideLineX = w / maxCount;
        circleX = divideLineX / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawBorder(canvas);
        drawDivideLine(canvas);
        drawPassCircle(canvas);
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
        inputLength = text.length();
        if (inputLength >= maxCount) {
            if (onCompleted != null) {
                onCompleted.onCompleted(text.toString());
            }
        }
        invalidate();
    }

    private void drawDivideLine(Canvas canvas) {
        for (int i = 0; i < maxCount - 1; i++) {
            canvas.drawLine((i + 1) * divideLineX, borderWidth / 2.0f, (i + 1) * divideLineX, height - borderWidth, paintLine);
        }
    }

    private void drawPassCircle(Canvas canvas) {
        for (int i = 0; i < inputLength; i++) {
            canvas.drawCircle(circleX * (i * 2 + 1), (float) height / 2, dp2px(context, circleWidth), paintCircle);
        }
    }

    private void drawBorder(Canvas canvas) {
        canvas.drawRoundRect(new RectF(borderWidth / 2.0f, borderWidth / 2.0f, width - borderWidth, height - borderWidth), dp2px(context, borderWidth), dp2px(context, borderWidth), paintLine);
    }

    public static int dp2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public interface OnCompleted {
        void onCompleted(String password);
    }
}

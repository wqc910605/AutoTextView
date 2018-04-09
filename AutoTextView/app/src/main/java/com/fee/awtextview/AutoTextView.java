package com.fee.awtextview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;

/**
 * Created by wwf on 2018/4/9.
 */

public class AutoTextView extends android.support.v7.widget.AppCompatTextView {
    private int tagTextColor = 0xFF466890;
    private String tag = "...全文";

    public AutoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public AutoTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoTextView(Context context) {
        this(context, null);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    public boolean onPreDraw() {
        replaceText();
        return super.onPreDraw();
    }

    private boolean isFirst = true;
    public void replaceText() {
        if (!isFirst) {
            return;
        }
        isFirst = false;
        int count = getLineCount();
        System.out.println("count::" + count);
        if (count > 3) {
            int st1 = getLayout().getLineEnd(0);
            int st2 = getLayout().getLineEnd(1);
            int st3 = getLayout().getLineEnd(2);
            System.out.println("count::" + st1 + "/" + st2 + "/" + st3);
            String content = getText().toString().substring(0, st3-1);
            if (st2 + 1 == st3) {
                content = content + tag;
                setColor(content, content.length() - 5, content.length(), tagTextColor);
                return;
            }
            //获取第三行的内容
            String threeText = getText().toString().substring(st2+1, st3-1);//去掉换行符
            Paint paint = new Paint();
            paint.setTextSize(getTextSize());
            float pointWidth = paint.measureText(tag);//全文的宽度
            float threeTextWidth = paint.measureText(threeText, 0, threeText.length());
            if ((threeTextWidth + pointWidth) < getMeasuredWidth()) {
                content = content + tag;
            } else {
                content = content.substring(0, content.length()-5) + tag;
            }
            setColor(content, content.length() - 5, content.length(), tagTextColor);
        }
    }

    private void setColor(String content, int start, int end, int textColor) {
        if (start <= end) {
            SpannableStringBuilder style = new SpannableStringBuilder(content);
            style.setSpan(new ForegroundColorSpan(textColor), start, end,
                    Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            setText(style);
        }
    }

    public void showText(String text) {
        isFirst = true;
        ViewTreeObserver viewTreeObserver = getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(this);
        setText(text);
    }
}
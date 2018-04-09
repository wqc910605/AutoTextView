package com.fee.awtextview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private String text = "jokG5456KL542356jsjdherGHSfgdhfkfghghfkklhgklghfklkhgghfghfhfjukjuhjhj";
    private String text2 = "jokG5456KL542356jsjdherGHS";
    private AutoTextView mAutoTextView;
    private EditText et_editext;
    private TextView tv_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAutoTextView = (AutoTextView) findViewById(R.id.awtextview);
        et_editext = (EditText) findViewById(R.id.et_editext);
        tv_send = (TextView) findViewById(R.id.tv_send);
        tv_send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String text = et_editext.getText().toString();
        mAutoTextView.showText(text);
    }
}

package cn.oywj.newscenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;

import javax.inject.Inject;

import cn.oywj.newscenter.stu.activity.OtherActivity;
import cn.oywj.newscenter.stu.di.MainComponent;
import cn.oywj.newscenter.stu.di.Property;
import cn.oywj.newscenter.stu.di.PropertyQualifier;

public class MainActivity extends AppCompatActivity {

    private TextView text;

    @PropertyQualifier("One")
    @Inject
    Property mPropertyOne;

    @PropertyQualifier("Two")
    @Inject
    Property mPropertyTwo;

    @Inject
    Gson mGson;

    @Inject
    int ssInt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainComponent.getInstance().inject(this);
        text = (TextView) findViewById(R.id.text_);
        //text.setText(mProperty.getPropertiesName());

        //String json = mGson.toJson(mProperty);
        //text.setText(json + "," + mProperty + ";" + ssInt);
        text.setText("one --- " + mPropertyOne.getPropertiesName() + ",two --- " + mPropertyTwo.getPropertiesName());

    }

    public void intentOther(View v) {
        Intent intent = new Intent(MainActivity.this, OtherActivity.class);
        startActivity(intent);
    }
}

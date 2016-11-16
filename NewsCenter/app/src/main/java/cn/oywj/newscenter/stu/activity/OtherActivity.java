package cn.oywj.newscenter.stu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.gson.Gson;

import javax.inject.Inject;

import cn.oywj.newscenter.R;
import cn.oywj.newscenter.stu.di.MainComponent;
import cn.oywj.newscenter.stu.di.Property;
import retrofit2.Retrofit;

public class OtherActivity extends AppCompatActivity {

    private TextView text;

    @Inject
    Property mProperty;

    @Inject
    Gson mGson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainComponent.getInstance().inject(this);
        text = (TextView) findViewById(R.id.text_);
        //text.setText(mProperty.getPropertiesName());

        String json = mGson.toJson(mProperty);
        text.setText(json + "," + mProperty);

    }
}

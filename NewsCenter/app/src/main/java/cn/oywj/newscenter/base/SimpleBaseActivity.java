package cn.oywj.newscenter.base;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import cn.oywj.newscenter.app.App;

/**
 * projectName:NewsCenter
 * packageName:cn.oywj.newscenter.base
 * date:2016/11/17
 * author：欧阳维骏
 * instructions:* Simple base class for Activity *
 * 如果是业务逻辑比较简单的Activity可实现此类，从而避免繁琐的BaseActivity。
 */
public abstract class SimpleBaseActivity extends AppCompatActivity {
    protected Activity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        mActivity = this;
        App.getInstance().addActivity(this);
        initEventAndData();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        App.getInstance().removeActivity(this);
    }

    protected abstract int getLayoutId();

    protected abstract void initEventAndData();
}

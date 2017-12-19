package com.xyz.leeeyou.zhihuribao.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.xyz.leeeyou.zhihuribao.R;
import com.xyz.leeeyou.zhihuribao.utils.ToolbarHelper;

/**
 * 基类,原生Toolbar样式的BaseActivity
 */
public abstract class BaseOriginalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base_original);

        setupActivityComponent();
        // 检查sdk的版本
        ToolbarHelper.checkSdkVersionToTranslucentFlag(getWindow());
    }

    // 抽象方法
    protected abstract void setupActivityComponent();

    // 在activity里面重写setContentView(int layoutResID)方法

    @Override
    public void setContentView(int layoutResID) {
//        super.setContentView(layoutResID);
        ToolbarHelper.setContentView(this, layoutResID);
    }


    // 在activity里面重写setContentView(View view)方法

    /**
     * 注释掉super方法，不然会报下面这个空指针异常：
     *  Caused by: java.lang.NullPointerException:
     *  Attempt to invoke virtual method 'void android.support.v7.app.ActionBar.setTitle(java.lang.CharSequence)' on a null object reference
     * @param view
     */
    @Override
    public void setContentView(View view) {
//        super.setContentView(view);
        ToolbarHelper.setContentView(this, view);
    }

    /**
     * @param title
     */
    // 自定义父类方法，便于子类使用
    public void setLeftTitleAndDoNotDisplayHomeAsUp(String title) {
        ToolbarHelper.setLeftTitle(getSupportActionBar(), title);
        ToolbarHelper.hideHomeAsUp(getSupportActionBar());
    }

    public void setLeftTitleAndDisplayHomeAsUp(String title) {
        ToolbarHelper.setLeftTitle(getSupportActionBar(), title);
        ToolbarHelper.showHomeAsUp(getSupportActionBar());
    }

}

package com.xyz.leeeyou.zhihuribao.view.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.xyz.leeeyou.zhihuribao.R;
import com.xyz.leeeyou.zhihuribao.base.BaseOriginalActivity;
import com.xyz.leeeyou.zhihuribao.model.ribao.StoryDetail;
import com.xyz.leeeyou.zhihuribao.di.component.DaggerStoryComponent;
import com.xyz.leeeyou.zhihuribao.di.component.StoryComponent;
import com.xyz.leeeyou.zhihuribao.di.module.StoryModule;
import com.xyz.leeeyou.zhihuribao.utils.HtmlUtils;
import com.xyz.leeeyou.zhihuribao.utils.T;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

public class StoryDetailActivity extends BaseOriginalActivity {

    private int storyId;

    WebView story_web;

    @Inject
    Observable<StoryDetail> detailObservable;

    private StoryComponent storyComponent;
    private StoryModule storyModule;

    private MaterialDialog mMaterialDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story_detail);

        story_web = (WebView) findViewById(R.id.story_web);

        storyId = getIntent().getIntExtra("storyId", -1);
        String storyTitle = getIntent().getStringExtra("storyTitle");

        injectModule();

        // 实现父类方法
        setLeftTitleAndDoNotDisplayHomeAsUp(storyTitle);

        initWebView();

        getStoryDetail();
    }

    private void injectModule() {
        storyModule.setStoryId(storyId);
        storyComponent.inject(this);
    }

    private void initWebView() {
        story_web.setVerticalScrollBarEnabled(false);
        story_web.getSettings().setDefaultTextEncodingName("UTF-8");
    }

    @Override
    protected void setupActivityComponent() {
        storyModule = new StoryModule();

        storyComponent = DaggerStoryComponent
                .builder()
                .storyModule(storyModule)
                .build();
    }

    public void getStoryDetail() {
        detailObservable
                .subscribeOn(Schedulers.newThread())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mMaterialDialog = new MaterialDialog.Builder(StoryDetailActivity.this)
                                .content("请等待...")
                                .progress(true, 0)
                                .show();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<StoryDetail>() {
                    @Override
                    public void onCompleted() {
                        mMaterialDialog.dismiss();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mMaterialDialog.dismiss();
                        e.printStackTrace();
                        T.showShort(StoryDetailActivity.this, "请求失败");
                    }

                    @Override
                    public void onNext(StoryDetail storyDetail) {
                        // 下面第一个也能得到结果，不会有乱文。但弊端是没有限制屏幕，文章左右拖动会有空白区域
//                        story_web.loadData(storyDetail.getBody(), "text/html; charset=UTF-8", null);
                        story_web.loadData(HtmlUtils.structHtml(storyDetail.getBody(), storyDetail.getCss()), "text/html; charset=UTF-8", null);
                    }
                });
    }

}

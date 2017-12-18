package com.xyz.leeeyou.zhihuribao.view.activity

import `in`.srain.cube.views.ptr.PtrClassicFrameLayout
import `in`.srain.cube.views.ptr.PtrFrameLayout
import `in`.srain.cube.views.ptr.PtrHandler
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.view.View
import com.xyz.leeeyou.zhihuribao.R
import com.xyz.leeeyou.zhihuribao.view.adapter.ViewPagerAdapter
import com.xyz.leeeyou.zhihuribao.view.fragment.BaseFragment
import com.xyz.leeeyou.zhihuribao.view.fragment.OneFragment
import com.xyz.leeeyou.zhihuribao.view.fragment.StoryFragment
import com.ogaclejapan.smarttablayout.SmartTabLayout
import com.xyz.leeeyou.zhihuribao.base.BaseOriginalActivity
import java.util.*

class IndexActivity : BaseOriginalActivity() {

//    重写父类的抽象方法——将todo注释掉不然会报错,kotlin.NotImplementedError: An operation is not implemented: not implemented
    override fun setupActivityComponent() {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    /**
     * lateinit 只用于 var，而 lazy 只用于 val
     * lazy 应用于单例模式(if-null-then-init-else-return)，而且当且仅当变量被第一次调用的时候，委托方法才会执行。
     * lateinit 则用于只能生命周期流程中进行获取或者初始化的变量，比如 Android 的 onCreate()
     */

    private val FIRST_PAGE_INDEX: Int = 0

    private lateinit var mPtrFrame: PtrClassicFrameLayout
    lateinit var mViewPagerAdapter: ViewPagerAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_index)

        initAdapter()
        initViewPager()
        initPtr()
    }

    private fun initViewPager() {
        val viewPager: ViewPager = findViewById(R.id.viewpager)
        viewPager.adapter = mViewPagerAdapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                mViewPagerAdapter.switchTo(position)
            }
        })

        val viewPagerTab: SmartTabLayout = findViewById(R.id.viewpagertab)
        viewPagerTab.setViewPager(viewPager)
    }

    private fun initAdapter() {
        val fragmentList = ArrayList<BaseFragment>()
        fragmentList.add(StoryFragment())
        fragmentList.add(OneFragment())

        val titleList = ArrayList<String>()
        titleList.add("知乎日报")
        titleList.add("一个")
        // 这里比较与Java的不同，传参
        mViewPagerAdapter = ViewPagerAdapter(supportFragmentManager, fragmentList, titleList)
        mViewPagerAdapter.switchTo(FIRST_PAGE_INDEX)
    }

    private fun initPtr() {
        mPtrFrame = findViewById(R.id.store_house_ptr_frame)
        mPtrFrame.disableWhenHorizontalMove(true)
        mPtrFrame.setPtrHandler(object : PtrHandler {
            override fun onRefreshBegin(frame: PtrFrameLayout?) {
                mViewPagerAdapter.updateData()
            }

            override fun checkCanDoRefresh(frame: PtrFrameLayout?, content: View?, header: View?): Boolean {
                return mViewPagerAdapter.checkCanDoRefresh()
            }
        })
    }

    fun refreshComplete() {
        mPtrFrame.refreshComplete()
    }

}

package com.xyz.leeeyou.zhihuribao.base

import android.support.v4.app.Fragment

/**
 * Created by leeeyou on 2017/4/26.
 */
abstract class BaseFragment : Fragment() {
    abstract fun checkCanDoRefresh(): Boolean
    abstract fun updateData()
}
package com.example.youjia.homemenudemo

import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup

/**
 * author:CQE
 * Date: 2017/9/28.
 */
class ViewpagerAdapter(val mViewList:MutableList<View>):PagerAdapter() {

    //添加
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        container.addView(mViewList[position])
        return mViewList[position]
    }

    //移除
    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(mViewList[position])
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return  view == `object`
    }

    override fun getCount(): Int {
        return if (mViewList.isEmpty()) 0 else mViewList.size
    }
}
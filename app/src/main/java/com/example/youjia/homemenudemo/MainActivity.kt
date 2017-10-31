package com.example.youjia.homemenudemo

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val titleName = arrayOf("咖啡", "汉堡", "蛋糕", "面条", "食物", "咖啡", "汉堡", "蛋糕", "面条", "食物")
    val iconArray = arrayOf(R.mipmap.cafe, R.mipmap.humberger, R.mipmap.cake, R.mipmap.noodles, R.mipmap.food,
            R.mipmap.cafe, R.mipmap.humberger, R.mipmap.cake, R.mipmap.noodles, R.mipmap.food)


    val mList: MutableList<Model> = mutableListOf()
    val mViewList: MutableList<View> = mutableListOf()
    val item_count = 8
    private var page_size: Int = 0//页数
    private var curIndex: Int = 0//目前选择的页

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initData()//初始化数据
        initPage()
    }

    private fun initPage() {
        //总页数 = 总数/每页个数 并取整
        page_size = Math.ceil(mList.size * 1.0 / item_count).toInt()
        for (i in 0 until page_size) {
            val gridview: GridView = LayoutInflater.from(this).inflate(R.layout.gridview_layout, view_pager, false) as GridView
            gridview.adapter = GridViewAdapter(this, mList, i, item_count)
            mViewList.add(gridview)
            gridview.onItemClickListener = AdapterView.OnItemClickListener { p0, view, position, id ->
                Toast.makeText(applicationContext, mList[id.toInt()].title, Toast.LENGTH_SHORT).show()
            }
        }
        view_pager.adapter = ViewpagerAdapter(mViewList)

        setPointLayout()
    }

    @SuppressLint("InflateParams")
    private fun setPointLayout() {
        val inflate = LayoutInflater.from(this)
        for (i in 0 until page_size) {
            point.addView(inflate.inflate(R.layout.point_layout, null))
        }
        // 默认显示第一页
        point.getChildAt(0).findViewById<ImageView>(R.id.im_dot).setImageResource(R.mipmap.dot_red)
        view_pager.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
            }

            override fun onPageSelected(position: Int) {
                // 取消圆点选中
                point.getChildAt(curIndex).findViewById<ImageView>(R.id.im_dot).setImageResource(R.mipmap.dot_bule)
                // 圆点选中
                point.getChildAt(position).findViewById<ImageView>(R.id.im_dot).setImageResource(R.mipmap.dot_red)

                curIndex = position
            }

        })
    }

    private fun initData() {
        titleName.indices.mapTo(mList) { Model(iconArray[it], titleName[it]) }
    }
}

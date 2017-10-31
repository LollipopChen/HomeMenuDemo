package com.example.youjia.homemenudemo

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

/**
 * author:CQE
 * Date: 2017/9/28.
 */
class GridViewAdapter(val context: Context,
                      val mList: MutableList<Model>,
                      val curIndex: Int,
                      val itemCount: Int
) : BaseAdapter() {

    override fun getView(position: Int, contentview: View?, parent: ViewGroup): View {
//        mList.let {
        Log.e("GridViewAdapter", "$mList,$curIndex,$itemCount")
        val holder: ViewHolder
        val view: View
        if (contentview == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_button_layout, parent, false)
            holder = ViewHolder()
            holder.im_icon = view.findViewById<ImageView>(R.id.icon)
            holder.tv_title = view.findViewById<TextView>(R.id.title)
            view.tag = holder
        } else {
            view = contentview
            holder = view.tag as ViewHolder
        }
        val pos = position + curIndex * itemCount
        val model = mList[pos]
        model.icon?.let { it1 -> holder.im_icon.setImageResource(it1) }
        holder.tv_title.text = model.title
//        }

        return view
    }

    override fun getItem(position: Int): Any {
        return mList[position + curIndex * itemCount]
    }

    override fun getItemId(position: Int): Long {
        return (position + curIndex * itemCount).toLong()
    }

    override fun getCount(): Int {
        //判断当前数据能否填满整页，如果能就返回最大页数，否则返回数量
        return if (mList.size > (curIndex + 1) * itemCount) itemCount else mList.size - curIndex * itemCount
    }

    inner class ViewHolder {
        lateinit var im_icon: ImageView  //= view.findViewById<ImageView>(R.id.icon)
        lateinit var tv_title: TextView //= view.findViewById<TextView>(R.id.title)
    }
}
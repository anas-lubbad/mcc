package com.example.a120181440assignment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import kotlinx.android.synthetic.main.lay1.view.*

class listViewAdapter(var activity: MainActivity, var data:MutableList<users>) :BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null){
            view = LayoutInflater.from(activity).inflate(R.layout.lay1,null,false)
        }
        view!!.name.text = data[position].name
        view!!.email.text = data[position].email

        return view!!
  }

    override fun getItem(position: Int): Any {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return data[position].id.toLong()
    }

    override fun getCount(): Int {
        return data.size
    }
}
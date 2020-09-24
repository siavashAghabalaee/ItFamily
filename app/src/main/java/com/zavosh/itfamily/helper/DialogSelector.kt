package com.zavosh.itfamily.helper

import android.app.Activity
import android.app.AlertDialog
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.widget.AdapterView
import com.zavosh.itfamily.R
import com.zavosh.itfamily.adapters.SelectorAdapter
import kotlinx.android.synthetic.main.dialog_selector.view.*

object DialogSelector {
    fun showDialog(activity: Activity,list: ArrayList<String>,key :String ,callback :SelectorResponse){
        var builder = AlertDialog.Builder(activity)
        var inflater = activity?.getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var rootView = inflater.inflate(R.layout.dialog_selector, null)
        builder.setView(rootView)
        builder.create()
        var show = builder.show()
        show?.getWindow()?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val adapter = SelectorAdapter(activity, list)
        rootView.lv_selector?.adapter = adapter

        rootView.lv_selector?.setOnItemClickListener(AdapterView.OnItemClickListener { parent, view, position, id ->
            callback.callback(position,key)
            show?.dismiss()
        })
    }


    interface SelectorResponse {
        fun callback(result: Int, key: String?)
    }
}
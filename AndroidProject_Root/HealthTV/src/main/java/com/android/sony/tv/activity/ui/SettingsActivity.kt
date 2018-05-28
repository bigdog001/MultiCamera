package com.android.sony.tv.activity.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.android.sony.tv.R
import com.android.sony.tv.activity.base.BaseActivity
import com.android.sony.tv.beans.VariableHolder
import com.hadoopz.MyDroidLib.inject.annotation.ContentView
import com.hadoopz.MyDroidLib.inject.annotation.Event
import com.hadoopz.MyDroidLib.inject.annotation.ViewInject

@ContentView(value = R.layout.health_tv_settings)
class SettingsActivity: BaseActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        VariableHolder.logProvider.d(javaClass.simpleName,"in onClick v is:"+v)
        when (v?.id) {
            R.id.testsettings ->
                Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show()
            else-> Toast.makeText(this, "View type not supported", Toast.LENGTH_SHORT).show()
        }
    }

    @ViewInject(value = R.id.testsettings)
    lateinit var testsettings:Button

    override fun OnStop() {

    }

    override fun initParams() {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        testsettings.setOnClickListener(this)
        VariableHolder.logProvider.d(javaClass.simpleName,"the testsettings:"+testsettings)
    }

//  @Event(value = {R.id.testseting},type = View.OnClickListener.class)
    @Event(value = *intArrayOf(R.id.testsettings)) fun testClik(v: View?) {
    VariableHolder.logProvider.d(javaClass.simpleName,"in viewClickListener v is:"+v)
        when (v?.id) {
            R.id.testsettings ->
                Toast.makeText(this, "点击了", Toast.LENGTH_SHORT).show()
            else-> Toast.makeText(this, "View type not supported", Toast.LENGTH_SHORT).show()
        }
    }
}
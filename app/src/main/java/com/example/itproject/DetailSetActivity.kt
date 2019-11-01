package com.example.itproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import link.fls.swipestack.SwipeStack

class DetailSetActivity : AppCompatActivity() {

    private var mdata:ArrayList<String>?=null
    private val swipestack:SwipeStack?=null
    private val adapter:SwipeStackAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_set)

        mdata=ArrayList()
        fillWithTestData()
    }

    private fun fillWithTestData() {
        for (x in 0..5){
            mdata?.add("com.example.itproject.Text"+(x+1))
        }
    }

}

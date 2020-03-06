package com.zavosh.itfamily.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zavosh.itfamily.R
import com.zavosh.itfamily.adapters.QuestionAdapter
import com.zavosh.itfamily.retrofit.Server
import com.zavosh.itfamily.retrofit.mymodels.Callback
import com.zavosh.itfamily.retrofit.mymodels.questionListlistrequest.QuestionListResult
import kotlinx.android.synthetic.main.fargment_question.view.*

class QuestionFragment: Fragment() {

    private lateinit var rootView : View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView =inflater.inflate(R.layout.fargment_question, container, false)
        setup()
        return rootView
    }

    private fun setup() {

        rootView.img_back.setOnClickListener {
            activity?.onBackPressed()

        }

        getQuestionList()
    }

    private fun getQuestionList() {

        Server.getInstance(activity).getQuestionList(rootView.loader_question,object : Callback.QuestionList {
            override fun callback(result: MutableList<QuestionListResult>?) {

                Log.i("log",""+result?.size)

                var adapter = QuestionAdapter(activity,result)
                rootView.rec_question.layoutManager = LinearLayoutManager(context)
                rootView.rec_question.adapter = adapter

            }
        })


    }


}
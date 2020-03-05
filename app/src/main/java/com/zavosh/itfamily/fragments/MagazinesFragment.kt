package com.zavosh.itfamily.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.zavosh.itfamily.R
import com.zavosh.itfamily.retrofit.Server
import com.zavosh.itfamily.retrofit.mymodels.Callback
import com.zavosh.itfamily.retrofit.mymodels.magazinerequest.MagazineResult

/**
 * A simple [Fragment] subclass.
 */
class MagazinesFragment : Fragment() {
    private lateinit var rootView : View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView =inflater.inflate(R.layout.fragment_magazines, container, false)
        setup()
        return rootView
    }

    private fun setup() {
        getList()
    }

    private fun getList() {
        /*Server.getInstance(context).getMagazineList(loader,object : Callback.MagazineList{loadero ezafe koni hale
            override fun callback(result: MutableList<MagazineResult>?) {

            }
        })*/
    }


}

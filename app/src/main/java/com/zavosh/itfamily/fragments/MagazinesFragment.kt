package com.zavosh.itfamily.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zavosh.itfamily.R
import com.zavosh.itfamily.adapters.MagazineAdapter
import com.zavosh.itfamily.retrofit.Server
import com.zavosh.itfamily.retrofit.mymodels.Callback
import com.zavosh.itfamily.retrofit.mymodels.magazinerequest.MagazineResult
import kotlinx.android.synthetic.main.fragment_magazines.view.*

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

        Server.getInstance(context).getMagazineList(rootView.loader_magazine,
            object : Callback.MagazineList {
                override fun callback(result: MutableList<MagazineResult>?) {

                    var adapter = MagazineAdapter(activity,result)
                    rootView.recyclerView_magazine.layoutManager = LinearLayoutManager(context)
                    rootView.recyclerView_magazine.adapter = adapter


                }
            })
    }


}

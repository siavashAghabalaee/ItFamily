package com.zavosh.itfamily.fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.zavosh.itfamily.R
import com.zavosh.itfamily.activities.HomeActivity
import com.zavosh.itfamily.adapters.HomeAdapters
import com.zavosh.itfamily.helper.Memory
import com.zavosh.itfamily.helper.PublicMethods
import com.zavosh.itfamily.retrofit.Server
import com.zavosh.itfamily.retrofit.mymodels.Callback
import kotlinx.android.synthetic.main.fragment_home.view.*

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {
    private lateinit var rootView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home, container, false)
        setup()
        return rootView
    }

    private fun setup() {

        rootView.img_menu.setOnClickListener {

            HomeActivity.drawer.openDrawer()

        }

        Log.i("tokennnnnnn", Memory.loadToken())
        getHomeData()
    }


    private fun getHomeData() {
        Log.i("aeaijwdaiojd", "1")
        Server.getInstance(context!!)
            .getHome(PublicMethods.getAppVersion(context!!), "Android", rootView.loader_home,
                Callback.Home {
                    Log.i("aeaijwdaiojd", "ok")
                    var adapter = HomeAdapters(
                        activity,
                        it.sliderContents,
                        it.video,
                        it.podcasts,
                        it.blogContent,
                        it.magzines
                    )
                    rootView.recyclerView_home.layoutManager = LinearLayoutManager(context)
                    rootView.recyclerView_home.adapter = adapter

                })
    }
}

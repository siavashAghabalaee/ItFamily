package com.zavosh.itfamily.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import com.zavosh.itfamily.R
import com.zavosh.itfamily.adapters.GroupDetailsAdapter
import com.zavosh.itfamily.helper.Memory
import com.zavosh.itfamily.retrofit.Server
import com.zavosh.itfamily.retrofit.mymodels.Callback
import com.zavosh.itfamily.retrofit.mymodels.grouprequest.GroupDetails
import kotlinx.android.synthetic.main.fragment_group_details.view.*
import kotlinx.android.synthetic.main.fragment_group_details.view.img_back
import kotlinx.android.synthetic.main.fragment_group_details.view.menu
import kotlinx.android.synthetic.main.fragment_group_details.view.recyclerView_group
import kotlinx.android.synthetic.main.fragment_group_list.view.*


class GroupDetailsFragment : Fragment() {
    private lateinit var fragmentView :View
    private var list = ArrayList<GroupDetails>()
    private var listHolder = ArrayList<GroupDetails>()
    private var QUERY = "QUERY"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentView = inflater.inflate(R.layout.fragment_group_details, container, false)
        setup()
        return fragmentView
    }

    private fun setup() {

        fragmentView.menu.setOnClickListener {
            activity?.findViewById<DrawerLayout>(R.id.mDrawerLayout)!!.openDrawer(GravityCompat.END)
        }

        fragmentView.img_back.setOnClickListener {

            activity?.onBackPressed()
        }


        fragmentView.tabLayout?.getTabAt(3)?.select()

        getGroup()
        listeners()
    }

    private fun listeners() {

        fragmentView.tabLayout?.setOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {

                list.clear()
                when (tab?.position){
                    0 ->{
                        list.addAll(listHolder.filter {
                            it.type.equals("blog")
                        })
                    }
                    1 ->{
                        list.addAll(listHolder.filter {
                            it.type.equals("podcast")
                        })
                    }
                    2 ->{
                        list.addAll(listHolder.filter {
                            it.type.equals("video")
                        })
                    }
                    3 ->{
                        list.addAll(listHolder)
                    }
                }

                fragmentView.recyclerView_group?.adapter?.notifyDataSetChanged()
            }

        })
    }

    private fun getGroup() {

        var query = arguments?.getString(QUERY)?:""
        if (query.isEmpty()) {
            var id = arguments?.getString("id")
            Server.getInstance(context).getGroup(fragmentView?.loader_group,id){
                listHolder.clear()
                list.clear()
                listHolder.addAll(it)
                list.addAll(listHolder)
                fragmentView?.recyclerView_group?.layoutManager = LinearLayoutManager(activity)
                fragmentView?.recyclerView_group?.adapter = GroupDetailsAdapter(activity,list)
        }
        }else{
            Server.getInstance(context).search(fragmentView?.loader_group,query, object : Callback.SetGroupDetails{
                override fun callback(listB: java.util.ArrayList<GroupDetails>?) {
                    listHolder.clear()
                    list?.clear()
                    listHolder.addAll(listB!!)
                    list.addAll(listHolder)
                    fragmentView?.recyclerView_group?.layoutManager = LinearLayoutManager(activity)
                    fragmentView?.recyclerView_group?.adapter = GroupDetailsAdapter(activity,list)

                    if (listB.size == 0)
                        fragmentView?.tv_not_found?.visibility = View.VISIBLE
                }
            })
        }
    }

}

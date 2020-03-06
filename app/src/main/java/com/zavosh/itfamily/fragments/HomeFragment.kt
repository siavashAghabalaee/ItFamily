package com.zavosh.itfamily.fragments


import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.zavosh.itfamily.R
import com.zavosh.itfamily.activities.HomeActivity
import com.zavosh.itfamily.adapters.HomeAdapters
import com.zavosh.itfamily.helper.Memory
import com.zavosh.itfamily.helper.PageManager
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

        initDrawer()

        Log.i("tokennnnnnn", Memory.loadToken())
        getHomeData()
    }

    private fun initDrawer() {

        val item1 = PrimaryDrawerItem().withIdentifier(1).withName("لیست ویدیوها")
            .withIcon(R.mipmap.app_icon)

        val item2 =
            SecondaryDrawerItem().withIdentifier(2).withName("لیست پادکست ها")
                .withIcon(R.mipmap.app_icon)


        val item3 =
            SecondaryDrawerItem().withIdentifier(3).withName("سوالات متداول")
                .withIcon(R.mipmap.app_icon)

        val item4 =
            SecondaryDrawerItem().withIdentifier(4).withName("پشتیبانی").withIcon(R.mipmap.app_icon)


        val drawer = DrawerBuilder()
            .withActivity(activity as HomeActivity)
            .addDrawerItems(
                item1,
                item2,
                item3,
                item4
            )
            .withDrawerGravity(Gravity.END)
            .build()

        drawer.onDrawerItemClickListener = object : Drawer.OnDrawerItemClickListener {
            override fun onItemClick(
                view: View?,
                position: Int,
                drawerItem: IDrawerItem<*>
            ): Boolean {

                when (position) {
                    0 -> PageManager.getInstance().goVideoListFragment()
                    1 -> PageManager.getInstance().goPodcastFragment()
                    2 -> PageManager.getInstance().goQuestionFragment()
                    3 -> PageManager.getInstance().goSupportFragment()
                }
                return false
            }

        }

        drawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true

        rootView.img_menu.setOnClickListener {

            drawer.openDrawer()

        }




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

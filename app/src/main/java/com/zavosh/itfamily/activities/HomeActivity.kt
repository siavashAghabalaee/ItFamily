package com.zavosh.itfamily.activities

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.zavosh.itfamily.R
import com.zavosh.itfamily.activities.ar.ArLauncherActivity
import com.zavosh.itfamily.helper.PageManager
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() {


    companion object {
        fun getInstance(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        setup()
        listeners()
    }

    private fun listeners() {
//        btnTab.setOnClickMenuListener {
//            when (it.id) {
//                1 -> {
//                    Log.i("tabId", "1")
//                    PageManager.getInstance(this@HomeActivity, supportFragmentManager)
//                        .goHomeFragment()
//                }
//                2 -> {
//                    Log.i("tabId", "2")
//                    PageManager.getInstance(this@HomeActivity, supportFragmentManager)
//                        .goProfileFragment()
//                }
//                3 -> {
//                    Log.i("tabId", "3")
//                    PageManager.getInstance(this@HomeActivity, supportFragmentManager)
//                        .goMagazinesFragment()
//                }
//                4 -> {
//                    Log.i("tabId", "4")
//
//                    PageManager.getInstance(this@HomeActivity, supportFragmentManager)
//                        .goBlogsFragment()
//                }
//            }
//        }

        nNavigationView?.setNavigationItemSelectedListener {
            mDrawerLayout.closeDrawer(GravityCompat.END)
            when (it.itemId) {
                R.id.navigation_item_1 -> {
                    Log.i("tabId", "1")
                    PageManager.getInstance(this@HomeActivity, supportFragmentManager)
                        .goVideoListFragment()

                    //it.isChecked = true
                }
                R.id.navigation_item_2 -> {
                    Log.i("tabId", "2")
                    PageManager.getInstance(this@HomeActivity, supportFragmentManager)
                        .goPodcastFragment()

                    //it.isChecked = true
                }
                R.id.navigation_item_3 -> {
                    Log.i("tabId", "3")
                    PageManager.getInstance(this@HomeActivity, supportFragmentManager)
                        .goQuestionFragment()

                    //it.isChecked = true
                }
                R.id.navigation_item_4 -> {
                    Log.i("tabId", "4")

                    PageManager.getInstance(this@HomeActivity, supportFragmentManager)
                        .goSupportFragment()

                    //it.isChecked = true
                }
                R.id.navigation_item_5 ->{
                    startActivity(Intent(this@HomeActivity,ArLauncherActivity::class.java))

                    //it.isChecked = true
                }
            }

            return@setNavigationItemSelectedListener true
        }
    }

    private fun setup() {

        PageManager.getInstance(this@HomeActivity, supportFragmentManager).goHomeFragment()

        /*val item1 = PrimaryDrawerItem().withIdentifier(1).withName("لیست ویدیوها")
            .withIcon(R.mipmap.app_icon).withOnDrawerItemClickListener(this)

        val item2 =
            SecondaryDrawerItem().withIdentifier(2).withName("لیست پادکست ها").withIcon(R.mipmap.app_icon)
                .withOnDrawerItemClickListener(this)

        val item3 =
            SecondaryDrawerItem().withIdentifier(3).withName("سوالات متداول").withIcon(R.mipmap.app_icon)
                .withOnDrawerItemClickListener(this)

        val item4 =
            SecondaryDrawerItem().withIdentifier(4).withName("پشتیبانی").withIcon(R.mipmap.app_icon)
                .withOnDrawerItemClickListener(this)*/






        supportActionBar?.setDisplayShowHomeEnabled(false)


       /* img_menu.setOnClickListener {
            drawer.openDrawer()
        }*/




    }

    override fun onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.END)){
            mDrawerLayout.closeDrawer(GravityCompat.END)
        }else{
            super.onBackPressed()
        }

    }
}

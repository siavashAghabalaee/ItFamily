package com.zavosh.itfamily.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.zavosh.itfamily.R
import com.zavosh.itfamily.helper.PageManager
import com.zavosh.itfamily.retrofit.ApiUtils
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
        btnTab.setOnClickMenuListener {
            when (it.id) {
                1 -> {
                    Log.i("tabId", "1")
                    PageManager.getInstance(this@HomeActivity, supportFragmentManager)
                        .goHomeFragment()
                }
                2 -> {
                    Log.i("tabId", "2")
                    PageManager.getInstance(this@HomeActivity, supportFragmentManager)
                        .goProfileFragment()
                }
                3 -> {
                    Log.i("tabId", "3")
                    PageManager.getInstance(this@HomeActivity, supportFragmentManager)
                        .goMagazinesFragment()
                }
                4 -> {
                    Log.i("tabId", "4")
                    PageManager.getInstance(this@HomeActivity, supportFragmentManager)
                        .goBlogsFragment()
                }
            }
        }

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

                    PageManager.getInstance(this@HomeActivity, supportFragmentManager)
                        .goGroupListFragment()
                }
                R.id.navigation_item_6 ->{
//                    var intent = Intent(this@HomeActivity, ArActivity::class.java)
//                    intent.putExtra("base_url",ApiUtils.BASE_URL)
//                    startActivity(intent)TODO start ar
                }
            }

            return@setNavigationItemSelectedListener true
        }
    }

    private fun setup() {

        PageManager.getInstance(this@HomeActivity, supportFragmentManager).goHomeFragment()

        btnTab.add(MeowBottomNavigation.Model(1, R.mipmap.home_icon))
        btnTab.add(MeowBottomNavigation.Model(2, R.mipmap.user_icon))
        btnTab.add(MeowBottomNavigation.Model(3, R.mipmap.magazin_icon))
        btnTab.add(MeowBottomNavigation.Model(4, R.mipmap.news_icon))

        btnTab.show(1)
        PageManager.getInstance(this@HomeActivity, supportFragmentManager).goHomeFragment()







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

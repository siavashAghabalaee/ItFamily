package com.zavosh.itfamily.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zavosh.itfamily.R
import android.graphics.Color.parseColor
import androidx.core.app.ComponentActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.View
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem
import com.zavosh.itfamily.helper.FragmentHandler
import com.zavosh.itfamily.helper.PageManager
import kotlinx.android.synthetic.main.activity_home.*


class HomeActivity : AppCompatActivity() ,Drawer.OnDrawerItemClickListener{
    lateinit var drawer : Drawer
    override fun onItemClick(view: View?, position: Int, drawerItem: IDrawerItem<*>): Boolean {
        Log.i("myDrawer","pos : "+position)
        //drawer.closeDrawer()
        return false
    }


    companion object{
        fun getInstance(context: Context) : Intent {
            return Intent(context,HomeActivity::class.java)
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
            when(it.id){
                1 ->{Log.i("tabId","1")
                    PageManager.getInstance(this@HomeActivity,supportFragmentManager).goHomeFragment()
                }
                2 ->{Log.i("tabId","2")
                    PageManager.getInstance(this@HomeActivity,supportFragmentManager).goProfileFragment()
                }
                3 ->{Log.i("tabId","3")
                    PageManager.getInstance(this@HomeActivity,supportFragmentManager).goMagazinesFragment()
                }
                4 ->{Log.i("tabId","4")

                    PageManager.getInstance(this@HomeActivity,supportFragmentManager).goArticlesFragment()
                }
            }
        }
    }

    private fun setup() {

        btnTab.add(MeowBottomNavigation.Model(1, R.drawable.home_icon))
        btnTab.add(MeowBottomNavigation.Model(2, R.drawable.user_icon))
        btnTab.add(MeowBottomNavigation.Model(3, R.drawable.magazine_icon))
        btnTab.add(MeowBottomNavigation.Model(4, R.drawable.news_icon))

        btnTab.show(1)
        PageManager.getInstance(this@HomeActivity,supportFragmentManager).goHomeFragment()

        val item1 = PrimaryDrawerItem().withIdentifier(1).withName("سیاوش").withIcon(R.mipmap.app_icon).withOnDrawerItemClickListener(this)
        val item2 = SecondaryDrawerItem().withIdentifier(2).withName("sia2").withIcon(R.mipmap.app_icon).withOnDrawerItemClickListener(this)

        val drawer = DrawerBuilder()
            .withActivity(this)
            .addDrawerItems(
                item1,
                item1,
                item1
            )
            .withDrawerGravity(Gravity.END)
            .build()



        supportActionBar?.setDisplayShowHomeEnabled(false)
        drawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true

    }
}

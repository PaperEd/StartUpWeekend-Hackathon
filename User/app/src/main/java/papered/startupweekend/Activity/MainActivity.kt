package papered.startupweekend.Activity

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.FragmentStatePagerAdapter
import android.view.View
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*
import papered.startupweekend.Fragment.LocationFragment
import papered.startupweekend.Fragment.MainFragment
import papered.startupweekend.Fragment.OptionFragment
import papered.startupweekend.R
import papered.startupweekend.R.id.main_bottomNavigationView
import papered.startupweekend.R.id.main_viewPager

class MainActivity : AppCompatActivity() {

    val fragment: Fragment by lazy {
        Fragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        main_viewPager.adapter = PagerAdapter(supportFragmentManager)
        main_viewPager.currentItem = 0

        main_bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_main -> {
                    main_viewPager.currentItem = 0
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_location -> {
                    main_viewPager.currentItem = 1
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.action_option -> {
                    main_viewPager.currentItem = 2
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }
}

private class PagerAdapter(fm: android.support.v4.app.FragmentManager) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): android.support.v4.app.Fragment? {
        return when (position) {
            0 -> {
                MainFragment()
            }
            1 -> LocationFragment()
            2 -> OptionFragment()
            else -> null
        }
    }

    override fun getCount(): Int = 3
}



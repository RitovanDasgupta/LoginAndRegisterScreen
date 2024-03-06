package com.example.loginandregisterscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import nl.joery.animatedbottombar.AnimatedBottomBar

class MainActivity : AppCompatActivity() {
    private lateinit var bottomBar: AnimatedBottomBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomBar=findViewById(R.id.bottom_bar)

        replaceFragment(HomeFragment())
        bottomBar.setOnTabSelectListener(object : AnimatedBottomBar.OnTabSelectListener{
            override fun onTabSelected(
                lastIndex: Int,
                lastTab: AnimatedBottomBar.Tab?,
                newIndex: Int,
                newTab: AnimatedBottomBar.Tab
            ) {

                when(newIndex){

                    0->replaceFragment(HomeFragment())
                    1->replaceFragment(OrderFragment())
                    2->replaceFragment(ProfileFragment())

                    else->{

                    }
                }
            }

        })

    }

    fun replaceFragment(fragment: Fragment){

        supportFragmentManager.beginTransaction().replace(R.id.fragments,fragment).commit()
    }
}
package com.example.newsletter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.newsletter.fragments.AboutUsFragment
import com.example.newsletter.fragments.FavorisFragment
import com.example.newsletter.fragments.HomeFragment

class MainActivity : AppCompatActivity(),
        NavigationListener {
    private lateinit var toolbar: Toolbar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        showFragment(HomeFragment())
    }

    override fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun showFragmentinFragment(id:Int, fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(id, fragment)
            addToBackStack(null)
        }.commit()
    }

    override fun updateTitle(title: Int) {
        toolbar.setTitle(title)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        menu.findItem(R.id.toolbar_btn_home).isVisible = true

        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle item selection
        return when (item.itemId) {
            R.id.btn_aboutus -> {
                showFragment(AboutUsFragment())
                updateTitle(R.string.aboutus)
                true
            }
            R.id.toolbar_btn_favoris -> {
                showFragment(FavorisFragment())
                updateTitle(R.string.favorite)
                true
            }
            R.id.toolbar_btn_home -> {
                showFragment(HomeFragment())
                updateTitle(R.string.home)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}
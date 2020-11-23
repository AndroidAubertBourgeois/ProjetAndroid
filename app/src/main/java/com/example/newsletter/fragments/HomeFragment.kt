package com.example.newsletter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.newsletter.NavigationListener
import com.example.newsletter.R

class HomeFragment : Fragment(){

    lateinit var tousArticles : TextView
    lateinit var aboutUs : Button
    /**
     * Fonction permettant de définir une vue à attacher à un fragment
     */
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.home_fragment, container, false)
        tousArticles = view.findViewById(R.id.text_last_categories)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? NavigationListener)?.let {
            it.showFragmentinFragment(R.id.fragment_list_categories, ListCategoryFragment())
            it.updateTitle(R.string.home)
        }

        tousArticles.setOnClickListener {
            (activity as? NavigationListener)?.let {
                it.showFragment(ListArticlesFragment("actuality"))
                it.updateTitle(R.string.list_articles)
            }
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.toolbar_btn_home).isVisible = false
        super.onPrepareOptionsMenu(menu)
    }



}
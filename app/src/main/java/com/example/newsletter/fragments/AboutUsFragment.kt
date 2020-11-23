package com.example.newsletter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newsletter.R

class AboutUsFragment :Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        val view = inflater.inflate(R.layout.aboutus_fragment, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(R.id.btn_aboutus).isVisible = false
        menu.findItem(R.id.toolbar_btn_favoris).isVisible = false
        menu.findItem(R.id.toolbar_btn_home).isVisible = true
        super.onPrepareOptionsMenu(menu)
    }

}
package org.wit.hillfort.views.favourites

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_hillfort_list.*
import org.wit.hillfort.R
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.views.BaseView
import org.wit.hillfort.views.hillfortlist.HillfortAdapter
import org.wit.hillfort.views.hillfortlist.HillfortListener


class HillfortListViewForFavourites :  BaseView(), HillfortListener {

    lateinit var presenter: HillfortListPresenterForFavourites
    val showedList  = mutableListOf<HillfortModel>()
    val favhillforts = mutableListOf<HillfortModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hillfort_list)
        setSupportActionBar(toolbar)
        super.init(toolbar, false)

        presenter = initPresenter(HillfortListPresenterForFavourites(view = this)) as HillfortListPresenterForFavourites

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        presenter.loadHillforts()


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomAdd-> presenter.doAddHillfort()
                R.id.bottomMain-> presenter.doShowHillfortsList()
                R.id.bottomFav-> presenter.doShowHillfortsFav()
            }
            true
        }
    }

    override fun showHillforts(hillforts: List<HillfortModel>) {


        for(item in hillforts){
            if(item.favourite){
                favhillforts.add(item)
            }
        }
        showedList.addAll(favhillforts)
        recyclerView.adapter = HillfortAdapter(showedList, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.item_add-> presenter.doAddHillfort()
            R.id.item_map -> presenter.doShowHillfortsMap()
            R.id.item_logout -> presenter.doLogout()
        }
        return super.onOptionsItemSelected(item)
    }




    override fun onHillfortClick(hillfort: HillfortModel) {
        presenter.doEditHillfort(hillfort)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        presenter.loadHillforts()
        super.onActivityResult(requestCode, resultCode, data)
    }
}
package org.wit.hillfort.views.hillfortlist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_hillfort_list.*
import org.wit.hillfort.R
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.views.BaseView


class HillfortListView :  BaseView(), HillfortListener {

    lateinit var presenter: HillfortListPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hillfort_list)
        setSupportActionBar(toolbar)
        super.init(toolbar, false)

        presenter = initPresenter(HillfortListPresenter(this)) as HillfortListPresenter

        val layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        presenter.loadHillforts()


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomAdd-> presenter.doAddHillfort()
                R.id.bottomMain-> presenter.loadHillforts()
                R.id.bottomMap-> presenter.doShowHillfortsMap()
            }
            true
        }
    }

    override fun showHillforts(hillforts: List<HillfortModel>) {
        recyclerView.adapter = HillfortAdapter(hillforts, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.bottom_nav_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.bottomAdd-> presenter.doAddHillfort()
/*            R.id.item_map -> presenter.doShowHillfortsMap()
            R.id.item_logout -> presenter.doLogout()*/
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
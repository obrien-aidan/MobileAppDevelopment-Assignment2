package org.wit.hillfort.views.hillfortlist

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_hillfort_list.*
import org.wit.hillfort.R
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.views.BaseView
import java.util.*


class HillfortListView :  BaseView(), HillfortListener {

    lateinit var presenter: HillfortListPresenter
    val showedList  = mutableListOf<HillfortModel>()
    val temphillforts = mutableListOf<HillfortModel>()
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
                R.id.bottomMain-> presenter.doShowHillfortsList()
/*
                R.id.bottomMap-> presenter.doShowHillfortsMap()
*/
                R.id.bottomFav-> presenter.doShowHillfortsFav()
            }
            true
        }
    }

/*    override fun showHillforts(hillforts: List<HillfortModel>) {
        recyclerView.adapter = HillfortAdapter(hillforts, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }*/


/*
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }
*/

    override fun showHillforts(hillforts: List<HillfortModel>) {

        for(item in hillforts){

            temphillforts.add(item)
        }
        showedList.addAll(temphillforts)
        recyclerView.adapter = HillfortAdapter(showedList, this)
        recyclerView.adapter?.notifyDataSetChanged()
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val menuItem = menu!!.findItem(R.id.menu_search)
        if(menuItem!=null){
            val search = menuItem.actionView as SearchView
            search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(query: String?): Boolean {
                    TODO("Not yet implemented")
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText!!.isNotEmpty()){
                        showedList.clear()
                        val search = newText.toLowerCase(Locale.getDefault())
                        for(item in temphillforts){
                            if(item.title.toLowerCase(Locale.getDefault()).contains(search)){
                                showedList.add(item)
                            }

                        }
                        recyclerView.adapter!!.notifyDataSetChanged()


                    }

                    else{

                        showedList.clear()
                        showedList.addAll(temphillforts)
                        recyclerView.adapter!!.notifyDataSetChanged()


                    }

                    return true
                }

            })

        }
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

/*
reference for search:
https://www.youtube.com/watch?v=rdu1ZqM9rSE
https://www.androidhive.info/2017/11/android-recyclerview-with-search-filter-functionality/
*/


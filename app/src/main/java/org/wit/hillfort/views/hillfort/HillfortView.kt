package org.wit.hillfort.views.hillfort

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.bumptech.glide.Glide
import com.google.android.gms.maps.GoogleMap
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_hillfort.*
import kotlinx.android.synthetic.main.activity_hillfort_list.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.hillfort.R
import org.wit.hillfort.helpers.readImageFromPath
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.models.Location
import org.wit.hillfort.views.BaseView


class HillfortView : BaseView(), AnkoLogger {

    lateinit var presenter: HillfortPresenter
    var hillfort = HillfortModel()
    lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hillfort)

        super.init(toolbarAdd, false);

        mapView.onCreate(savedInstanceState);
        mapView.getMapAsync {
            map = it
            presenter.doConfigureMap(map)
            it.setOnMapClickListener { presenter.doSetLocation() }
        }

        presenter = initPresenter (HillfortPresenter(this)) as HillfortPresenter

        chooseImage.setOnClickListener {
            presenter.cacheHillfort(hillfortTitle.text.toString(), description.text.toString(), rating1.rating,favourite1.isChecked)
            presenter.doSelectImage()
        }
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.bottomAdd-> presenter.doAddHillfort()
                R.id.bottomMain-> presenter.doShowHillfortsList()
                R.id.bottomFav-> presenter.doShowHillfortsFav()
            }
            true
        }

/*        hillfortLocation.setOnClickListener {
            presenter.cacheHillfort(hillfortTitle.text.toString(), description.text.toString())
            presenter.doSetLocation()
        }*/
    }

    override fun showHillfort(hillfort: HillfortModel) {
        if (hillfortTitle.text.isEmpty()) hillfortTitle.setText(hillfort.title)
        if (description.text.isEmpty())  description.setText(hillfort.description)
        rating1.setRating(hillfort.rating)
        favourite1.setChecked(hillfort.favourite)

        Glide.with(this).load(hillfort.image).into(hillfortImage);


        if (hillfort.image != null) {
            chooseImage.setText(R.string.change_hillfort_image)
        }
        this.showLocation(hillfort.location)
    }
    override fun showLocation (loc : Location) {
        lat.setText("%.6f".format(loc.lat))
        lng.setText("%.6f".format(loc.lng))
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_hillfort, menu)
        if (presenter.edit) menu.getItem(0).setVisible(true)
        return super.onCreateOptionsMenu(menu)
    }

/*    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.item_delete -> {
                presenter.doDelete()
            }

            R.id.item_save -> {
                hillfort.description = description.text.toString()
                hillfort.rating = rating1.getRating()
                if (hillfort.title.isEmpty()) {
                    if (hillfort.title.isEmpty()) {
                        toast(R.string.enter_hillfort_title)
                    }
                    presenter.doAddOrSave(hillfort.title, hillfort.description, hillfort.rating)
                }
            }
            R.id.item_cancel -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }*/
override fun onOptionsItemSelected(item: MenuItem): Boolean {
    when (item?.itemId) {
        R.id.item_delete -> {
            presenter.doDelete()
        }
        R.id.item_save -> {
            if (hillfortTitle.text.toString().isEmpty()) {
                toast(R.string.enter_hillfort_title)
            } else {
                presenter.doAddOrSave(hillfortTitle.text.toString(), description.text.toString(),rating1.getRating(),favourite1.isChecked())
            }
        }
        R.id.item_cancel -> {
            presenter.doCancel()
        }
    }
    return super.onOptionsItemSelected(item)
}

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            presenter.doActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onBackPressed() {
        presenter.doCancel()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        presenter.doResartLocationUpdates()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
}
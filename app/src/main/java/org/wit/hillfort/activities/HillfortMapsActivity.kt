package org.wit.hillfort.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import org.wit.hillfort.R
import kotlinx.android.synthetic.main.activity_hillfort_maps.*
import org.wit.hillfort.helpers.readImageFromPath
import org.wit.hillfort.main.MainApp

class HillfortMapsActivity : AppCompatActivity(), GoogleMap.OnMarkerClickListener {

    lateinit var app: MainApp
    lateinit var map: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hillfort_maps)
        setSupportActionBar(toolbar)
        mapView.onCreate(savedInstanceState);
        app = application as MainApp

        mapView.getMapAsync {
            map = it
            configureMap()
        }
    }

    fun configureMap() {
        map.uiSettings.setZoomControlsEnabled(true)
        app.hillforts.findAll().forEach {
            val loc = LatLng(it.lat, it.lng)
            val options = MarkerOptions().title(it.title).position(loc)
            map.addMarker(options).tag = it.id
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(loc, it.zoom))
            map.setOnMarkerClickListener(this)

        }
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
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }
    override fun onMarkerClick(marker: Marker): Boolean {
        val tag = marker.tag as Long
        val hillfort = app.hillforts.findById(tag)
        currentTitle.text = hillfort!!.title
        currentDescription.text = hillfort!!.description
        currentImage.setImageBitmap(readImageFromPath(this, hillfort.image))
        return true
    }
}
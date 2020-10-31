package org.wit.hillfort.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfort.models.HillfortModel
import android.view.*
import org.wit.hillfort.models.HillfortMemStore


class MainApp : Application(), AnkoLogger {
/*
    val hillfortsorts = ArrayList<HillfortModel>()
*/
    val hillforts = HillfortMemStore()


    override fun onCreate() {
        super.onCreate()
        info("Hillfort started")

//        hillforts.add(HillfortModel("One", "About one..."))
//        hillforts.add(HillfortModel("Two", "About two..."))
//        hillforts.add(HillfortModel("Three", "About three..."))
    }
}
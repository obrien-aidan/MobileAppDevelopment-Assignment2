package org.wit.hillfort.main

import android.app.Application
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.wit.hillfort.models.HillfortModel
import android.view.*
import org.wit.hillfort.models.HillfortMemStore


class MainApp : Application(), AnkoLogger {

    val hillforts = HillfortMemStore()


    override fun onCreate() {
        super.onCreate()
        info("Hillfort started")

    }
}
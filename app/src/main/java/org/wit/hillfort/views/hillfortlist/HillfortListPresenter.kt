package org.wit.hillfort.views.hillfortlist

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.views.BasePresenter
import org.wit.hillfort.views.BaseView
import org.wit.hillfort.views.VIEW

class HillfortListPresenter(view: BaseView) : BasePresenter(view) {

    fun doAddHillfort() {
        view?.navigateTo(VIEW.HILLFORT)
    }

    fun doEditHillfort(hillfort: HillfortModel) {
        view?.navigateTo(VIEW.HILLFORT, 0, "hillfort_edit", hillfort)
    }

    fun doShowHillfortsMap() {
        view?.navigateTo(VIEW.MAPS)
    }

    fun loadHillforts() {
        view?.navigateTo(VIEW.LIST)
    }
    fun doLogout() {
        FirebaseAuth.getInstance().signOut()
        app.hillforts.clear()
        view?.navigateTo(VIEW.LOGIN)
    }
}
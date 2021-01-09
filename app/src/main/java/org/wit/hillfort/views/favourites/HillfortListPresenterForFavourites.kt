package org.wit.hillfort.views.favourites

import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.views.BasePresenter
import org.wit.hillfort.views.BaseView
import org.wit.hillfort.views.VIEW

class HillfortListPresenterForFavourites(view: BaseView) : BasePresenter(view) {

    fun doAddHillfort() {
        view?.navigateTo(VIEW.HILLFORT)
    }

    fun doEditHillfort(hillfort: HillfortModel) {
        view?.navigateTo(VIEW.HILLFORT, 0, "hillfort_edit", hillfort)
    }

    fun loadHillforts() {
        doAsync {
            val hillforts = app.hillforts.findAll()
            uiThread {
                view?.showHillforts(hillforts)
            }
        }
    }

    fun doShowHillfortsFav() {
        view?.navigateTo(VIEW.FAVLIST)
    }
    fun doShowHillfortsList() {
        view?.navigateTo(VIEW.LIST)
    }
    fun doShowHillfortsMap() {
        view?.navigateTo(VIEW.MAPS)
    }
    fun doLogout() {
        FirebaseAuth.getInstance().signOut()
        app.hillforts.clear()
        view?.navigateTo(VIEW.LOGIN)
    }
}
package org.wit.hillfort.views

import android.content.Intent
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import org.jetbrains.anko.AnkoLogger
import org.wit.hillfort.activities.SplashScreenActivity
import org.wit.hillfort.models.Location
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.views.location.EditLocationView
import org.wit.hillfort.views.map.HillfortMapView
import org.wit.hillfort.views.hillfort.HillfortView
import org.wit.hillfort.views.hillfortlist.HillfortListView
import org.wit.hillfort.views.favourites.HillfortListViewForFavourites
import org.wit.hillfort.views.login.LoginView

val IMAGE_REQUEST = 1
val LOCATION_REQUEST = 2

enum class VIEW {
    LOCATION, HILLFORT, MAPS, LIST, LOGIN, SPLASHSCREEN, FAVLIST
}

open abstract class BaseView() : AppCompatActivity(), AnkoLogger {

    var basePresenter: BasePresenter? = null

    fun navigateTo(view: VIEW, code: Int = 0, key: String = "", value: Parcelable? = null) {
        var intent = Intent(this, HillfortListView::class.java)
        when (view) {
            VIEW.LOCATION -> intent = Intent(this, EditLocationView::class.java)
            VIEW.HILLFORT -> intent = Intent(this, HillfortView::class.java)
            VIEW.MAPS -> intent = Intent(this, HillfortMapView::class.java)
            VIEW.LIST -> intent = Intent(this, HillfortListView::class.java)
            VIEW.FAVLIST -> intent = Intent(this, HillfortListViewForFavourites::class.java)

            VIEW.LOGIN -> intent = Intent(this, LoginView::class.java)
            VIEW.SPLASHSCREEN -> intent = Intent(this, SplashScreenActivity::class.java)

        }
        if (key != "") {
            intent.putExtra(key, value)
        }
        startActivityForResult(intent, code)
    }

/*    fun init(toolbar: Toolbar, upEnabled: Boolean) {
        toolbar.title = title
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(upEnabled)
    }*/

    fun initPresenter(presenter: BasePresenter): BasePresenter {
        basePresenter = presenter
        return presenter
    }

    fun init(toolbar: Toolbar) {
        toolbar.title = title
        setSupportActionBar(toolbar)
    }

    fun init(toolbar: Toolbar, upEnabled: Boolean) {
        toolbar.title = title
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(upEnabled)
        val user = FirebaseAuth.getInstance().currentUser
        if (user != null) {
            toolbar.title = "${title} | ${user.email}"
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            basePresenter?.doActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        basePresenter?.doRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    open fun showHillfort(hillfort: HillfortModel) {}
    open fun showHillforts(hillforts: List<HillfortModel>) {}
    open fun showLocation(location : Location) {}
    open fun showProgress() {}
    open fun hideProgress() {}
}
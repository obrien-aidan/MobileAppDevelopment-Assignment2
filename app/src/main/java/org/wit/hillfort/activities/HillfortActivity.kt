package org.wit.hillfort.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_hillfort.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.wit.hillfort.models.HillfortModel
import org.wit.hillfort.R



class HillfortActivity : AppCompatActivity(), AnkoLogger {

    var hillfort = HillfortModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hillfort)
        info("Hillfort Activity started..")

        btnAdd.setOnClickListener() {
            hillfort.title = hillfortTitle.text.toString()
            if (hillfort.title.isNotEmpty()) {
                info("add Button Pressed: $hillfort")
            }
            else {
                toast ("Please Enter a title")
            }
        }
    }
}
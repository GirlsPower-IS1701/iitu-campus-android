package kz.iitu.campus.ui.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_notification.*
import kz.iitu.campus.R

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        setUpViews()
    }

    private fun setUpViews() {
        val title = intent.getStringExtra("title")
        titleText.text = title

        val body = intent.getStringExtra("body")
        bodyText.text = body
    }

}
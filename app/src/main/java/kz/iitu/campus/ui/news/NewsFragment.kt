package kz.iitu.campus.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_news.*
import kz.iitu.campus.R
import kz.iitu.campus.repository.AcademicCalendarRepository
import kz.iitu.campus.services.ApiFactory
import kz.iitu.campus.services.UserSession
import kz.iitu.campus.ui.academ_calendar.CalendarRVA

class NewsFragment: Fragment() {

    private val viewModel by lazy {
        ViewModelProviders.of(
            this, NewsViewModel.NewsFactory(
                AcademicCalendarRepository(
                    ApiFactory.getApi()
                )
            )
        )[NewsViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_news, container, false)
        setupViews()
        setObservers()
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        newsRV.layoutManager =
            LinearLayoutManager(this.context)
    }

    private fun setupViews() {
        val bearer: String = "Bearer " + this.context?.let { UserSession.getUserToken(it) }
        viewModel.getNews(bearer)
    }

    private fun setObservers() {
        viewModel.errorLiveData.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank())
                Toast.makeText(
                    context,
                    it,
                    Toast.LENGTH_LONG
                ).show()
        })
        viewModel.news.observe(viewLifecycleOwner, Observer {
            newsRV.adapter = NewsRVA(it, onClickItem = { item->
                val intent = Intent(activity, NotificationActivity::class.java)
                intent.putExtra("title", item.title)
                intent.putExtra("body", item.body)
                startActivity(intent)
            })
        })
    }
}
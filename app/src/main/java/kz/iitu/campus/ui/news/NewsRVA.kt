package kz.iitu.campus.ui.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_news.view.*
import kotlinx.android.synthetic.main.item_ref_history.view.date
import kz.iitu.campus.R
import kz.iitu.campus.model.model.Notification
import java.text.SimpleDateFormat

class NewsRVA (
    private val list: List<Notification>,
    private val onClickItem: (Notification) -> Unit
) : RecyclerView.Adapter<NewsRVA.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[position], onClickItem)

    override fun getItemCount(): Int = list.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_news, parent, false
            )
        )

    inner class ViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        fun bind(item: Notification, onClickItem: (Notification) -> Unit) {
            view.title.text = item.title
            view.body.text = item.body
            val format = SimpleDateFormat("dd.MM.yyyy hh:mm")
            view.date.text = format.format(item.created_at)

            view.setOnClickListener{
                onClickItem(item)
            }
        }
    }

}
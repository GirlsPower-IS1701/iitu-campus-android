package kz.iitu.campus.ui.academ_calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_academic_calendar.view.*
import kz.iitu.campus.R
import kz.iitu.campus.pojo.academic_calendar.CalendarItem


public class CalendarRVA(
private val list: List<CalendarItem>
) : RecyclerView.Adapter<CalendarRVA.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_academic_calendar, parent, false
            )
        )

    inner class ViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        fun bind(item: CalendarItem) {
            view.title.text = item.name
            view.date.text = item.date
        }
    }

}

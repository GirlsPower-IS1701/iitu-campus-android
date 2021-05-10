package kz.iitu.campus.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_schedule.view.*
import kz.iitu.campus.R
import kz.iitu.campus.model.schedule.ScheduleItem

class ScheduleRVA(
    private val list: List<ScheduleItem>
) : RecyclerView.Adapter<ScheduleRVA.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_schedule, parent, false
            )
        )

    inner class ViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        fun bind(item: ScheduleItem) {
            view.subjectName.text = item.subjectName
            view.group.text = item.group
            view.room.text = item.room
            view.teacher.text = item.teacher_name + " ( " + item.teacher_position + " )"
        }
    }

}

package kz.iitu.campus.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_schedule.view.*
import kz.iitu.campus.R
import kz.iitu.campus.model.schedule.Timetable

class ScheduleRVA(
    private val list: List<Timetable>
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
        fun bind(item: Timetable) {
            view.subjectName.text = item.subject
            view.group.text = "IS_1701K"
            view.room.text = item.room_number
            view.teacher.text = item.teacher_name + " " + item.teacher_surname
            view.startTime.text = item.start_time.take(5)
            view.finishTime.text = item.finish_time.take(5)
        }
    }

}

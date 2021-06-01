package kz.iitu.campus.ui.transcript

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_ref_history.view.*
import kz.iitu.campus.R
import kz.iitu.campus.model.model.StudyPlanHistory
import java.text.SimpleDateFormat

class TranscriptHistoryRVA (
    private val list: List<StudyPlanHistory>
    ) : RecyclerView.Adapter<TranscriptHistoryRVA.ViewHolder>() {

        override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(list[position])

        override fun getItemCount(): Int = list.size
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_study_plan_history, parent, false
                )
            )

        inner class ViewHolder(
            private val view: View
        ) : RecyclerView.ViewHolder(view) {
            fun bind(item: StudyPlanHistory) {

                val format = SimpleDateFormat("dd.MM.yyyy hh:mm")
                view.date.text = format.format(item.created_at)
            }
        }

    }
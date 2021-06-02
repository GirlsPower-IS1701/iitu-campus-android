package kz.iitu.campus.ui.transcript

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_study_plan.view.*
import kz.iitu.campus.R
import kz.iitu.campus.model.model.StudyPlan

class IupRVA(
    private val list: List<StudyPlan>
) : RecyclerView.Adapter<IupRVA.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(list[position])

    override fun getItemCount(): Int = list.size
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_study_plan, parent, false
            )
        )

    inner class ViewHolder(
        private val view: View
    ) : RecyclerView.ViewHolder(view) {
        fun bind(item: StudyPlan) {
            view.title.text = item.subject
            view.rk1.text = getStringLetter(item.pk1) + " (" + item.pk1.toString() + ")"
            view.rk2.text = getStringLetter(item.pk2) + " (" + item.pk2.toString() + ")"
            view.final_grade.text = getStringLetter(item.final) + " (" + item.final.toString() + ")"
            view.gpa.text = item.gpa.toString()
        }
    }

    private fun getStringLetter(grade: Float): String {
        return "F"
    }
}
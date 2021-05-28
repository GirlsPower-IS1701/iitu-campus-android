package kz.iitu.campus.ui.ref

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_ref_history.view.*
import kz.iitu.campus.R
import kz.iitu.campus.model.model.RefHistory

class RefHistoryRVA (
    private val list: List<RefHistory>
    ) : RecyclerView.Adapter<RefHistoryRVA.ViewHolder>() {

        override fun onBindViewHolder(holder: ViewHolder, position: Int) =
            holder.bind(list[position])

        override fun getItemCount(): Int = list.size
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
            ViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_ref_history, parent, false
                )
            )

        inner class ViewHolder(
            private val view: View
        ) : RecyclerView.ViewHolder(view) {
            fun bind(item: RefHistory) {
                view.ref_type.text = item.reference_type_id.name
                view.date.text = item.created_at.time.toString()
            }
        }

    }

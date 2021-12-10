package com.tiketxbncc.dynamicui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListFragment : Fragment() {

    lateinit var myAdapter: ListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_list, container, false)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_list)
        val fabList = root.findViewById<FloatingActionButton>(R.id.fab_list)
        val fabGrid = root.findViewById<FloatingActionButton>(R.id.fab_grid)

        myAdapter = ListAdapter(getContents())
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = myAdapter
        }

        fabGrid.setOnClickListener {
            recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
        }

        fabList.setOnClickListener {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
        }

        return root
    }

    private fun getContents(): List<Content> {
        return listOf(
            Content("Kotlin", R.drawable.ic_android_black_24dp),
            Content("Java", R.drawable.ic_baseline_add_reaction_24),
            Content("Swift", R.drawable.ic_baseline_account_circle_24),
        )
    }
}

class ListAdapter(
    val dataSet: List<Content>
): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.textview_list)
        val imageView = view.findViewById<ImageView>(R.id.image_view)
    }

    class ViewHolder2(view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.text2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            0 -> ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false))
            1 -> ViewHolder2(LayoutInflater.from(parent.context).inflate(R.layout.item_list_2, parent, false))
            else -> throw error("")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val context = holder.itemView.context
        when (holder) {
            is ViewHolder -> {
                holder.textView.text = dataSet[position].name
                holder.imageView.setImageDrawable(context.getDrawable(dataSet[position].icon))
            }
            is ViewHolder2 -> {
                holder.textView.text = dataSet[position].name
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position % 2
    }

    override fun getItemCount() = dataSet.size
}

data class Content(
    val name: String,
    val icon: Int
)


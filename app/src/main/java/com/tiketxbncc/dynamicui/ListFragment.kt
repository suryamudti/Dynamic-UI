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
): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.textview_list)
        val imageView = view.findViewById<ImageView>(R.id.image_view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val context = holder.itemView.context
        holder.textView.text = dataSet[position].name
        holder.imageView.setImageDrawable(context.getDrawable(dataSet[position].icon))
    }

    override fun getItemCount() = dataSet.size
}

data class Content(
    val name: String,
    val icon: Int
)


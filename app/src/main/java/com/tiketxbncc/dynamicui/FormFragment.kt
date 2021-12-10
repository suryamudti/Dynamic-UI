package com.tiketxbncc.dynamicui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout

class FormFragment : Fragment() {

    private val dataList = mutableListOf("")
    private lateinit var formAdapter: TextAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_form, container, false)

        val fab = root.findViewById<FloatingActionButton>(R.id.fab_plus)
        val editText = root.findViewById<EditText>(R.id.edit_text)
        val textInputLayout = root.findViewById<TextInputLayout>(R.id.text_input_layout)
        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_view_form)

        formAdapter = TextAdapter(
            dataList,
            onClick = {
                dataList.removeAt(it)
                formAdapter.notifyItemRemoved(it)
            }
        )
        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = formAdapter
        }

        fab.setOnClickListener {
            textInputLayout.error = null
            if (editText.text.toString().isEmpty()) {
                textInputLayout.error = "opps, tidak boleh kosong"
            } else {
                dataList.add(editText.text.toString())
                editText.text.clear()
                formAdapter.notifyDataSetChanged()
            }
        }

        return root
    }

}

class TextAdapter(
    private val dataList: List<String>,
    private val onClick: (Int) -> Unit
) : RecyclerView.Adapter<TextAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val textView = view.findViewById<TextView>(R.id.text_view_form)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_form_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.textView.text = dataList[position]
        holder.itemView.setOnClickListener {
            onClick(position)
        }
    }

    override fun getItemCount() = dataList.size

}
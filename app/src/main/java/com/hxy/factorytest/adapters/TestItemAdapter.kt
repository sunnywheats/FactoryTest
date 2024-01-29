package com.hxy.factorytest.adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hxy.factorytest.R
import com.hxy.factorytest.data.model.TestItem
import com.hxy.factorytest.util.Const

class TestItemAdapter(private val items: List<TestItem>, private val onClick: (TestItem) -> Unit) :
    RecyclerView.Adapter<TestItemAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    class ViewHolder(view: View, val onClick: (TestItem) -> Unit) : RecyclerView.ViewHolder(view) {
        private val textView: TextView
        private var currentItem: TestItem? = null

        init {
            // Define click listener for the ViewHolder's View
            textView = view.findViewById(R.id.textView)
            view.setOnClickListener {
                currentItem?.let { onClick(it) }
            }
        }

        fun bind(item: TestItem) {
            currentItem = item
            textView.setText(item?.name)
            val color = when{
                item?.state == Const.PASS -> Color.GREEN
                item?.state == Const.FAIL -> Color.RED
                else -> {Color.WHITE}
            }
            textView.setTextColor(color)
        }
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val isMainMenu = items.size < 5
        val layout = if (isMainMenu) {
            R.layout.main_item
        } else {
            R.layout.test_item
        }
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(layout, viewGroup, false)

        return ViewHolder(view, onClick)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.bind(items.get(position))
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = items.size

}
package com.pocket.allinone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.pocket.allinone.R
import com.pocket.allinone.models.Tool
import kotlinx.android.synthetic.main.row_recycler_tools.view.*

class ToolsAdapter(_context: Context, _toolList: ArrayList<Tool>, _itemClickListener: OnItemClickListener): RecyclerView.Adapter<ToolsAdapter.ToolsViewHolder>() {
    private var context: Context = _context
    private var toolList: ArrayList<Tool> = _toolList
    private var onItemClickListener:OnItemClickListener=_itemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToolsViewHolder {
        val view =LayoutInflater.from(context).inflate(R.layout.row_recycler_tools,parent,false)
        return ToolsViewHolder(view)
    }

    override fun getItemCount(): Int {
      return toolList.size
    }

    override fun onBindViewHolder(holder: ToolsViewHolder, position: Int) {
        holder.ivTool.setImageResource(toolList[position].toolImage)
        holder.tvTool.text=toolList[position].toolName
        holder.rootView.setOnClickListener{
            onItemClickListener.onItemClick(toolList[position].toolName)

        }
    }
    class ToolsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivTool: AppCompatImageView =itemView.iv_tool
        val tvTool: AppCompatTextView =itemView.tv_tool
        val rootView: CardView =itemView.cardView
    }

    interface OnItemClickListener{
        fun onItemClick(toolName:String)
    }

}
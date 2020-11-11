package com.pocket.allinone.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pocket.allinone.R
import com.pocket.allinone.models.Tool
import kotlinx.android.synthetic.main.row_recycler_tools.view.*

class ToolsAdapter(_context: Context, _toolList: ArrayList<Tool>, _itemClickListener:ToolsAdapter.OnItemClickListener): RecyclerView.Adapter<ToolsAdapter.ToolsViewHolder>() {
    var context: Context = _context
    var toolList: ArrayList<Tool> = _toolList
    var onItemClickListener:OnItemClickListener=_itemClickListener

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
        val ivTool=itemView.iv_tool
        val tvTool=itemView.tv_tool
        val rootView=itemView.cardView
    }

    interface OnItemClickListener{
        fun onItemClick(toolName:String)
    }

}
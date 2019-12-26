package com.goda.ikhair.presentation.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.goda.marvel.presentation.common.RecyclerViewCallback

class AppAdapter<T>(private var clickCallback: RecyclerViewCallback = object : RecyclerViewCallback {}, var list: List<T>) :
    RecyclerView.Adapter<AppAdapter.ViewHolder>() {

    private var layoutId: Int? = null

    private val selectionMap : HashMap<T, Boolean> = HashMap()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bind: ViewDataBinding =
            DataBindingUtil.bind(LayoutInflater.from(parent.context).inflate(viewType, parent, false))!!
        return ViewHolder(bind)
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model: T = list[position]
        holder.binding.setVariable(BR.model, model)
        holder.binding.setVariable(BR.callback, clickCallback)
        //holder.binding.setVariable(BR.isSelected, selectionMap[model])
        holder.binding.executePendingBindings()
    }

    override fun getItemViewType(position: Int): Int {
        return layoutId!!
    }

    /**
     * set the recyclerView item layout ID
     * @sample layoutId can be set like  R.layout.item_recyclerView
     * */
    fun setItemViewType(layoutId: Int) {
        this.layoutId = layoutId
    }


    fun setItemSelected(item: T, multiSelect:Boolean){
        if(!multiSelect){
            for (key : T in selectionMap.keys){
                selectionMap[key] = false
            }
        }

        selectionMap[item] = !selectionMap[item]!!
        notifyDataSetChanged()
    }

    fun submitList(list: List<T>){
        this.list = list
        for(item : T in list){
            selectionMap[item] = false
        }
        notifyDataSetChanged()
    }


    class ViewHolder(val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root)

}
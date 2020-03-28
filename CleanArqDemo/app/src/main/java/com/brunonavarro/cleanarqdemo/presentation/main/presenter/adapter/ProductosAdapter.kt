package com.brunonavarro.cleanarqdemo.presentation.main.presenter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brunonavarro.cleanarqdemo.data.entidades.Productos
import com.brunonavarro.cleanarqdemo.R
import kotlinx.android.synthetic.main.productos_detalle.view.*

class ProductosAdapter(val productList: ArrayList<Productos>) : RecyclerView.Adapter<ProductosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.productos_detalle, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(productList[position])
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(item: Productos) {
            itemView.txt_name.text=item.nombre
            itemView.txt_precio.text=item.precio
        }
    }

    fun add(festividades: Productos) {
        if (!productList.contains(festividades)) {
            productList.add(festividades)
            notifyItemInserted(productList.size - 1)

        } else {
            update(festividades)
        }
    }

    fun update(festividades: Productos) {
        if (productList.contains(festividades)) {
            val pos = productList.indexOf(festividades)
            productList.set(pos, festividades)
            notifyItemChanged(pos)
        }
    }

    fun remove(festividades: Productos) {
        if (productList.contains(festividades)) {
            val pos = productList.indexOf(festividades)
            productList.removeAt(pos)
            notifyItemRemoved(pos)
        }
    }
}
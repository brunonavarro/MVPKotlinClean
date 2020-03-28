package com.brunonavarro.cleanarqdemo.presentation.Productos.view.adapter

import com.brunonavarro.cleanarqdemo.data.entidades.Productos

interface OnItemClickListener {
    abstract fun onItemClick(festividades: Productos)
    abstract fun onLongItemClick(festividades: Productos)
}
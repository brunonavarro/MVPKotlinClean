package com.brunonavarro.cleanarqdemo.presentation.main.presenter.adapter


import com.brunonavarro.cleanarqdemo.data.entidades.Productos

interface OnItemClickListener {
    fun onItemClick(productos: Productos)
    fun onLongItemClick(productos: Productos)
}

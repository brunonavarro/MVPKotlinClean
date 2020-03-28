package com.brunonavarro.cleanarqdemo.presentation.Productos.model

import com.brunonavarro.cleanarqdemo.data.entidades.Productos

interface ProductoListener {
    abstract fun onChildAdded(productos: Productos)
    abstract fun onChildUpdated(productos: Productos)
    abstract fun onChildRemoved(productos: Productos)
    abstract fun onError(resMsg: Int)
}
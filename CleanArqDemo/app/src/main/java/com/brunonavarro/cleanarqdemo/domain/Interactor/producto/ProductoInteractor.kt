package com.brunonavarro.cleanarqdemo.domain.Interactor.producto

import android.content.Context
import android.view.View
import com.brunonavarro.cleanarqdemo.data.entidades.Productos

interface ProductoInteractor {
    suspend fun addNewProduct(name:String,stock:String,precio:String)
    suspend fun removeProduct(name:String,stock:String,precio:String)
    suspend fun loadProducts()//:ArrayList<Productos>
    suspend fun getList(vista:View,context: Context)//:ArrayList<Productos>
}
package com.brunonavarro.cleanarqdemo.presentation.main

import android.content.Context
import android.view.View
import com.brunonavarro.cleanarqdemo.data.entidades.Productos

interface MainContract {
    interface View{
        fun insertProducto()
        fun loadProductos()
        fun showProgress()
        fun hideProgress()
        fun showError(errorMessage:String?)
    }

    interface Presenter{
        fun attachView(view: View)
        fun isAttachView():Boolean
        fun detachView()
        fun dettachJob()
        fun checkEmptyFields(nombre: String, stock: String, precio: String):Boolean
        fun addProduct(nombre:String,stock:String,precio:String)
        fun loadProducts(vista:android.view.View,contex:Context)//:ArrayList<Productos>
    }
}
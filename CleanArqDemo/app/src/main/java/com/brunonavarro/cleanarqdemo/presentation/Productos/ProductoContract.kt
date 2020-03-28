package com.brunonavarro.cleanarqdemo.presentation.Productos

import com.brunonavarro.cleanarqdemo.presentation.auth.Registro.RegistroContract

interface ProductoContract {

    interface View{
        fun showError(mess:String?)
        fun showProgressBar()
        fun hideProgressBar()
        fun addProduct()
        fun removeProduct()
        fun loadProduct()
    }

    interface Presenter{
        fun attachView(view: View)
        fun isAttachView():Boolean
        fun detachView()
        fun dettachJob()
        fun checkEmptyCampos(name:String,stock:String,precio:String):Boolean
        fun ProductoAdd(nombre:String,stock:String,precio:String)
        fun ProductoRemove(nombre:String,stock:String,precio:String)
    }



}
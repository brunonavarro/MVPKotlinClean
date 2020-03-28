package com.brunonavarro.cleanarqdemo.presentation.main.presenter

import android.content.Context
import android.view.View
import com.brunonavarro.cleanarqdemo.data.entidades.Productos
import com.brunonavarro.cleanarqdemo.domain.Interactor.auth.registroInteractor.signUpInteractor
import com.brunonavarro.cleanarqdemo.domain.Interactor.producto.ProductoInteractor
import com.brunonavarro.cleanarqdemo.presentation.Productos.ProductoContract
import com.brunonavarro.cleanarqdemo.presentation.auth.Registro.RegistroContract
import com.brunonavarro.cleanarqdemo.presentation.main.MainContract
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

class MainPresenter(productoInteractor: ProductoInteractor): MainContract.Presenter,
    CoroutineScope {
    lateinit var list:ArrayList<Productos>
    var view: MainContract.View?=null
    var productoInteractor: ProductoInteractor?=null
    private val job= Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main+job
    init {
        this.productoInteractor=productoInteractor
    }
    override fun attachView(view: MainContract.View) {
        this.view=view
    }

    override fun isAttachView(): Boolean {
        return view!=null
    }

    override fun detachView() {
        view=null
    }

    override fun dettachJob() {
        coroutineContext.cancel()
    }

    override fun checkEmptyFields(nombre: String, stock: String, precio: String): Boolean {
        return nombre.isEmpty()||stock.isEmpty()||precio.isEmpty()
    }

    override fun addProduct(nombre: String, stock: String, precio: String) {
        launch {
            try {
                productoInteractor?.addNewProduct(nombre,stock,precio)
                //if (isAttachView()){
                    //view?.hideProgress()
                //}
                //loadProducts()
            }catch (e:Exception){
                view?.showError(e.message)
            }
        }
    }

    override fun loadProducts(vista: View, contex: Context){//: ArrayList<Productos> {
        launch {
            try {
                //productoInteractor?.loadProducts()
                //list=productoInteractor?.getList(vista,contex)!!
                productoInteractor?.getList(vista,contex)
                //view?.loadProductos()
            }catch (e:Exception){
                view?.showError(e.message)
            }

        }
        //return list
    }
}
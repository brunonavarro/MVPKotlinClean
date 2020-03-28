package com.brunonavarro.cleanarqdemo.presentation.Productos.presenter

import com.brunonavarro.cleanarqdemo.domain.Interactor.auth.registroInteractor.signUpInteractor
import com.brunonavarro.cleanarqdemo.domain.Interactor.producto.ProductoInteractor
import com.brunonavarro.cleanarqdemo.presentation.Productos.ProductoContract
import com.brunonavarro.cleanarqdemo.presentation.Productos.exceptions.ProductoExceptions
import com.brunonavarro.cleanarqdemo.presentation.auth.Registro.RegistroContract
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class ProductoPresenter(productoInteractor: ProductoInteractor): ProductoContract.Presenter, CoroutineScope {


    var view: ProductoContract.View?=null
    var productoInteractor: ProductoInteractor?=null
    private val job= Job()

    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main+job

    init {
        this.productoInteractor=productoInteractor
    }

    override fun attachView(view: ProductoContract.View) {
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

    override fun checkEmptyCampos(name: String, stock: String, precio: String): Boolean {
        return name.isEmpty()||stock.isEmpty()||precio.isEmpty()
    }

    override fun ProductoAdd(nombre: String, stock: String, precio: String) {
        launch {
            try {
                view?.showProgressBar()
                productoInteractor?.addNewProduct(nombre,stock,precio)
                if (isAttachView()){
                    view?.hideProgressBar()
                    view?.loadProduct()
                }
            }catch (e:ProductoExceptions){
                if (isAttachView()){
                    view?.hideProgressBar()
                    view?.showError(e.message)
                }
            }
        }
    }

    override fun ProductoRemove(nombre: String, stock: String, precio: String) {
        launch {
            try {
                view?.showProgressBar()
                productoInteractor?.removeProduct(nombre,stock,precio)
                if (isAttachView()){
                    view?.hideProgressBar()
                    view?.loadProduct()
                }
            }catch (e:ProductoExceptions){
                if (isAttachView()){
                    view?.hideProgressBar()
                    view?.showError(e.message)
                }
            }
        }
    }
}
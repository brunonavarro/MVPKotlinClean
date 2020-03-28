package com.brunonavarro.cleanarqdemo.domain.Interactor.producto

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brunonavarro.cleanarqdemo.R
import com.brunonavarro.cleanarqdemo.data.dataBase.FirebaseRealtimeDatabaseAPI
import com.brunonavarro.cleanarqdemo.data.entidades.Productos
import com.brunonavarro.cleanarqdemo.presentation.Productos.exceptions.ProductoExceptions
import com.brunonavarro.cleanarqdemo.presentation.main.presenter.adapter.ProductosAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class ProductoInteractorImpl:ProductoInteractor {

    //: ArrayList<Productos> {
    override suspend fun getList(vista: View, context: Context): Unit = suspendCancellableCoroutine{continuacion->
        try {
            val ref = FirebaseRealtimeDatabaseAPI().GetReferencia("products")
            var lists = ArrayList<Productos>()
            /*ref.addValueEventListener(object : ValueEventListener {
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()) {
                        for (h in p0.children) {
                            var product = h.getValue(Productos::class.java)
                            lists.add(product!!)
                        }
                        val rv_list: RecyclerView = vista.findViewById(R.id.rv_listProductos)
                        rv_list.layoutManager = LinearLayoutManager(context)
                        rv_list.hasFixedSize()
                        rv_list.adapter = ProductosAdapter(lists)
                        //continuacion.resume(Unit)
                    } else {
                        //continuacion.resumeWithException(ProductoExceptions("No hay datos"))
                    }
                }
            })
            continuacion.resume(Unit)*/

            val productListener=object : ValueEventListener{
                override fun onCancelled(p0: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(p0: DataSnapshot) {
                    if (p0.exists()) {
                        for (h in p0.children) {
                            var product = h.getValue(Productos::class.java)
                            lists.add(product!!)
                        }
                        val rv_list: RecyclerView = vista.findViewById(R.id.rv_listProductos)
                        rv_list.layoutManager = LinearLayoutManager(context)
                        rv_list.hasFixedSize()
                        rv_list.adapter = ProductosAdapter(lists)
                    }
                }
            }
            ref.addValueEventListener(productListener)
            continuacion.resume(Unit)
        }catch (e:ProductoExceptions){
            continuacion.resumeWithException(ProductoExceptions(e.message))
        }

        //return lists
    }

    override suspend fun loadProducts(): Unit = suspendCancellableCoroutine{
        try {
            //getList()
            it.resume(Unit)
        }catch (e:ProductoExceptions){
            it.resumeWithException(ProductoExceptions(e.message))
        }
    }

    override suspend fun removeProduct(name: String, stock: String, precio: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun addNewProduct(name: String, stock: String, precio: String): Unit = suspendCancellableCoroutine {continuacion->

        val ref=FirebaseRealtimeDatabaseAPI().GetReferencia("products")
        val productoId=ref.push().key
        val producto=Productos(productoId.toString(),name,stock,precio)

        ref.child(productoId.toString()).setValue(producto)
            .addOnSuccessListener {
                continuacion.resume(Unit)
            }.addOnFailureListener {et->
                continuacion.resumeWithException(ProductoExceptions(et.message))
            }

        //getList()
    }

}
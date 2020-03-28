package com.brunonavarro.cleanarqdemo.presentation.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brunonavarro.cleanarqdemo.PRUEBA
import com.brunonavarro.cleanarqdemo.R
import com.brunonavarro.cleanarqdemo.base.BaseActivity
import com.brunonavarro.cleanarqdemo.data.dataBase.FirebaseRealtimeDatabaseAPI
import com.brunonavarro.cleanarqdemo.data.entidades.Productos
import com.brunonavarro.cleanarqdemo.domain.Interactor.auth.registroInteractor.signUpInteractorImpl
import com.brunonavarro.cleanarqdemo.domain.Interactor.producto.ProductoInteractorImpl
import com.brunonavarro.cleanarqdemo.presentation.Productos.ProductoContract
import com.brunonavarro.cleanarqdemo.presentation.Productos.exceptions.ProductoExceptions
import com.brunonavarro.cleanarqdemo.presentation.auth.Registro.RegistroContract
import com.brunonavarro.cleanarqdemo.presentation.auth.Registro.presenter.RegistroPresenter
import com.brunonavarro.cleanarqdemo.presentation.main.MainContract
import com.brunonavarro.cleanarqdemo.presentation.main.presenter.MainPresenter
import com.brunonavarro.cleanarqdemo.presentation.main.presenter.adapter.OnItemClickListener
import com.brunonavarro.cleanarqdemo.presentation.main.presenter.adapter.ProductosAdapter
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.productos_detalle.*
import java.util.ArrayList

class MainActivity : BaseActivity(),MainContract.View{


    lateinit var presenter: MainContract.Presenter
    lateinit var mSearchProduct: EditText
    lateinit var mReciclerView: RecyclerView
    lateinit var mDatabase: DatabaseReference
    //lateinit var items:ArrayList<Productos>
    private var mAdapter: ProductosAdapter? = null

    lateinit var vista:View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter= MainPresenter(ProductoInteractorImpl())
        presenter.attachView(this)
        vista=findViewById(android.R.id.content)
        //getLists()
        //configAdapter()
        //configRecyclerView()

        loadProductos()
        btn_save.setOnClickListener {
            insertProducto()
        }
    }

    private fun configAdapter() {
        mAdapter = ProductosAdapter(ArrayList<Productos>())
    }

    private fun configRecyclerView() {
        val linearLayoutManager = LinearLayoutManager(this)
        rv_listProductos.setLayoutManager(linearLayoutManager)
        rv_listProductos.setAdapter(mAdapter)
    }

    /*fun getLists(): ArrayList<Productos> {
        val ref=FirebaseRealtimeDatabaseAPI().GetReferencia("products")
        var lists = ArrayList<Productos>()

        ref.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    for (h in p0.children){
                        var product=h.getValue(Productos::class.java)
                        lists.add(product!!)
                    }
                    rv_listProductos.layoutManager = LinearLayoutManager(applicationContext)
                    rv_listProductos.hasFixedSize()
                    rv_listProductos.adapter = ProductosAdapter(lists)
                    //(rv_listProductos.adapter as ProductosAdapter).notifyDataSetChanged()
                }else{
                    ProductoExceptions("No hay datos")
                }
            }
        })

        /*lists.add(Productos("123", "Item 1", "Descripcion 1","vfdg"))
        lists.add(Productos("1234", "Item 2", "Descripcion 2","fdss"))
        lists.add(Productos("321", "Item 3", "Descripcion 3","asdf"))
        lists.add(Productos("12345", "Item 4", "Descripcion 4","asd"))*/
        return lists;
    } */

    override fun getLayout(): Int {
        return R.layout.activity_main
    }

    override fun insertProducto() {
        val nombre=edt_nombre.text.toString().trim()
        val stock=edt_stock.text.toString().trim()
        val precio=edt_precio.text.toString().trim()
        if(presenter.checkEmptyFields(nombre,stock,precio)){
            edt_nombre.error="Campo vacio"
            edt_stock.error="Campo vacio"
            edt_precio.error="Campo vacio"
            return
        }
        presenter.addProduct(nombre,stock,precio)

        edt_nombre.setText("").toString().trim()
        edt_stock.setText("").toString().trim()
        edt_precio.setText("").toString().trim()
    }

    override fun loadProductos() {
        //items=presenter.loadProducts(vista,applicationContext)
        presenter.loadProducts(vista,applicationContext)
        /*rv_listProductos.layoutManager = LinearLayoutManager(this)
        rv_listProductos.hasFixedSize()
        rv_listProductos.adapter = ProductosAdapter(items)*/
    }

    override fun showProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideProgress() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showError(errorMessage: String?) {
        toast(this,errorMessage)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        presenter.detachView()
        presenter.dettachJob()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
        presenter.dettachJob()
    }


}

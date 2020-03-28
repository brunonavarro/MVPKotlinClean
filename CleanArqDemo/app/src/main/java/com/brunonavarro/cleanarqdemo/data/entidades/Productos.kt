package com.brunonavarro.cleanarqdemo.data.entidades

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties


class Productos {
    var id:String?=null
    var nombre: String?=null
    var stock: String?=null
    var precio: String?=null

    constructor(){

    }

    constructor(id:String,nombre: String,stock: String,precio: String){
        this.id=id
        this.nombre=nombre
        this.stock=stock
        this.precio=precio
    }


}


//SI FUNCIONA
/*class Productos(val id:String, val nombre: String, val stock: String, val precio: String)*/
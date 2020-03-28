package com.brunonavarro.cleanarqdemo.data.dataBase

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class FirebaseRealtimeDatabaseAPI{

    fun GetReferencia(referencia:String): DatabaseReference{
        return FirebaseDatabase.getInstance().getReference(referencia)
    }
}


/*class FirebaseRealtimeDatabaseAPI private constructor() {

    private val mReference: DatabaseReference

    init {
        mReference = FirebaseDatabase.getInstance().reference
    }

    //REFERENCIAS
    fun getmReference(): DatabaseReference {
        return mReference
    }

    companion object {

        private var INSTANCE: FirebaseRealtimeDatabaseAPI? = null

        val instance: FirebaseRealtimeDatabaseAPI
            get() {
                if (INSTANCE == null) {
                    INSTANCE = FirebaseRealtimeDatabaseAPI()
                }
                return INSTANCE!!
            }
    }
} */






/*class FirebaseRealtimeDatabaseAPI {

    private lateinit var database: DatabaseReference
    private lateinit var INSTANCE:FirebaseRealtimeDatabaseAPI

    fun FirebaseRealtimeDatabaseAPI(){
        database = FirebaseDatabase.getInstance().reference
    }

    fun getInstance():FirebaseRealtimeDatabaseAPI{
        if (INSTANCE==null){
            INSTANCE = FirebaseRealtimeDatabaseAPI()
        }
       return INSTANCE!!
    }

    fun getDatabase():DatabaseReference{
        return database
    }


}*/
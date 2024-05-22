package mx.unam.fi.ejemplofirebasemovie.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import mx.unam.fi.ejemplofirebasemovie.model.Movie
import mx.unam.fi.ejemplofirebasemovie.sealed.DataState

class MainViewModel: ViewModel() {
    private val db = Firebase.firestore
    val response: MutableState<DataState> = mutableStateOf(DataState.Empty)
    init{
        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase(){
        val tempList = mutableListOf<Movie>()
        response.value = DataState.Loading
        db.collection("Cine")
            .get()
            .addOnSuccessListener {
                    document ->

                for( dataSnap in document  ){

                    val movieItem = dataSnap.toObject(Movie::class.java)
                    Log.d("getFirebase",movieItem.titulo.toString() )
                    Log.d("getFirebase",movieItem.clasificacion.toString() )
                    Log.d("getFirebase",movieItem.cartel.toString() )
                    tempList.add(movieItem)
                }
                response.value = DataState.Success(tempList)

            }.addOnCanceledListener {
                response.value = DataState.Failure(error("Error"))
            }
    }
}
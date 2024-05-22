package mx.unam.fi.ejemplofirebasemovie.sealed

import mx.unam.fi.ejemplofirebasemovie.model.Movie

sealed class DataState {
    class Success(val data: MutableList<Movie>):DataState()
    class Failure(val message: String):DataState()
    data object Loading:DataState()
    data object Empty: DataState()



}
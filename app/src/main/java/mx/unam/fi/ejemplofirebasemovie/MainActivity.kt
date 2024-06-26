package mx.unam.fi.ejemplofirebasemovie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import mx.unam.fi.ejemplofirebasemovie.model.Movie
import mx.unam.fi.ejemplofirebasemovie.sealed.DataState
import mx.unam.fi.ejemplofirebasemovie.ui.components.CardItem
import mx.unam.fi.ejemplofirebasemovie.ui.components.ShowLazyList
import mx.unam.fi.ejemplofirebasemovie.ui.theme.EjemploFirebaseMovieTheme
import mx.unam.fi.ejemplofirebasemovie.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EjemploFirebaseMovieTheme {
                // A surface container using the 'background' color from the theme
                Column {
                    TopAppBar(title = { Text(text = "Peliculas de Estreno") },
                        Modifier.background(Color.Black)
                    )
                    SetData(viewModel = viewModel)
                }
            }
        }
    }
}
@Composable
fun SetData(viewModel: MainViewModel){
    when( val result = viewModel.response.value){
        is DataState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        is DataState.Success -> {
            ShowLazyList(movies = result.data)
        }
        is DataState.Failure -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = result.message,
                    fontSize = 34.sp
                )
            }
        }
        else ->{
            Box(
                modifier =  Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                Text(
                    text = "Error Fetching Data",
                    fontSize = 34.sp
                )
            }
        }
    }
}





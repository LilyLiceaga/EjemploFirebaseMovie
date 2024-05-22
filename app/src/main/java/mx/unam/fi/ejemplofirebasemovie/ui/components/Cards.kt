package mx.unam.fi.ejemplofirebasemovie.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import mx.unam.fi.ejemplofirebasemovie.model.Movie

@Composable
fun CardItem(movie: Movie){
    Card(
        modifier = Modifier
            .fillMaxWidth()

    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box()
            {

                AsyncImage(model = movie.cartel ,
                    contentDescription =movie.titulo )

            }
            Spacer(modifier = Modifier.height(22.dp))
            TextMovie(label = "Clasificación", title = movie.clasificacion )
            Spacer(modifier = Modifier.height(22.dp))
            TextMovie(label = "Estreno", title = movie.estreno.toString() )

        }

    }
}

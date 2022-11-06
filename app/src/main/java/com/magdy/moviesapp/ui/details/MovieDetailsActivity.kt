package com.magdy.moviesapp.ui.details

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import androidx.activity.compose.LocalOnBackPressedDispatcherOwner
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.magdy.moviesapp.R
import com.magdy.moviesapp.core.models.Genres
import com.magdy.moviesapp.core.models.Movie
import com.magdy.moviesapp.core.utils.Constants
import com.magdy.moviesapp.core.utils.ParcelableUtils.parcelable
import com.magdy.moviesapp.ui.details.viewmodels.MovieDetailsViewModel
import com.magdy.moviesapp.ui.theme.MoviesAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsActivity : ComponentActivity() {
    private val mViewModel: MovieDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val onBack = {
                setResult(RESULT_OK)
                finish()
            }
            MovieDetailsScreen(
                mViewModel,
                intent.parcelable(Constants.MOVIE)!!,
                onBack
            )
        }
    }
}

@Composable
fun TopAppBar(onBack: () -> Unit) {
    TopAppBar(
        title = {
            Text(text = stringResource(id = R.string.movie_details))
        },
        navigationIcon = {
            IconButton(onClick = onBack) {
                Icon(Icons.Filled.ArrowBack, contentDescription = stringResource(R.string.Back))
            }
        },
        backgroundColor = MaterialTheme.colors.primary,
    )
}

@Composable
fun MovieDetailsScreen(
    mViewModel: MovieDetailsViewModel = viewModel(),
    movie: Movie,
    onBack: () -> Unit
) {
    mViewModel.checkIfMovieIsFavorite(movie.id)
    mViewModel.retrieveGenresFromBackend(movie.id)


    BackPressHandler(onBackPressed = onBack)
    MoviesAppTheme {
        val scaffoldState = rememberScaffoldState()
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(onBack)
            },
            modifier = Modifier.fillMaxSize(),
            content = { padding ->
                MovieDetailsContent(
                    padding = padding,
                    movie = movie,
                    isFavorite = mViewModel.isFavorite,
                    onFavoriteClicked = mViewModel.toggleFavorite,
                    genres = mViewModel.genres
                )
            }
        )
    }
}

@Composable
fun MovieDetailsContent(
    padding: PaddingValues,
    movie: Movie,
    isFavorite: Boolean,
    onFavoriteClicked: (movie: Movie) -> Unit,
    genres: List<Genres>?,
) {
    Column {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(Constants.BASE_IMAGE_URL_API + movie.posterPath)
                .crossfade(true)
                .build(),
            loading = {
                CircularProgressIndicator()
            },
            contentDescription = movie.title,
            modifier = Modifier
                .padding(padding)
                .weight(1f, fill = false)
                .aspectRatio(1.3f)
                .fillMaxWidth(),
        )
        Column(modifier = Modifier.padding(horizontal = 8.dp)) {
            Spacer(modifier = Modifier.height(16.dp))
            ExtendedFloatingActionButton(
                onClick = {
                    onFavoriteClicked(movie)
                },
                icon = {
                    Icon(
                        if (isFavorite) painterResource(id = R.drawable.ic_baseline_favorite_24) else painterResource(
                            id = R.drawable.ic_baseline_favorite_border_24
                        ),
                        contentDescription = stringResource(id = if (isFavorite) R.string.unfavorite else R.string.favorite)
                    )
                },
                text = { Text(text = stringResource(id = if (isFavorite) R.string.unfavorite else R.string.favorite)) },
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = movie.title)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = stringResource(id = R.string.votes, movie.voteAverage, movie.voteCount))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = stringResource(id = R.string.overview))
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = movie.overview)
            Spacer(modifier = Modifier.height(8.dp))
            GenresChipGroup(genres = genres)
        }
    }
}

@Composable
fun GenresChipGroup(genres: List<Genres>?) {
    Column {
        Text(text = stringResource(id = R.string.genres))
        Spacer(modifier = Modifier.height(4.dp))
        LazyRow {
            genres?.run {
                items(genres) {
                    Chip(it.name)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Chip(
    name: String = "Chip",
) {
    Surface(
        modifier = Modifier.padding(4.dp),
        elevation = 8.dp,
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colors.primary
    ) {
        Row {
            Text(
                text = name,
                style = MaterialTheme.typography.body2,
                color = Color.White,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@Composable
fun BackPressHandler(
    backPressedDispatcher: OnBackPressedDispatcher? =
        LocalOnBackPressedDispatcherOwner.current?.onBackPressedDispatcher,
    onBackPressed: () -> Unit
) {
    val currentOnBackPressed by rememberUpdatedState(newValue = onBackPressed)

    val backCallback = remember {
        object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                currentOnBackPressed()
            }
        }
    }

    DisposableEffect(key1 = backPressedDispatcher) {
        backPressedDispatcher?.addCallback(backCallback)
        onDispose {
            backCallback.remove()
        }
    }
}
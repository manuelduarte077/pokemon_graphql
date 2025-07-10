package dev.donmanuel.pokedexkmp.ui.pokemonInfo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun PokemonInfoScreen(
    onBackClick: () -> Unit
) {
    val viewModel = koinViewModel<PokemonInfoViewModel>()
    val state = viewModel.pokemonInfoState.value
    val info = state.info

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = info.name.replaceFirstChar(Char::titlecase),
                        style = MaterialTheme.typography.headlineSmall
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Localized description"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AsyncImage(
                    model = info.imageUrl,
                    contentDescription = info.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .size(300.dp)
                )

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(
                            text = info.name.replaceFirstChar(Char::titlecase),
                            style = MaterialTheme.typography.headlineSmall.copy(fontWeight = FontWeight.SemiBold)
                        )
                        Text(
                            text = info.idString,
                            style = MaterialTheme.typography.titleLarge
                        )

                        InfoItem(
                            title = "Height",
                            infoText = info.heightInMeter
                        )

                        InfoItem(
                            title = "Weight",
                            infoText = info.weightInKg
                        )

                        InfoItem(
                            title = "Types",
                            infoText = info.types.joinToString(", ") { it.type.name }
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun InfoItem(
    title: String,
    infoText: String
) {
    Row {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.SemiBold
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = infoText,
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
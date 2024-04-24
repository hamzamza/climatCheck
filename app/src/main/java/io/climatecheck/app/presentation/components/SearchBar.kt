package io.climatecheck.app.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.focus.FocusState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import io.climatecheck.app.domain.schema.CitySchema
import io.climatecheck.app.presentation.AppViewModel

@Composable
fun SearchAppBar(viewModel: AppViewModel) {
    var searchText by remember { mutableStateOf(TextFieldValue()) }
    val searchResults by viewModel.citiesList.collectAsState()
    val focusRequester =  remember{FocusRequester()}
    Surface(
        shadowElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Column {
            TextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                    viewModel.searchforCity(it.text)
                },
                label = { Text("Search for a city") },
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxWidth() .focusRequester(focusRequester)
                    .onFocusChanged { focusState ->
                        viewModel.scoopIn(focusState.isFocused)
                    }
            )
            SearchResultsList(searchResults,viewModel)
        }
    }
}

@Composable
fun SearchResultsList(searchResults: List<CitySchema>, viewModel: AppViewModel) {
    LazyColumn {
        items(searchResults)
        {
            Surface(onClick = {viewModel.selectCity(it)}) {
                Text(text = it.name)
            }
        }
    }
}
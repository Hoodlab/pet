package com.example.pet.home


import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pet.data.DummyPetDataSource

@Composable
fun Home(
    modifier: Modifier = Modifier,
    onPetClick: (Int) -> Unit
) {
    val petList = DummyPetDataSource.petList
    Scaffold(
        topBar = {
        },
        modifier = modifier
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            itemsIndexed(petList) { index, pet ->
                PetInfoItem(pet = pet) {
                    onPetClick(index)
                }
            }
        }
    }

}

package com.example.pet.detail


import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.pet.data.DummyPetDataSource
import com.example.pet.data.Pet
import com.example.pet.home.GenderTag

@OptIn(ExperimentalMaterial3Api::class, ExperimentalSharedTransitionApi::class)
@Composable
fun DetailScreen(
    index: Int,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onNavigate: () -> Unit
) {
   with(sharedTransitionScope) {
       Scaffold(topBar = {
           TopAppBar(
               title = { Text(text = "Detail") },
               navigationIcon = {
                   Icon(
                       imageVector = Icons.Default.ArrowBack,
                       contentDescription = null,
                       modifier = Modifier
                           .size(24.dp, 24.dp)
                           .clickable {
                               onNavigate.invoke()
                           },
                   )
               },
           )
       }) { padding ->
           val pet = DummyPetDataSource.petList[index]
           LazyColumn(contentPadding = padding) {
               item {
                   Image(
                       painter = painterResource(id = pet.image),
                       contentDescription = null,
                       modifier = Modifier
                           .sharedElement(
                               state = rememberSharedContentState(key = "image/${pet.id}"),
                               animatedVisibilityScope = animatedVisibilityScope,
                           )
                           .fillMaxWidth()
                           .height(346.dp),
                       alignment = Alignment.CenterStart,
                       contentScale = ContentScale.Crop
                   )
                   Spacer(modifier = Modifier.height(16.dp))
                   PetBasicInfoItem(
                       name = pet.name,
                       gender = pet.gender,
                       location = pet.location,
                       animatedVisibilityScope = animatedVisibilityScope
                   )
               }

               item {
                   PetInfo(pet = pet)
               }

           }


       }
   }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.PetBasicInfoItem(
    name: String,
    gender: String,
    location: String,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(modifier = Modifier.align(CenterVertically)) {
            Text(
                text = name,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .sharedElement(
                        state = rememberSharedContentState(key = "text/${name}"),
                        animatedVisibilityScope = animatedVisibilityScope,
                    ),
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.headlineLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.Bottom,
                modifier = Modifier
                    .padding(end = 12.dp),
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp, 16.dp),
                    tint = Color.Red
                )
                Text(
                    text = location, modifier = Modifier.padding(
                        start = 8.dp, top = 0.dp, end = 12.dp, bottom = 0.dp
                    ), style = MaterialTheme.typography.bodySmall
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Adoptable", modifier = Modifier
                    .padding(
                        0.dp, 0.dp, 12.dp, 0.dp
                    ), style = MaterialTheme.typography.labelSmall
            )


        }
        Column(
            modifier = Modifier.height(80.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GenderTag(
                gender = gender, modifier = Modifier
            )
            Text(
                text = "Dog", style = MaterialTheme.typography.bodySmall
            )
        }

    }

}

@Composable
fun Title(title: String) {
    Text(
        text = title,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp),
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.W600,
        textAlign = TextAlign.Start
    )
}

@Composable
fun PetInfo(pet: Pet) {
    Column {
        Spacer(modifier = Modifier.height(24.dp))
        Title(title = "Pet Info")
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround
        ) {
            InfoCard(
                primaryText = pet.age,
                secondaryText = "Age",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )

            InfoCard(
                primaryText = pet.color,
                secondaryText = "Color",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            InfoCard(
                primaryText = pet.breed,
                secondaryText = "Breed",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )

        }


    }

}

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    primaryText: String,
    secondaryText: String,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = secondaryText)
            Text(
                text = primaryText,
                style = MaterialTheme.typography.bodySmall,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.size(4.dp))
        }

    }

}


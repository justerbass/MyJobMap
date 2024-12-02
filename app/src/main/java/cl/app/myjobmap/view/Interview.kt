package cl.app.myjobmap.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cl.app.myjobmap.components.Separation
import cl.app.myjobmap.naviagation.Screen
import cl.app.myjobmap.viewModel.PostulationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Interview(navController: NavController, viewModel: PostulationViewModel) {
    val id = viewModel.listenID.value
    val job = viewModel.getPostulationById(id).collectAsState(initial = null)

    val interviewDate = remember { mutableStateOf("") }
    val interviewPlace = remember { mutableStateOf("") }
    val interviewHour = remember { mutableStateOf("") }
    val interviewModal = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Detalles de la Entrevista")
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate(Screen.MainView.route) }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ){
        paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "fecha de la entrevista")
            Separation()
            OutlinedTextField(value = interviewDate.value, onValueChange = {interviewDate.value = it})
            Separation()
            Text(text = "Lugar de la entrevista")
            Separation()
            OutlinedTextField(value = interviewPlace.value, onValueChange = {interviewPlace.value = it})
            Separation()
            Text(text = "Hora de la entrevista")
            Separation()
            OutlinedTextField(value = interviewHour.value, onValueChange = {interviewHour.value = it})
            Text(text = "modalidad")
            Separation()
            OutlinedTextField(value = interviewModal.value, onValueChange = {interviewModal.value = it})
        }
    }
}
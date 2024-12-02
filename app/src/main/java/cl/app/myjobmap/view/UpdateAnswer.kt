package cl.app.myjobmap.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import cl.app.myjobmap.components.Separation
import cl.app.myjobmap.naviagation.Screen
import cl.app.myjobmap.viewModel.PostulationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UpdateAnswer(navController: NavController, viewModel: PostulationViewModel) {
    val id = viewModel.listenID.value
    val job = viewModel.getPostulationById(id).collectAsState(initial = null)

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Respuesta a " + job.value?.job.toString())
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Indique el tipo de respuesta dado")
            Separation()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    job.value?.answer = "Llamado a Entrevista"
                    viewModel.updatePostulation(job.value!!)
                    navController.navigate(Screen.Interview.route)
                }
                ) {
                    Text(text = "Entrevista")
                }

                Button(onClick = {
                    job.value?.answer = "Avanza de etapa"
                    viewModel.updatePostulation(job.value!!)
                    navController.navigate(Screen.MainView.route)
                }) {
                    Text(text = "Pasa a siguiente etapa")
                }
            }
            Separation()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Button(onClick = {
                    job.value?.answer = "Rechazado"
                    viewModel.updatePostulation(job.value!!)
                    navController.navigate(Screen.MainView.route)
                }
                ) {
                    Text(text = "Rechazado")
                }

                Button(onClick = {
                    job.value?.answer = "Proceso Desistido"
                    viewModel.updatePostulation(job.value!!)
                    navController.navigate(Screen.MainView.route)
                }) {
                    Text(text = "Proceso Desistido")
                }
            }
            Separation()

        }
    }
}
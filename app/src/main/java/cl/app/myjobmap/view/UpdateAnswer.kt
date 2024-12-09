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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import cl.app.myjobmap.R
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
                    Text(text = stringResource(id = R.string.answer_to) + "\n" +  job.value?.job.toString(),
                        textAlign = TextAlign.Center)
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
            Text(text = stringResource(id = R.string.type_answer))
            Separation()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val callItAnswer = stringResource(id = R.string.call_it)
                val nextStepAnswer = stringResource(id = R.string.next_step)
                Button(onClick = {
                    job.value?.answer = callItAnswer
                    viewModel.updatePostulation(job.value!!)
                    navController.navigate(Screen.Interview.route)
                }
                ) {
                    Text(text = stringResource(id = R.string.interview))
                }

                Button(onClick = {
                    job.value?.answer = nextStepAnswer
                    viewModel.updatePostulation(job.value!!)
                    navController.navigate(Screen.MainView.route)
                }) {
                    Text(text = stringResource(id = R.string.next))
                }
            }
            Separation()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val reject = stringResource(id = R.string.rejected)
                val desist = stringResource(id = R.string.desist)
                Button(onClick = {
                    job.value?.answer = reject
                    viewModel.updatePostulation(job.value!!)
                    navController.navigate(Screen.MainView.route)
                }
                ) {
                    Text(text = reject)
                }

                Button(onClick = {
                    job.value?.answer = desist
                    viewModel.updatePostulation(job.value!!)
                    navController.navigate(Screen.MainView.route)
                }) {
                    Text(text = desist)
                }
            }
            Separation()

        }
    }
}
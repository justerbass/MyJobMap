package cl.app.myjobmap.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.app.myjobmap.R
import cl.app.myjobmap.components.Separation
import cl.app.myjobmap.naviagation.Screen
import cl.app.myjobmap.viewModel.PostulationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Answer(navController: NavController, viewModel: PostulationViewModel) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.answer)
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.MainView.route) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 100.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            val id = viewModel.listenID.value
            val job = viewModel.getPostulationById(id).collectAsState(initial = null)

            Text(text = stringResource(id = R.string.company))
            Separation()
            Separation()
            Text(text = job.value?.company.toString())
            Separation()
            Separation()
            Text(text = stringResource(id = R.string.postulation))
            Separation()
            Separation()
            Text(text = job.value?.job.toString())
            Separation()
            Separation()
            Text(text = stringResource(id = R.string.date))
            Separation()
            Separation()
            Text(text = job.value?.date.toString())
            Separation()
            Separation()
            Text(text = stringResource(id = R.string.question_answer))
            Separation()
            Separation()
            Separation()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                val negativeAnswer = stringResource(id = R.string.no_answer)
                val positiveAnswer = stringResource(id = R.string.yes_answer)

                Button(onClick = {
                    job.value?.answer = positiveAnswer
                    viewModel.updatePostulation(job.value!!)
                    navController.navigate(Screen.UpdateAnswer.route)
                }
                ) {
                    Text(text = stringResource(id = R.string.yes).uppercase(),
                        modifier = Modifier.padding(20.dp, 10.dp)
                        )
                }

                Button(onClick = {
                    navController.navigate(Screen.MainView.route)
                    job.value?.answer = negativeAnswer
                    viewModel.updatePostulation(job.value!!)
                }) {
                    Text(text = stringResource(id = R.string.no).uppercase(),
                        modifier = Modifier.padding(20.dp, 10.dp)
                        )
                }
            }
        }
    }
}
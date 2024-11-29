package cl.app.myjobmap.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import cl.app.myjobmap.R
import cl.app.myjobmap.components.JobCard
import cl.app.myjobmap.naviagation.Screen
import cl.app.myjobmap.viewModel.PostulationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(navControler: NavController, viewModel: PostulationViewModel) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.mainview))
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navControler.navigate(Screen.NewJob.route)
                }
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ShowJobs(navControler = navControler, viewModel = viewModel)
        }
    }

}

@Composable
fun ShowJobs(
    navControler: NavController,
    viewModel: PostulationViewModel
) {
    val jobs by viewModel.getAllPostulations().collectAsState(initial = emptyList())
    LazyColumn {
        items(jobs) { job ->
            JobCard(jobTitle = job.job,
                companyName = job.company,
                recruiterName = job.recruiter,
                date = job.date,
                answer = job.answer,
                onClick = { navControler.navigate(Screen.Answer.route) },

            )
        }
    }
}




package cl.app.myjobmap.view


import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import cl.app.myjobmap.R
import cl.app.myjobmap.components.BannerAd
import cl.app.myjobmap.components.JobCard
import cl.app.myjobmap.naviagation.Screen
import cl.app.myjobmap.util.Constants.Companion.ad_id_banner
import cl.app.myjobmap.viewModel.PostulationViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(
    navControler: NavController,
    viewModel: PostulationViewModel
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.mainview),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navControler.navigate(Screen.NewJob.route)
                },
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },

        ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            BannerAd(
                width = LocalConfiguration.current.screenWidthDp,
                height = 50,
                adunitId = ad_id_banner
            )
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

            val backColor: Color

            if (job.answer == stringResource(id = R.string.no_answer)) {
                val jobDate = try {
                    LocalDate.parse(job.date, DateTimeFormatter.ofPattern("dd/MM/yyyy"))
                } catch (e: Exception) {
                    null
                }

                val daysElapsed = jobDate?.let {
                    ChronoUnit.DAYS.between(it, LocalDate.now()).toInt()
                } ?: Int.MAX_VALUE

                backColor = when (daysElapsed) {
                    in 0..7 -> MaterialTheme.colorScheme.onBackground
                    in 8..20 -> MaterialTheme.colorScheme.inversePrimary
                    else -> MaterialTheme.colorScheme.tertiary
                }
            } else {
                backColor = MaterialTheme.colorScheme.onBackground
            }

            JobCard(
                jobTitle = job.job,
                companyName = job.company,
                recruiterName = job.salary,
                date = job.date,
                answer = job.answer,
                onClick = {
                    navControler.navigate(Screen.Answer.route)
                    viewModel.listenID.value = job.id
                },
                backColor = backColor
            )
        }
    }
}

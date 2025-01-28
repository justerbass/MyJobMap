package cl.app.myjobmap.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.app.myjobmap.R
import cl.app.myjobmap.components.BannerAd
import cl.app.myjobmap.components.Separation
import cl.app.myjobmap.naviagation.Screen
import cl.app.myjobmap.util.Constants.Companion.ad_id_banner
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
                    Text(
                        text = stringResource(id = R.string.answer_to) + "\n" + job.value?.job.toString(),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column (modifier = Modifier
            .padding(paddingValues)
        ){
            BannerAd(width = LocalConfiguration.current.screenWidthDp,
                height = 50,
                adunitId = ad_id_banner)
            Column(
                modifier = Modifier
                    .padding(top = 100.dp)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Text(text = stringResource(id = R.string.type_answer),
                    color = MaterialTheme.colorScheme.primary)
                Separation()
                Separation()
                Separation()
                Row(
                    modifier = Modifier.fillMaxWidth(0.9F),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val callItAnswer = stringResource(id = R.string.call_it)
                    Button(
                        onClick = {
                            job.value?.answer = callItAnswer
                            viewModel.updatePostulation(job.value!!)
                            navController.navigate(Screen.Interview.route)
                        },
                        modifier = Modifier.fillMaxWidth(0.6F),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onBackground,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text(text = stringResource(id = R.string.interview),
                            modifier = Modifier.padding(vertical =  5.dp)
                        )
                    }
                }
                Separation()
                Separation()
                Row(
                    modifier = Modifier.fillMaxWidth(0.9F),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val nextStepAnswer = stringResource(id = R.string.next_step)
                    Button(
                        onClick = {
                            job.value?.answer = nextStepAnswer
                            viewModel.updatePostulation(job.value!!)
                            navController.navigate(Screen.MainView.route)
                        },
                        modifier = Modifier.fillMaxWidth(0.6F),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onTertiary,
                            contentColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(text = stringResource(id = R.string.next),
                            modifier = Modifier.padding(vertical =  5.dp)
                        )
                    }
                }
                Separation()
                Separation()
                Row(
                    modifier = Modifier.fillMaxWidth(0.9F),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val reject = stringResource(id = R.string.rejected)
                    Button(
                        onClick = {
                            job.value?.answer = reject
                            viewModel.updatePostulation(job.value!!)
                            navController.navigate(Screen.MainView.route)
                        },
                        modifier = Modifier.fillMaxWidth(0.6F),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text(text = reject,
                            modifier = Modifier.padding(vertical =  5.dp)
                        )
                    }
                }
                Separation()
                Separation()
                Row(
                    modifier = Modifier.fillMaxWidth(0.9F),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val desist = stringResource(id = R.string.desist)
                    Button(
                        onClick = {
                            job.value?.answer = desist
                            viewModel.updatePostulation(job.value!!)
                            navController.navigate(Screen.MainView.route)
                        },
                        modifier = Modifier.fillMaxWidth(0.6F),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.inversePrimary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text(text = desist,
                            modifier = Modifier.padding(vertical =  5.dp)
                        )
                    }
                }
                Separation()
                Separation()
            }
        }

    }
}
package cl.app.myjobmap.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cl.app.myjobmap.R
import cl.app.myjobmap.components.Alert
import cl.app.myjobmap.components.BannerAd
import cl.app.myjobmap.components.Separation
import cl.app.myjobmap.model.Postulation
import cl.app.myjobmap.naviagation.Screen
import cl.app.myjobmap.util.Constants.Companion.ad_id_banner
import cl.app.myjobmap.viewModel.PostulationViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Answer(navController: NavController, viewModel: PostulationViewModel) {
    val id = viewModel.listenID.value
    val job = viewModel.getPostulationById(id).collectAsState(initial = null)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.answer),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate(Screen.MainView.route) }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.alert.value = true
                },

                containerColor = MaterialTheme.colorScheme.tertiary,
                contentColor = MaterialTheme.colorScheme.onSecondary

            ) {
                Icon(
                    imageVector = Icons.Default.Delete, contentDescription = null,
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background
    ) { paddingValues ->
        Column (
            modifier = Modifier
                .padding(paddingValues)
        ) {
            BannerAd(
                width = LocalConfiguration.current.screenWidthDp,
                height = 50,
                adunitId = ad_id_banner
            )
            Column(
                modifier = Modifier
                    .padding(top = 100.dp)
                    .padding(horizontal = 30.dp)
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.background),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {

                Text(
                    text = stringResource(id = R.string.company),
                    color = MaterialTheme.colorScheme.primary
                )
                Separation()
                Separation()
                Text(
                    text = job.value?.company.toString(),
                    color = MaterialTheme.colorScheme.primary
                )
                Separation()
                Separation()
                Text(
                    text = stringResource(id = R.string.postulation),
                    color = MaterialTheme.colorScheme.primary
                )
                Separation()
                Separation()
                Text(
                    text = job.value?.job.toString(),
                    color = MaterialTheme.colorScheme.primary
                )
                Separation()
                Separation()
                Text(
                    text = stringResource(id = R.string.date),
                    color = MaterialTheme.colorScheme.primary
                )
                Separation()
                Separation()
                Text(
                    text = job.value?.date.toString(),
                    color = MaterialTheme.colorScheme.primary
                )
                Separation()
                Separation()
                Text(
                    text = stringResource(id = R.string.question_answer),
                    color = MaterialTheme.colorScheme.primary
                )
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

                    Button(
                        onClick = {
                            job.value?.answer = positiveAnswer
                            viewModel.updatePostulation(job.value!!)
                            navController.navigate(Screen.UpdateAnswer.route)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.onBackground,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.yes).uppercase(),
                            modifier = Modifier.padding(20.dp, 10.dp),
                            fontSize = 20.sp
                        )
                    }

                    Button(
                        onClick = {
                            navController.navigate(Screen.MainView.route)
                            job.value?.answer = negativeAnswer
                            viewModel.updatePostulation(job.value!!)
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.tertiary,
                            contentColor = MaterialTheme.colorScheme.onSecondary
                        )
                    ) {
                        Text(
                            text = stringResource(id = R.string.no).uppercase(),
                            modifier = Modifier.padding(20.dp, 10.dp),
                            fontSize = 20.sp
                        )
                    }
                }
            }
        }

    }
    ShowDelete(viewModel, navController, job)
}

@Composable
fun ShowDelete(viewModel: PostulationViewModel, navController: NavController, job: State<Postulation?>) {


    if (viewModel.alert.value) {
        Alert(
            title = stringResource(id = R.string.delete_job),
            msg = stringResource(id = R.string.message_delete),
            confirmText = stringResource(id = R.string.accept),
            onDismissClick = { viewModel.alert.value = false },
            onConfirmClick = {
                viewModel.alert.value = false
                viewModel.deletePostulation(job.value!!)
                navController.navigate(Screen.MainView.route)
            }
        )

    }
}
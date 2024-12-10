package cl.app.myjobmap.view

import androidx.annotation.FloatRange
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cl.app.myjobmap.R
import cl.app.myjobmap.components.Separation
import cl.app.myjobmap.model.Postulation
import cl.app.myjobmap.naviagation.Screen
import cl.app.myjobmap.viewModel.PostulationViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewJob(
    navControler: NavController,
    viewModel: PostulationViewModel
) {
    val postulation = remember { mutableStateOf("") }
    val company = remember { mutableStateOf("") }
    val recruiter = remember { mutableStateOf("") }
    val description = remember { mutableStateOf("") }
    val salary = remember { mutableStateOf("") }
    val other = remember { mutableStateOf("") }
    val date = remember { mutableStateOf("") }
    val answer = remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navControler.navigate(Screen.MainView.route) }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.NewJob),
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
                    navControler.navigate(Screen.MainView.route)
                    date.value = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))

                    val newPostulation = Postulation(
                        job = postulation.value,
                        company = company.value,
                        date = date.value,
                        jobDescription = description.value,
                        salary = salary.value,
                        recruiter = recruiter.value,
                        answer = answer.value
                    )
                    viewModel.insertPostulation(newPostulation)
                },
                containerColor = MaterialTheme.colorScheme.secondary,
                contentColor = MaterialTheme.colorScheme.onSecondary
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        },

        ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                OutlinedTextField(
                    value = recruiter.value,
                    onValueChange = { recruiter.value = it },
                    label = { Text(text = stringResource(id = R.string.recruiter)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.7F)
                )
                Separation()
            }
            item {
                OutlinedTextField(
                    value = postulation.value,
                    onValueChange = { postulation.value = it },
                    label = { Text(text = stringResource(id = R.string.postulation)) },
                    modifier = Modifier.fillMaxWidth(0.7F)
                )
                Separation()
            }
            item {
                OutlinedTextField(
                    value = company.value,
                    onValueChange = { company.value = it },
                    label = { Text(text = stringResource(id = R.string.company)) },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.7F)
                )
                Separation()
            }
            item {
                OutlinedTextField(
                    value = description.value,
                    onValueChange = { description.value = it },
                    label = { Text(text = stringResource(id = R.string.description)) },
                    modifier = Modifier.fillMaxWidth(0.7F)
                )
                Separation()
            }
            item {
                OutlinedTextField(
                    value = salary.value,
                    onValueChange = { salary.value = it },
                    label = { Text(text = stringResource(id = R.string.salary)) },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth(0.7F)
                )
                Separation()
            }
            item {
                OutlinedTextField(
                    value = other.value,
                    onValueChange = { other.value = it },
                    label = { Text(text = stringResource(id = R.string.other)) },
                    modifier = Modifier.fillMaxWidth(0.7F)
                )
                Separation()
                answer.value = stringResource(id = R.string.no_answer)
            }
            item {
                Separation()
                Box(
                    modifier = Modifier
                        .size(300.dp)
                )
                Separation()
            }
        }
    }
}
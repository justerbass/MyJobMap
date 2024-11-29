package cl.app.myjobmap.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
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
){
    val postulation = remember { mutableStateOf("")}
    val company = remember { mutableStateOf("")}
    val recruiter = remember { mutableStateOf("")}
    val description = remember { mutableStateOf("")}
    val salary = remember { mutableStateOf("")}
    val other = remember { mutableStateOf("")}
    val date = remember { mutableStateOf("")}



    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navControler.navigate(Screen.MainView.route) }
                        ) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.back)
                        )
                    }
                },
                title = {
                    Text(text = stringResource(id = R.string.NewJob))
                }
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
                        answer = "Sin Respuesta"
                    )
                    viewModel.insertPostulation(newPostulation)
                }
            ){
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ){paddingValues ->
        Column (
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            OutlinedTextField(
                value = recruiter.value,
                onValueChange = {recruiter.value = it},
                label = { Text(text = "Recruiter") }
            )
            Separation()
            OutlinedTextField(
                value = postulation.value,
                onValueChange = {postulation.value = it},
                label = { Text(text = "Postulation") }
            )
            Separation()
            OutlinedTextField(
                value = company.value,
                onValueChange = {company.value = it},
                label = { Text(text = "Company") }
            )
            Separation()
            OutlinedTextField(
                value = description.value,
                onValueChange = {description.value = it},
                label = { Text(text = "Description") }
            )
            Separation()
            OutlinedTextField(
                value = salary.value,
                onValueChange = {salary.value = it},
                label = { Text(text = "Salary") }
            )
            Separation()
            OutlinedTextField(
                value = other.value,
                onValueChange = {other.value = it},
                label = { Text(text = "Other") }
            )
            Separation()

        }

    }
}
package cl.app.myjobmap.view

import android.Manifest
import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.CalendarContract
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import cl.app.myjobmap.components.Separation
import cl.app.myjobmap.naviagation.Screen
import cl.app.myjobmap.viewModel.PostulationViewModel
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Interview(navController: NavController, viewModel: PostulationViewModel) {
    val context = LocalContext.current
    val id = viewModel.listenID.value
    val job = viewModel.getPostulationById(id).collectAsState(initial = null)

    val interviewDate = remember { mutableStateOf("") }
    val interviewHour = remember { mutableStateOf("") }
    val interviewPlace = remember { mutableStateOf("") }
    val interviewModal = remember { mutableStateOf("") }

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            interviewDate.value = String.format("%02d-%02d-%04d", dayOfMonth, month + 1, year)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val timePickerDialog = TimePickerDialog(
        context,
        { _, hourOfDay, minute ->
            interviewHour.value = String.format("%02d:%02d", hourOfDay, minute)
        },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        true
    )

    fun addEventDirectlyToCalendar(
        context: Context,
        title: String,
        location: String,
        description: String,
        startTime: Long,
        endTime: Long
    ) {
        val event = ContentValues().apply {
            put(CalendarContract.Events.CALENDAR_ID, 1) // ID del calendario principal
            put(CalendarContract.Events.TITLE, title)
            put(CalendarContract.Events.EVENT_LOCATION, location)
            put(CalendarContract.Events.DESCRIPTION, description)
            put(CalendarContract.Events.DTSTART, startTime)
            put(CalendarContract.Events.DTEND, endTime)
            put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
        }
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_CALENDAR)
            == PackageManager.PERMISSION_GRANTED
        ) {
            val uri = context.contentResolver.insert(CalendarContract.Events.CONTENT_URI, event)
            if (uri != null) {
                Toast.makeText(context, "Evento agregado al calendario", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Error al agregar el evento", Toast.LENGTH_SHORT).show()
            }
        } else {
            ActivityCompat.requestPermissions(
                (context as Activity),
                arrayOf(Manifest.permission.WRITE_CALENDAR),
                1
            )
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Detalles de la Entrevista a\n" + job.value?.job.toString())
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val intent = Intent(Intent.ACTION_EDIT).apply {
                    data = CalendarContract.Events.CONTENT_URI
                    putExtra(
                        CalendarContract.Events.TITLE,
                        "Entrevista: " + job.value?.job.toString()
                    )
                    putExtra(CalendarContract.Events.EVENT_LOCATION, interviewPlace.value)
                    putExtra(
                        CalendarContract.Events.DESCRIPTION,
                        "Modalidad: " + interviewModal.value
                    )

                    val dateParts = interviewDate.value.split("-")
                    val timeParts = interviewHour.value.split(":")
                    if (dateParts.size == 3 && timeParts.size == 2) {
                        val startTime = Calendar.getInstance().apply {
                            set(
                                dateParts[2].toInt(), // Año
                                dateParts[1].toInt() - 1, // Mes
                                dateParts[0].toInt(), // Día
                                timeParts[0].toInt(), // Hora
                                timeParts[1].toInt() // Minutos
                            )
                        }
                        val endTime = Calendar.getInstance().apply {
                            timeInMillis = startTime.timeInMillis + 3600000 // Duración: 1 hora
                        }

                        putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, startTime.timeInMillis)
                        putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.timeInMillis)
                    }
                }

                if (intent.resolveActivity(context.packageManager) != null) {
                    context.startActivity(intent)
                } else {

                    val dateParts = interviewDate.value.split("-")
                    val timeParts = interviewHour.value.split(":")
                    if (dateParts.size == 3 && timeParts.size == 2) {
                        val startTime = Calendar.getInstance().apply {
                            set(
                                dateParts[2].toInt(),
                                dateParts[1].toInt() - 1,
                                dateParts[0].toInt(),
                                timeParts[0].toInt(),
                                timeParts[1].toInt()
                            )
                        }
                        val endTime = Calendar.getInstance().apply {
                            timeInMillis = startTime.timeInMillis + 3600000
                        }
                        addEventDirectlyToCalendar(
                            context,
                            "Entrevista: " + job.value?.job.toString(),
                            interviewPlace.value,
                            "Modalidad: " + interviewModal.value,
                            startTime.timeInMillis,
                            endTime.timeInMillis
                        )
                    }
                }
                navController.navigate(Screen.MainView.route)
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Agregar al calendario")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(text = "Fecha de la entrevista")
            Separation()
            Button(onClick = { datePickerDialog.show() }) {
                Text(if (interviewDate.value.isEmpty()) "Seleccionar fecha" else interviewDate.value)
            }
            Separation()

            Text(text = "Hora de la entrevista")
            Separation()
            Button(onClick = { timePickerDialog.show() }) {
                Text(if (interviewHour.value.isEmpty()) "Seleccionar hora" else interviewHour.value)
            }
            Separation()

            Text(text = "Lugar de la entrevista")
            Separation()
            OutlinedTextField(
                value = interviewPlace.value,
                onValueChange = { interviewPlace.value = it })
            Separation()

            Text(text = "Modalidad")
            Separation()
            OutlinedTextField(
                value = interviewModal.value,
                onValueChange = { interviewModal.value = it })
        }
    }
}

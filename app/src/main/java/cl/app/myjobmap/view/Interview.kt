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
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import cl.app.myjobmap.R
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
    val interviewOrganizer = remember { mutableStateOf("") }

    val interview = stringResource(id = R.string.interview)
    val modal = stringResource(id = R.string.modal)
    val organizer = stringResource(id = R.string.interview_organizer)

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

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.details_interview) + "\n" + job.value?.job.toString())
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val intent = Intent(Intent.ACTION_EDIT).apply {
                    data = CalendarContract.Events.CONTENT_URI
                    putExtra(
                        CalendarContract.Events.TITLE,
                        interview + job.value?.job.toString()
                    )
                    putExtra(CalendarContract.Events.EVENT_LOCATION, interviewPlace.value)
                    putExtra(
                        CalendarContract.Events.DESCRIPTION,
                        modal + interviewModal.value + "\n" +
                        interviewOrganizer + interviewOrganizer.value)

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
                            interview + job.value?.job.toString(),
                            interviewPlace.value,
                            modal + interviewModal.value + "\n" +
                            interviewOrganizer + interviewOrganizer.value,
                            startTime.timeInMillis,
                            endTime.timeInMillis
                        )
                    }
                }
                navController.navigate(Screen.MainView.route)
            }) {
                Icon(imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_to_calendar))
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
            Text(text = stringResource(id = R.string.interview_date))
            Separation()
            Button(onClick = { datePickerDialog.show() }) {
                Text(if (interviewDate.value.isEmpty()) stringResource(id = R.string.select_date)
                else interviewDate.value)
            }
            Separation()

            Text(text = stringResource(id = R.string.interview_hour))
            Separation()
            Button(onClick = { timePickerDialog.show() }) {
                Text(if (interviewHour.value.isEmpty()) stringResource(id = R.string.select_hour)
                else interviewHour.value)
            }
            Separation()

            Text(text = stringResource(id = R.string.interview_place))
            Separation()
            OutlinedTextField(
                value = interviewPlace.value,
                onValueChange = { interviewPlace.value = it })
            Separation()

            Text(text = stringResource(id = R.string.interview_modal))
            Separation()
            OutlinedTextField(
                value = interviewModal.value,
                onValueChange = { interviewModal.value = it })
            Separation()

            Text(text = stringResource(id = R.string.interview_organizer))
            Separation()
            OutlinedTextField(
                value = interviewOrganizer.value,
                onValueChange = { interviewOrganizer.value = it })
        }
    }
}

fun addEventDirectlyToCalendar(
    context: Context,
    title: String,
    location: String,
    description: String,
    startTime: Long,
    endTime: Long
) {
    val event = ContentValues().apply {
        put(CalendarContract.Events.CALENDAR_ID, 1)
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
            Toast.makeText(context, R.string.add_event, Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(context, R.string.error_event, Toast.LENGTH_SHORT).show()
        }
    } else {
        ActivityCompat.requestPermissions(
            (context as Activity),
            arrayOf(Manifest.permission.WRITE_CALENDAR),
            1
        )
    }
}
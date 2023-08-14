package com.example.ageinminutescalculator

import java.text.SimpleDateFormat
import java.util.Locale

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.util.Calendar

class MainActivity : AppCompatActivity() {
    private var selectedDateTextView: TextView ?=null
    private var ageInMinutesTextView: TextView ?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePick: Button = findViewById(R.id.btnDatePicker)
        selectedDateTextView = findViewById(R.id.selectedDate)
        ageInMinutesTextView = findViewById(R.id.ageInMinutes)
        btnDatePick.setOnClickListener{
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){

        val myCalendar=Calendar.getInstance()
        val year=myCalendar.get(Calendar.YEAR)
        val month=myCalendar.get(Calendar.MONTH)
        val day=myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd=DatePickerDialog(/* context = */ this,/* listener = */
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this, "Date Selected", Toast.LENGTH_LONG).show()
            val selectedDate="$selectedDayOfMonth:${selectedMonth + 1}:$selectedYear"
            selectedDateTextView?.text = selectedDate
            val sdf=SimpleDateFormat("dd:MM:yyyy", Locale.ENGLISH)
            val selectedDateSdf= sdf.parse(selectedDate)
            selectedDate?.let {
                val selectedDateInMinutes=selectedDateSdf.time/60000
                val currentDateSdf=sdf.parse(sdf.format(System.currentTimeMillis()))
                currentDateSdf?.let {
                    val currentDateInMinutes=currentDateSdf.time/60000
                    val diffInMinutes=currentDateInMinutes-selectedDateInMinutes
                    ageInMinutesTextView?.text=diffInMinutes.toString()
                }
            }
            },
            year,
            month,
            day)
        dpd.datePicker.maxDate=System.currentTimeMillis()-86400000
        dpd.show()

    }
}
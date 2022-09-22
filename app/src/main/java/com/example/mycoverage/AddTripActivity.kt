package com.example.mycoverage

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

const val TRIP_STEPS = "steps"
const val TRIP_LOCATION = "location"
const val TRIP_DATE = "date"

/**
 * The second activity for adding one more homework
 */
class AddTripActivity : AppCompatActivity() {
    private lateinit var addTripSteps: TextInputEditText
    private lateinit var addTripLocation: TextInputEditText
    private lateinit var addTripDate: TextInputEditText


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_trip_layout)

        findViewById<Button>(R.id.done_button).setOnClickListener {
            addTrip()
        }
        addTripSteps = findViewById(R.id.add_trip_steps)
        addTripLocation = findViewById(R.id.add_trip_location)
        addTripDate = findViewById(R.id.add_trip_date)
    }

    /* The onClick action for the done button. Closes the activity and returns the new homework name
    and description as part of the intent. If the name or description are missing, the result is set
    to cancelled. */
    private fun addTrip() {
        val resultIntent = Intent()

        if (addTripSteps.text.isNullOrEmpty() || addTripLocation.text.isNullOrEmpty() || addTripDate.text.isNullOrEmpty()) {
            setResult(Activity.RESULT_CANCELED, resultIntent)
        } else {
            val steps = addTripSteps.text.toString()
            val location = addTripLocation.text.toString()
            val date = addTripDate.text.toString()
            resultIntent.putExtra(TRIP_STEPS, steps)
            resultIntent.putExtra(TRIP_LOCATION, location)
            resultIntent.putExtra(TRIP_DATE, date)
            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()
    }
}
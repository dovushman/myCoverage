package com.example.mycoverage

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import android.view.View

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private val newTripActivityRequestCode = 1
    private lateinit var mRecycler: RecyclerView

    private var adapter: TripAdapter = TripAdapter(this,
        getTripList()
    )

    private lateinit var dataSource: DataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mRecycler = findViewById(R.id.trip_recycler)

        mRecycler.layoutManager = LinearLayoutManager(this)

        mRecycler.adapter = adapter

        dataSource = DataSource.getDataSource()
        val liveData = dataSource.getTripList()

        liveData.observe(this) {
            it?.let {
                adapter = TripAdapter(this, it)
                mRecycler.adapter = adapter
            }
        }

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            fabOnClick()
        }

        setRecyclerViewItemTouchListener()
    }

    private fun setRecyclerViewItemTouchListener() {

        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, viewHolder1: RecyclerView.ViewHolder): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, swipeDir: Int) {
                val pos = viewHolder.getAdapterPosition()
                val currTrip: Trip? = dataSource.getTripList().value?.get(pos)
                if (currTrip != null) {
                    dataSource.removeTrip(currTrip)
                }
            }
        }

        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(mRecycler)
    }

    private fun fabOnClick() {
        val intent = Intent(this, AddTripActivity::class.java)
        startActivityForResult(intent, newTripActivityRequestCode)
    }

    override fun onActivityResult(request: Int, result: Int, intentData: Intent?) {
        super.onActivityResult(request, result, intentData)

        if (request == newTripActivityRequestCode && result == Activity.RESULT_OK) {
            intentData?.let { data ->
                val tripSteps = data.getStringExtra(TRIP_STEPS)
                val tripLocation = data.getStringExtra(TRIP_LOCATION)
                val tripDate = data.getStringExtra(TRIP_DATE)

                Log.d(TAG, "trying to update the new trip")
                dataSource.insertTrip(tripSteps, tripLocation, tripDate)
            }
        }
    }
 }

//private fun RecyclerView.ViewHolder.getBindingAdapterPosition(): Int {
//
//}


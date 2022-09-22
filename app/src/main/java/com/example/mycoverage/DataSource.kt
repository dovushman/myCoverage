package com.example.mycoverage

import android.content.res.Resources
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

private const val TAG = "DataSource"

class DataSource {

    private val initialList = com.example.mycoverage.getTripList()
    private val liveData = MutableLiveData(initialList)


    fun insertTrip(steps: String?, location: String?, date: String?){
        if (steps == null || location == null || date == null) {
            return
        }

        val newTrip = Trip(steps, location, date)
        addTrip(newTrip)

    }

    /**
     * Adds trip to liveData and posts value.
     */
    private fun addTrip(trip: Trip){

        val currList = liveData.value
        if (currList == null){
            liveData.postValue(listOf(trip))
        } else {
            Log.d(TAG, "update the current trip list")
            val updatedList = currList.toMutableList()
            updatedList.add(0, trip)
            liveData.postValue(updatedList)
        }
    }

    /**
     * Removes trip from liveData and posts value.
     */
    fun removeTrip(trip: Trip) {
        val currList =liveData.value
        if (currList != null) {
            val updatedList = currList.toMutableList()
            updatedList.remove(trip)
            liveData.postValue(updatedList)
        }

    }

    fun getTripList(): LiveData<List<Trip>> {
        return liveData
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource()
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}
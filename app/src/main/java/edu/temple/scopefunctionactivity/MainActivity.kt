package edu.temple.scopefunctionactivity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val array1: List<Double> = List(5) { it * 1.1 }
        val array2: List<Double> = List(5) { it * 3.0 }
        // You can test your helper functions by  calling them from onCreate() and
        // printing their output to the Log, which is visible in the LogCat:
        // eg. Log.d("function output", getTestDataArray().toString())
        Log.d("function 1 output original", getTestDataArray().toString())
        Log.d("function 1 output modified", getTestDataArray2().toString())

        Log.d("function 2 output original", averageLessThanMedian(array1).toString())
        Log.d("function 2 output original", averageLessThanMedian2(array1).toString())
    }


    /* Convert all the helper functions below to Single-Expression Functions using Scope Functions */
    // eg. private fun getTestDataArray() = ...

    // HINT when constructing elaborate scope functions:
    // Look at the final/return value and build the function "working backwards"

    // Return a list of random, sorted integers
    private fun getTestDataArray() : List<Int> {
        val testArray = MutableList(10){ Random.nextInt()}
        testArray.sort()
        return testArray
    }

    private fun getTestDataArray2() : List<Int> =
        MutableList(10){ Random.nextInt() }.apply{this.sort()}

    // Return true if average value in list is greater than median value, false otherwise
    private fun averageLessThanMedian(listOfNumbers: List<Double>): Boolean {
        val avg = listOfNumbers.average()
        val sortedList = listOfNumbers.sorted()
        val median = if (sortedList.size % 2 == 0)
            (sortedList[sortedList.size / 2] + sortedList[(sortedList.size - 1) / 2]) / 2
        else
            sortedList[sortedList.size / 2]

        return avg < median
    }

    private fun averageLessThanMedian2(listOfNumbers: List<Double>) : Boolean =
        listOfNumbers.average() < listOfNumbers.sorted().let{ sortedList ->
                if(sortedList.size % 2 == 0){
                    (sortedList[sortedList.size / 2] + sortedList[(sortedList.size - 1) / 2]) / 2
                }else{
                    sortedList[sortedList.size / 2]
                }
        }

    // Create a view from an item in a collection, but recycle if possible (similar to an AdapterView's adapter)
    private fun getView(position: Int, recycledView: View?, collection: List<Int>, context: Context): View {
        val textView: TextView

        if (recycledView != null) {
            textView = recycledView as TextView
        } else {
            textView = TextView(context)
            textView.setPadding(5, 10, 10, 0)
            textView.textSize = 22f
        }

        textView.text = collection[position].toString()

        return textView
    }

}
package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var topList: ArrayList<Top>
    lateinit var myRV: RecyclerView
    lateinit var rvAdapter: RVAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRV = findViewById(R.id.rvMain)
//        rvAdapter = RVAdapter(topList)
//        myRV.adapter = rvAdapter
//        myRV.layoutManager = LinearLayoutManager(MainActivity())

        val getButton = findViewById<Button>(R.id.btTop10)

        getButton.setOnClickListener {
            parsing()
        }

    }

    private fun parsing() {
        CoroutineScope(IO).launch {
            val data = withContext(Dispatchers.Default) {
                val parser = MyXmlPullParserHandler()
                parser.parse()
            }
            Log.d("MAIN", "$data")

            withContext(Main) {
                topList = data
                rvAdapter = RVAdapter(topList)
                myRV.adapter = rvAdapter
                myRV.layoutManager = LinearLayoutManager(MainActivity())
                Log.d("MAIN", "11able to get data${topList.size}")
            }
        }
    }
}
package com.example.flowoperators

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        GlobalScope.launch(Dispatchers.Main){
            producer()
//                .onStart {
//                    emit(-1)
//                    Log.d("Harsh","Starting Out")
//                }.onCompletion {
//                    emit(6)
//                    Log.d("Harsh","Completed")
//                }.onEach {
//                    Log.d("Harsh","About to Emit - $it")
//                }

                .collect{
                Log.d("Harsh",it.toString())
            }
            Log.d("Harsh",producer().first().toString())
            Log.d("Harsh",producer().toList().toString())
            producer().map {
                it * 2
            }.filter {
                it < 8
            }

        }

    }
    private fun producer():Flow<Int>{
        return flow<Int> {
            val list = listOf(1,2,3,4,5)
            list.forEach{
                delay(1000)
                emit(it)
            }
        }
    }
}
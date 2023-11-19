package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy (LazyThreadSafetyMode.NONE){
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.btnInc.setOnClickListener {
            viewModel.inc()
        }

        binding.btnDec.setOnClickListener {
            viewModel.dec()
        }

        viewModel.counterLv.observe(this) { result ->
            binding.tvResult.text = result.toString()
        }
    }
}
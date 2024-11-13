package com.alnino.customview

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import com.alnino.customview.databinding.ActivityMainBinding
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val message = listOf(
        "Raih Tujuanmu, Satu Tabungan dalam Sekali Langkah",
        "Tabungan Tepat Sasaran, Tujuan di Depan Mata",
        "Mencapai Impian, Lewat Setiap Simpanan",
    )

    private var messageIndex = 0
    private val switchDelay = 2000L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        startTypewriterEffect()
    }

    private fun startTypewriterEffect() {
        lifecycleScope.launch {
            while (true) {
                // start animation
                binding.Typewriter.animateText(message[messageIndex])

                // delay before switching to next message
                delay(switchDelay + message[messageIndex].length * 125)

                // reset the index when it is the last text
                messageIndex = (messageIndex + 1) % message.size
            }
        }
    }


}
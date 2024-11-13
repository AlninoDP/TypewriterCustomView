package com.alnino.customview

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class TypeWriterTextView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0,
) : AppCompatTextView(context, attrs, defStyleAttr), CoroutineScope {

    private var job: Job? = null
    override val coroutineContext: CoroutineContext = Dispatchers.Main

    fun animateText(text: CharSequence ) {
        job?.cancel() // Cancel any existing job
        job = launch {
            setText("")
            text.forEachIndexed { _, char ->
                append(char.toString()) // add one character at a time
                delay(100L)
            }
        }
    }

    override fun onDetachedFromWindow() {
        job?.cancel()
        super.onDetachedFromWindow()
    }

}
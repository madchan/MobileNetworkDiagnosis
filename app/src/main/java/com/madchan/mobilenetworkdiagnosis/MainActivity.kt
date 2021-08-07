package com.madchan.mobilenetworkdiagnosis

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.madchan.comp.networkdiagnosis.ExecuteCallback
import com.madchan.comp.networkdiagnosis.Ping
import com.madchan.comp.networkdiagnosis.TraceRoute

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text)

        Thread() {
            val ping = Ping(host = "www.baidu.com", count = 5)
            ping.execute(object : ExecuteCallback {

                override fun onExecuting(line: String) {
                    runOnUiThread {
                        textView.append(line)
                        textView.append("\n")
                    }
                }

                override fun onCompleted(result: String) {
                    runOnUiThread {
                        textView.append("\n\n\n")
                    }

                    TraceRoute("www.baidu.com").execute(object : ExecuteCallback{
                        override fun onExecuting(line: String) {
                            runOnUiThread {
                                textView.append(line)
                                textView.append("\n")
                            }
                        }

                        override fun onCompleted(result: String) {
                            TODO("Not yet implemented")
                        }

                    })
                }
            })
        }.start()

    }
}
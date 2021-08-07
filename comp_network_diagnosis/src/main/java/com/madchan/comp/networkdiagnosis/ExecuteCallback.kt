package com.madchan.comp.networkdiagnosis

interface ExecuteCallback {
    fun onExecuting(line: String)
    fun onCompleted(result: String)
}
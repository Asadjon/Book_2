package com.cyberpanterra.book_2.database

import android.content.Context
import android.util.Log
import java.io.*

/* 
   The creator of the DatabaseCopyFromAssets class is Asadjon Xusanjonov
   Created on 18:25, 17.02.2021
*/
const val TAG: String = "DatabaseCopyFromAssets"

open class DatabaseCopyFromAssets constructor(private val context: Context, var mFileName: String) {
    var mFilePath: String = context.applicationInfo.dataDir + "/cache/"

    fun getFileValue(): String {
        val file = File("$mFilePath$mFileName")
        return try {
            if (!file.exists()) saveFile(getFileValue(context.assets.open(file.name)))
            getFileValue(FileInputStream(file))
        } catch (e: IOException) {
            e.printStackTrace()
            "{\"Contents\": []}"
        }
    }

    private fun getFileValue(inputStream: InputStream): String {
        val bytes = ByteArray(inputStream.available())
        inputStream.read(bytes)
        inputStream.close()
        return String(bytes)
    }

    fun saveFile(value: String) {
        val file = File("$mFilePath$mFileName")
        try {
            val fileOutputStream = OutputStreamWriter(FileOutputStream(file))
            fileOutputStream.write(value)
            fileOutputStream.close()

            Log.i(TAG, "$mFilePath$mFileName saved")
        } catch (e: IOException) {
            e.printStackTrace()
            Log.i(TAG, "$mFilePath$mFileName not saved")
        }
    }

    fun deleteFile() {
        val file = File("$mFilePath$mFileName")
        if (file.exists()) {
            file.delete()
            Log.i(TAG, "$mFilePath$mFileName deleted")
        } else Log.i(TAG, "$mFilePath$mFileName not deleted")
    }
}
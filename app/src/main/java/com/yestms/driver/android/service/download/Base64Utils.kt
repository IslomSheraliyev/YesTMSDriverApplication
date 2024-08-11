package com.yestms.driver.android.service.download

import android.content.Context
import android.os.Environment
import android.util.Base64
import android.util.Log
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class Base64Utils(private val context: Context) {

    // Function to save a Base64 string as a ZIP file
//    fun saveBase64AsZip(base64: Base64, fileName: String) {
//        // Decode the Base64 data into a ByteArray
//        val decodedBytes = Base64.decode(base64Data, Base64.DEFAULT)
//
//        // Get the Downloads directory
//        val downloadsDirectory = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
//
//        // Create the file in the Downloads directory
//        val file = File(downloadsDirectory, fileName)
//
//        try {
//            // Write the decoded bytes to the file
//            FileOutputStream(file).use { outputStream ->
//                outputStream.write(decodedBytes)
//            }
//            Log.d("Success", "File saved successfully: $fileName")
//        } catch (e: IOException) {
//            Log.e("Error", "Failed to save file: ${e.message}")
//            e.printStackTrace()
//        }
//    }
}

package com.example.apmc1

import android.graphics.Bitmap
import android.graphics.Point
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidmads.library.qrgenearator.QRGContents
import androidmads.library.qrgenearator.QRGEncoder
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.WriterException

class QRcreator : AppCompatActivity() {
    //vaiables for imageview,edittext,button, bitmap and qrencoder.
    private lateinit var qrCodeIV: ImageView
    private lateinit var dataEdt: EditText
    private lateinit var dataEdt2:EditText
    private lateinit var dataEdt3:EditText
    private lateinit var dataEdt4:EditText
    private lateinit var generateQrBtn: Button

    private lateinit var con:EditText
    var bitmap: Bitmap? = null
    private lateinit var qrgEncoder: QRGEncoder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_qrcreator)
        //initializing all variables.
        qrCodeIV = findViewById<ImageView>(R.id.idIVQrcode)
        dataEdt = findViewById<EditText>(R.id.nametf)
        dataEdt2 = findViewById<EditText>(R.id.addrtf)
        dataEdt3 = findViewById<EditText>(R.id.producttf)
        dataEdt4 = findViewById<EditText>(R.id.quantitytf)
        generateQrBtn = findViewById<Button>(R.id.idBtnGenerateQR)
        val stringList: ArrayList<String> = ArrayList()
        




        //intializing onclick listner for button.
        generateQrBtn.setOnClickListener(View.OnClickListener {
            if (TextUtils.isEmpty(dataEdt.getText().toString())) {
                //if the edittext inputs are empty then execute this method showing a toast message.
                Toast.makeText(
                    this@QRcreator,
                    "Enter some text to generate QR Code",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                //concatenation of all edittext data

                val inputText1 = dataEdt.text.toString()
                val inputText2 = dataEdt2.text.toString()
                val inputText3 = dataEdt3.text.toString()
                val inputText4 = dataEdt4.text.toString()

                val combinedText = "$inputText1 - $inputText2 - $inputText3 -$inputText4"
                stringList.add(combinedText)

                if (inputText1.isNotEmpty() && inputText2.isNotEmpty() && inputText3.isNotEmpty() && inputText4.isNotEmpty()) {

                    Toast.makeText(this, "Data added: $combinedText", Toast.LENGTH_SHORT).show()
                    dataEdt.text.clear()
                    dataEdt2.text.clear()
                    dataEdt3.text.clear()
                    dataEdt4.text.clear()

                } else {
                    Toast.makeText(this, "Please enter text in fields", Toast.LENGTH_SHORT).show()
                }

                //closing concatenation

                //below line is for getting the windowmanager service.
                val manager = getSystemService(WINDOW_SERVICE) as WindowManager
                //initializing a variable for default display.
                val display = manager.defaultDisplay
                //creating a variable for point which is to be displayed in QR Code.
                val point = Point()
                display.getSize(point)
                //getting width and height of a point
                val width = point.x
                val height = point.y
                //generating dimension from width and height.
                var dimen = if (width < height) width else height
                dimen = dimen * 3 / 4
                //setting this dimensions inside our qr code encoder to generate our qr code.
                qrgEncoder =
                    QRGEncoder(combinedText.toString(), null, QRGContents.Type.TEXT, dimen)
                try {
                    //getting our qrcode in the form of bitmap.
                    bitmap = qrgEncoder.bitmap
                    // the bitmap is set inside our image view using .setimagebitmap method.
                    qrCodeIV.setImageBitmap(bitmap)
                } catch (e: WriterException) {
                    //this method is called for exception handling.
                    Log.e("Tag", e.toString())
                }
            }
        })
    }
}
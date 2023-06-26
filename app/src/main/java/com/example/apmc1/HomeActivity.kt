package com.example.apmc1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // button for logout and initializing our button.
        val logoutBtn = findViewById<Button>(R.id.idBtnLogout)

        //connecting qr scanner creator
        val QR_creat_Btn = findViewById<Button>(R.id.idBtnQRcreater)
        // adding onclick Listner for QR_create_Btn
        QR_creat_Btn.setOnClickListener {
            startActivity(Intent(this,QRcreator::class.java ))
        }

        // adding onclick listener for our logout button.
        logoutBtn.setOnClickListener {
            // below line is for getting instance
            // for AuthUi and after that calling a
            // sign out method from FIrebase.
            AuthUI.getInstance()
                .signOut(this@HomeActivity) // after sign out is executed we are redirecting
                // our user to MainActivity where our login flow is being displayed.
                .addOnCompleteListener { // below method is used after logout from device.
                    Toast.makeText(this@HomeActivity, "User Signed Out", Toast.LENGTH_SHORT).show()

                    // below line is to go to MainActivity via an intent.
                    val i = Intent(this@HomeActivity, MainActivity::class.java)
                    startActivity(i)
                }
        }
    }
}
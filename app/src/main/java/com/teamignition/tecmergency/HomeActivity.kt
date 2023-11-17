package com.teamignition.tecmergency

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.teamignition.tecmergency.ui.theme.TecmergencyTheme
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth


enum class ProviderType{
    BASIC
}
class HomeActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val bundle = intent.extras
        val email = bundle?.getString("email")
        val provider = bundle?.getString("provider")
        setup(email ?: "", provider ?:"")

    }
    private fun setup(email:String, provider:String){
        title = "Inicio"
        findViewById<TextView>(R.id.providerTextView).setText(provider)
        findViewById<TextView>(R.id.emailTextView).setText(email)

        //Boton Registrar
        val logOutButton = findViewById<View>(R.id.logOutButton) as Button
        logOutButton.setOnClickListener {

            FirebaseAuth.getInstance().signOut()
            onBackPressed()
            false
        }
        //BotonRegistrar



    }


}

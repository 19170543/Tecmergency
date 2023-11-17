package com.teamignition.tecmergency


import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.google.firebase.auth.FirebaseAuth
import java.security.Provider


class AuthActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)


        val bundle = Bundle()
        bundle.putString("message", "integracion de firebase completa")

        //Boton Registrar
        val boton = findViewById<View>(R.id.signUpButton) as Button
        boton.setOnClickListener {
            System.out.println("QUEONDA")
            val correo = findViewById<EditText>(R.id.emailEditText).text.toString();
            val contra = findViewById<EditText>(R.id.passwordEditText).text.toString();

            if ( correo.isNotEmpty() && contra.isNotEmpty()){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo,contra).addOnCompleteListener{
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {showAlert()}
                }
            }
            false
        }
        //BotonRegistrar

        //Boton Logear
        val botonLogear = findViewById<View>(R.id.logInButton) as Button
        botonLogear.setOnClickListener {
            val correo = findViewById<EditText>(R.id.emailEditText).text.toString();
            val contra = findViewById<EditText>(R.id.passwordEditText).text.toString();

            if ( correo.isNotEmpty() && contra.isNotEmpty()){
                FirebaseAuth.getInstance().signInWithEmailAndPassword(correo,contra).addOnCompleteListener{
                    if (it.isSuccessful){
                        showHome(it.result?.user?.email ?: "", ProviderType.BASIC)
                    } else {showAlert()}
                }
            }
            false
        }
        //BotonLogear








    }
    @SuppressLint("SuspiciousIndentation")
    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
            builder.setTitle("Error")
        builder.setMessage("Se ha producido un erorr autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()

    }
    private fun showHome(email: String, provider: ProviderType){
        val homeIntent = Intent(this,HomeActivity::class.java).apply {
            putExtra("email",email)
            putExtra("Provider", provider.name)
        }
        startActivity(homeIntent)

    }

}


package co.edu.udea.compumovil.gr02_20241.lab1

import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.content.Intent

class ContactDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact_data)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // CONFIGURACIÓN DEL BOTÓN SIGUIENTE
        val sigButton: Button = findViewById(R.id.sigButton)
        sigButton.setOnClickListener {
            if (validateFields()) {
                // Aquí iría el código para guardar o procesar los datos
                // por ahora solo mostraremos un mensaje
                Toast.makeText(this, "Datos de contacto guardados correctamente", Toast.LENGTH_SHORT).show()
                // Imprimir los datos en Logcat
                printData()
            } else {
                Toast.makeText(this, "Por favor complete todos los campos obligatorios o ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Función para validar campos obligatorios y el formato del correo electrónico
    private fun validateFields(): Boolean {
        val telefonoEditText: EditText = findViewById(R.id.editTextPhone)
        val direccionEditText: EditText = findViewById(R.id.editTextAddress)
        val emailEditText: EditText = findViewById(R.id.editTextEmail)
        val paisEditText: EditText = findViewById(R.id.autoCompleteCountry)
        val ciudadEditText: EditText = findViewById(R.id.autoCompleteCity)

        val telefono = telefonoEditText.text.toString().trim()
        val direccion = direccionEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val pais = paisEditText.text.toString().trim()
        val ciudad = ciudadEditText.text.toString().trim()

        // Verificar si alguno de los campos obligatorios está vacío
        if (telefono.isEmpty() || direccion.isEmpty() || email.isEmpty() || pais.isEmpty() || ciudad.isEmpty()) {
            return false
        }

        // Verificar si el correo electrónico tiene un formato válido utilizando una expresión regular
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false
        }

        return true
    }

    // Función para imprimir los datos ingresados en Logcat
    private fun printData() {
        val telefonoEditText: EditText = findViewById(R.id.editTextPhone)
        val direccionEditText: EditText = findViewById(R.id.editTextAddress)
        val emailEditText: EditText = findViewById(R.id.editTextEmail)
        val paisEditText: EditText = findViewById(R.id.autoCompleteCountry)
        val ciudadEditText: EditText = findViewById(R.id.autoCompleteCity)

        val telefono = telefonoEditText.text.toString().trim()
        val direccion = direccionEditText.text.toString().trim()
        val email = emailEditText.text.toString().trim()
        val pais = paisEditText.text.toString().trim()
        val ciudad = ciudadEditText.text.toString().trim()

        // Imprimir los datos en Logcat
        val tag = "Información de contacto"
        Log.d(tag, "Teléfono: $telefono")
        Log.d(tag, "Dirección: $direccion")
        Log.d(tag, "Email: $email")
        Log.d(tag, "País: $pais")
        Log.d(tag, "Ciudad: $ciudad")
    }

}



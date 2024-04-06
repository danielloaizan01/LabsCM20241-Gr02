package co.edu.udea.compumovil.gr02_20241.lab1


import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Calendar

class PersonalDataActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_personal_data)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //CONFIGURACIÓN BOTON SIGUIENTE A DATA CONTACT
        val sigButton: Button = findViewById(R.id.sigButton)
        sigButton.setOnClickListener{
            if (validateFields()) {
                val intent = Intent(this, ContactDataActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Por favor complete todos los campos obligatorios", Toast.LENGTH_SHORT).show()
            }
        }

        //CONFIGURACIÓN BOTONES DE HOMBRE / MUJER
        val hombreButton: RadioButton = findViewById(R.id.hombreButton)
        val mujerButton: RadioButton = findViewById(R.id.mujerButton)
        hombreButton.setOnClickListener{
            mujerButton.isChecked = false
        }
        mujerButton.setOnClickListener {
            hombreButton.isChecked = false
        }

        //CONFIGURACION DE DATEPICKER
        val datePickerButton: Button = findViewById(R.id.datePicker)
        datePickerButton.setOnClickListener{
            // Obtener la fecha actual
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                this,
                year,
                month,
                dayOfMonth
            )
            datePickerDialog.show()
        }

        //CONFIGURACION DE NIVEL ACADEMICO
        val spinner: Spinner = findViewById(R.id.my_spinner)
        val items = listOf("Nivel académico", "Primaria", "Secundaria", "Universidad", "Otro")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter = adapter
        spinner.setSelection(0)
        spinner.onItemSelectedListener = object :AdapterView.OnItemSelectedListener{
            override fun onItemSelected( parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val selectedItem = items[position]
                Toast.makeText(this@PersonalDataActivity, "$selectedItem", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}

        }
    }

    //FECHA SELECCIONADA:
    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        val selectedDate = "$dayOfMonth/${month + 1}/$year"
        val datePickerButton: Button = findViewById(R.id.datePicker)
        datePickerButton.text = selectedDate
        if(selectedDate.isEmpty()){
            datePickerButton.text = "Seleccionar fecha"
        }
        Toast.makeText(this, "Fecha seleccionada: $selectedDate", Toast.LENGTH_SHORT).show()
    }

    private fun validateFields(): Boolean {
        val nombreEditText: EditText = findViewById(R.id.textNombre)
        val apellidoEditText: EditText = findViewById(R.id.textApellido)
        val datePickerButton: Button = findViewById(R.id.datePicker)
        val hombreButton: RadioButton = findViewById(R.id.hombreButton)
        val mujerButton: RadioButton = findViewById(R.id.mujerButton)
        val spinner: Spinner = findViewById(R.id.my_spinner)

        val nombre = nombreEditText.text.toString().trim()
        val apellido = apellidoEditText.text.toString().trim()
        val fechaNacimiento = datePickerButton.text.toString()
        val sexo = if (hombreButton.isChecked) "Hombre" else if (mujerButton.isChecked) "Mujer" else "Opcional"
        val nivelAcademico = spinner.selectedItem.toString()

        // Verificar si alguno de los campos obligatorios está vacío
        if (nombre.isEmpty() || apellido.isEmpty() || fechaNacimiento.isEmpty() || fechaNacimiento == "Fecha Nacimiento") {
            return false
        }

        // Imprimir los datos en el Logcat
        Log.d("Informacion personal", "Nombre: $nombre")
        Log.d("Informacion personal", "Apellido: $apellido")
        Log.d("Informacion personal", "Fecha de nacimiento: $fechaNacimiento")
        Log.d("Informacion personal", "Sexo: $sexo")
        Log.d("Informacion personal", "Nivel academico: $nivelAcademico")

        return true
    }
}

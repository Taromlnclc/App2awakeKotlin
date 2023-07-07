package com.example.app2awake

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.EditText
import android.text.TextWatcher
import android.text.Editable
import android.content.DialogInterface
import android.util.Log
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog

/*
 Quedaron sin uso....
import com.example.app2awake.R
import android.view.animation.Animation
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //implementacion de deeccion de ingreso en valor y filtro solo numeros en el input
        val textView = findViewById<TextView>(R.id.resultado)  // toma textView como un valor unico, en findViewById especifica que es un TextView
        val editText = findViewById<EditText>(R.id.valor)
        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) { // Las llamadas son funciones
                // Antes de que el texto cambie
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // Durante el cambio del texto
            }

            override fun afterTextChanged(editable: Editable) {
                val num = findViewById<EditText>(R.id.valor)
                if (num.length() != 0) {
                    var text = editText.text.toString() // Asign EditText de forma variable

                    // identifica la bandera y activa
                    val textoBandera = findViewById<TextView>(R.id.bandera)
                    val bandText = textoBandera.text.toString()
                    val numOtro = findViewById<TextView>(R.id.resultado)
                    val numText = numOtro.text.toString()
                    if (bandText.isEmpty()) {  // Antes condicion > bandText.length == 0
                        // El TextView no está vacío
                        // Realiza las acciones necesarias
                        if (numText.isNotEmpty()) { // Antes condicion >  numText.length != 0
                            text = numText + text
                        }
                        textView.text = text
                        textView.text = text
                        editText.setText("")
                    }
                }
            }
        })
    }
    //Transformo a funciones
    // Indica que no utiliza view: View : fun botonSuma(view: View)
    fun botonSuma(view: View) {
        //log
        Log.d("DEPURACION", "Presiona boton de Suma.")
        //activa animacion
        val suma = findViewById<ImageView>(R.id.suma)
        val animation = AnimationUtils.loadAnimation(this, R.anim.set)
        suma.startAnimation(animation)
        //ejecutar operacion
        operacion("+")
    }

    // Indica que no utiliza view: View : fun botonResta(view: View)
    fun botonResta(view: View) {
        //log
        Log.d("DEPURACION", "Presiona boton de Resta.")
        //activa animacion
        val resta = findViewById<ImageView>(R.id.resta)
        val animation = AnimationUtils.loadAnimation(this, R.anim.set)
        resta.startAnimation(animation)
        //ejecutar operacion
        operacion("-")
    }

    // Indica que no utiliza view: View : fun botonMulti(view: View)
    fun botonMulti(view: View) {
        //log
        Log.d("DEPURACION", "Presiona boton de Multiplicacion.")
        //activa animacion
        val multi = findViewById<ImageView>(R.id.multi)
        val animation = AnimationUtils.loadAnimation(this, R.anim.set)
        multi.startAnimation(animation)
        //ejecutar operacion
        operacion("*")
    }

    // Indica que no utiliza view: View : fun botonDivi(view: View)
    fun botonDivi(view: View) {
        //log
        Log.d("DEPURACION", "Presiona boton de Division.")
        //activa animacion
        val igual = findViewById<ImageView>(R.id.divide)
        val animation = AnimationUtils.loadAnimation(this, R.anim.set)
        igual.startAnimation(animation)
        //ejecutar operacion
        operacion("/")
    }

    //opraciones artimeticas
    private fun operacion(oper: String) {
        //activa bandera, verifica valor ingresado para int y calcular
        val num = findViewById<EditText>(R.id.valor)
        val textoBandera = findViewById<TextView>(R.id.bandera)
        if (num.length() != 0) {
            val num1 = num.text.toString().toInt()

            // Obtener los números ingresados
            val numOtro = findViewById<TextView>(R.id.resultado)
            val numText = numOtro.text.toString()
            var num2 = 0
            if (numText.isNotEmpty()) { // Antes condicion numText.length != 0
                // El TextView no está vacío
                num2 = numOtro.text.toString().toInt()
            }
            var opera = 0
            when (oper) {
                "+" ->                     // Realizar la suma
                    opera = num2 + num1
                "-" ->                     // Realizar la resta
                    opera = num2 - num1
                "*" ->                     // Realizar la multi
                    opera = num2 * num1
                "/" ->                     // Realizar la divi
                    opera = num2 / num1
                else -> {}
            }
            // Mostrar el resultado en el TextView, limpiar Edittext
            numOtro.text = opera.toString()
            num.setText("")
        } else {
            //pasa bandera y veriifca si esta vacio...
            if (textoBandera.length() != 0) {
                mostrarAlerta("Error al ejecutar operador $oper")
            }
        }
        textoBandera.text = "X"
    }

    //Creo una funcion privada
    //mensajes de alerta
    private fun mostrarAlerta(titulo: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(titulo)
            .setMessage("Debe ingresar valor.")
            .setPositiveButton("Aceptar") { dialog: DialogInterface, _: Int -> dialog.dismiss() } // Indica variable no usada remplazar por _
        val alert = builder.create()
        alert.show()
    }

    // Indica que no utiliza view: View : fun botonCerrar(view: View)
    //cierra app
    fun botonCerrar(view: View) {
        finishAffinity() // Cierra la aplicación completamente al estar depurando no hace cierra total, probar  System.exit(0); no recomendado
    }

}
package com.calculadoraimc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.calculadoraimc.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bt_calcular = binding.calcular
        val msm = binding.mensagem

        bt_calcular.setOnClickListener {
            val receber_peso = binding.peso.text.toString()
            val receber_altura = binding.altura.text.toString()

            if (receber_peso.isEmpty()){
                msm.setText("Informa seu peso")
            }else if (receber_altura.isEmpty()){
                msm.setText("Informa sua altura")
            }else{
                CalcularIMC(receber_peso, receber_altura)
            }

        }
    }

    private fun CalcularIMC(peso: String,altura: String){
        val peso = Integer.parseInt(binding.peso.text.toString())
        val altura = java.lang.Float.parseFloat(binding.altura.text.toString() )
        val resultado = binding.mensagem
        val imc =  peso / (altura * altura)

        val message =when{
            imc <= 18.5 ->"Peso baixo para vc"
            imc <= 24.9 ->"Peso normal"
            imc <=29.9  ->"Sobrepeso"
            imc <=34.9  ->"Obsidade Grau 1"
            imc <=39.9  ->"Obsidade Grau 2"
            else  ->"Obsidade MORBIDA"
        }
        imc.toString()
        resultado.setText("IMC: $imc \n$message")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate = menuInflater
        inflate.inflate(R.menu.menu_main,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.reset -> {
                val limparPeso = binding.peso
                val limparAltura = binding.altura
                val limparMSM = binding.mensagem

                limparPeso.setText("")
                limparAltura.setText("")
                limparMSM.setText("")
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
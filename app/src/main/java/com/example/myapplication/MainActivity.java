package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.Console;

public class MainActivity extends AppCompatActivity {

    boolean operacao = false, clear = false, float1 = false, float2 = false, acumulo = false;
    String valor1 = "", valor2 = "", nomeOperacao = "";
    Double res = 0.0;

    public void operacao(View view){
        TextView texto = (TextView) findViewById(R.id.textoValor);
        Button botao = (Button) view;
        if(!((String) botao.getText()).equals("=") && !acumulo) {
            nomeOperacao = (String) botao.getText();
        }

        if(clear){
            valor1 = res.toString();
            valor2 = "";
            float2 = false;
            clear = false;
        }

        if(operacao) {

            if(valor1.isEmpty()){
                valor1 = "0";
                float1 = false;
            }else if(valor2.isEmpty()){
                valor2 = "0";
                float2 = false;
            }

            if (nomeOperacao.equals("+")) {
                 res = Double.parseDouble(valor1) + Double.parseDouble(valor2);
            } else if (nomeOperacao.equals("-")) {
                 res = Double.parseDouble(valor1) - Double.parseDouble(valor2);
            } else if (nomeOperacao.equals("*")) {
                 res = Double.parseDouble(valor1) * Double.parseDouble(valor2);
            } else if (nomeOperacao.equals("÷")) {
                 res = Double.parseDouble(valor1) / Double.parseDouble(valor2);
            } else if (nomeOperacao.equals("^")) {
                 res = Math.pow(Double.parseDouble(valor1), Double.parseDouble(valor2));
            }

            if(((String)botao.getText()).equals("=")){
                if(res % 1 == 0){
                    int parse = (int) (double) res;
                    texto.setText(String.valueOf(parse));
                }else{
                    texto.setText(String.valueOf(res));
                }
                clear = true;
                operacao = false;
                acumulo = false;
            }else{
                if(acumulo){
                    nomeOperacao = botao.getText().toString();
                }
                valor1 = res.toString();
                valor2 = "";
                float2 = false;
                if(res % 1 == 0){
                    int parse = (int) (double) res;
                    texto.setText(String.valueOf(parse));
                }else{
                    texto.setText(String.valueOf(res));
                }
                clear = false;
            }
        }else{
            operacao = true;
            acumulo = true;
            texto.setText("0");
        }
    }

    @SuppressLint("SetTextI18n")
    public void tecla(View view){
        Button tecla = (Button) view;
        TextView texto = (TextView) findViewById(R.id.textoValor);
        if(clear){
            valor1 = "";
            valor2 = "";
            float1 = false;
            float2 = false;
            clear = false;
        }
        if(!operacao){
            if(tecla.getText().equals(".") && valor1.isEmpty()){
                valor1 = "0.";
                texto.setText(valor1);
                float1 = true;
            }else if(tecla.getText().equals(".") && float1) {
                Log.d("Aviso", "Número já tem ponto flutuante");
            }else {
                    valor1 += (String) tecla.getText();
                    texto.setText(valor1);
                    if(tecla.getText().equals(".")){
                        float1 = true;
                    }
            }
        }else{
            if(tecla.getText().equals(".") && valor2.isEmpty()){
                valor2 = "0.";
                texto.setText(valor2);
                float2 = true;
            }else if(tecla.getText().equals(".") && float2) {
                Log.d("Aviso", "Número já tem ponto flutuante");
            }else {
                valor2 += (String) tecla.getText();
                texto.setText(valor2);
                if(tecla.getText().equals(".")){
                    float2 = true;
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button root = (Button) findViewById(R.id.buttonRoot);
        root.setOnClickListener((View.OnClickListener) v -> {
            if(clear){
                valor1 = res.toString();
                valor2 = "";
                float2 = false;
                clear = false;
            }
            TextView texto = (TextView) findViewById(R.id.textoValor);
            if(!operacao) {
                double store = Double.parseDouble(valor1);
                valor1 = String.valueOf(Math.sqrt(store));
                int parse = (int) (double) Double.parseDouble(valor1);
                if(Double.parseDouble(valor1) % 1 == 0){
                    texto.setText(String.valueOf(parse));
                }else{
                    texto.setText(valor1);
                }
            }else {
                double store = Double.parseDouble(valor2);
                valor2 = String.valueOf(Math.sqrt(store));
                int parse = (int) (double) Double.parseDouble(valor2);
                if(Double.parseDouble(valor1) % 1 == 0){
                    texto.setText(String.valueOf(parse));
                }else{
                    texto.setText(valor2);
                }
            }
        });
    }
}
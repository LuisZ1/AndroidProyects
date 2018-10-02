package com.example.lzumarraga.boletin02act02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * El metodo realizará una operacion matematica con dos operandos, dependiendo del radio button
     * */
    public void calcular(View v){
        int operacionARealizar = -1;
        RadioGroup rdGroup = findViewById(R.id.radioGroup);
        TextView resultado = findViewById(R.id.resultado);
        EditText op1 = findViewById(R.id.op1);
        EditText op2 = findViewById(R.id.op2);
        int operador1 = 0;
        int operador2 = 0;

        if(rdGroup.getCheckedRadioButtonId() == R.id.rdBtnSumar){
            operacionARealizar = 0;
        }else{
            if(rdGroup.getCheckedRadioButtonId() == R.id.rdBtnRestar){
                operacionARealizar = 1;
            }else{
                if(rdGroup.getCheckedRadioButtonId() == R.id.rdBtnMultiplicar){
                    operacionARealizar = 2;
                }else{
                    if(rdGroup.getCheckedRadioButtonId() == R.id.rdBtnDividir){
                        operacionARealizar = 3;
                    }else{
                        Toast.makeText(this, "Selecciona una operación", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }

        if(op1.getText().toString().matches("") || op2.getText().toString().matches("")){
            operacionARealizar = -1;
            Toast.makeText(this, "Uno de los operadores está vacío", Toast.LENGTH_SHORT).show();
        }else{
            operador1 = Integer.parseInt(op1.getText().toString());
            operador2 = Integer.parseInt(op2.getText().toString());
        }

        switch (operacionARealizar){
            case 0: //Sumar
                resultado.setText(String.valueOf(operador1 + operador2));
                break;
            case 1: //Restar
                resultado.setText(String.valueOf(operador1 - operador2));
                break;
            case 2: //Multiplicar
                resultado.setText(String.valueOf(operador1 * operador2));
                break;
            case 3: //Dividir
                resultado.setText(String.valueOf(operador1 / operador2));
                break;
        }

    }

    public void calcularV2(View v){
        RadioGroup rdGroup = findViewById(R.id.radioGroup);
        int operacionARealizar = rdGroup.getCheckedRadioButtonId();
        TextView resultado = findViewById(R.id.resultado);
        EditText op1 = findViewById(R.id.op1);
        EditText op2 = findViewById(R.id.op2);
        int operador1 = 0;
        int operador2 = 0;

        if(rdGroup.getCheckedRadioButtonId() == -1){
            Toast.makeText(this, "Selecciona una operación", Toast.LENGTH_SHORT).show();
        }

        if(op1.getText().toString().matches("") || op2.getText().toString().matches("")){
            Toast.makeText(this, "Uno de los operadores está vacío", Toast.LENGTH_SHORT).show();
        }else{
            operador1 = Integer.parseInt(op1.getText().toString());
            operador2 = Integer.parseInt(op2.getText().toString());
        }

        switch (operacionARealizar){
            case R.id.rdBtnSumar: //Sumar
                resultado.setText(String.valueOf(operador1 + operador2));
                break;
            case R.id.rdBtnRestar: //Restar
                resultado.setText(String.valueOf(operador1 - operador2));
                break;
            case R.id.rdBtnMultiplicar: //Multiplicar
                resultado.setText(String.valueOf(operador1 * operador2));
                break;
            case R.id.rdBtnDividir: //Dividir
                resultado.setText(String.valueOf(operador1 / operador2));
                break;
        }

    }

}

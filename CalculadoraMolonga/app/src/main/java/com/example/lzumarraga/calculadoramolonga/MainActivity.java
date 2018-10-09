package com.example.lzumarraga.calculadoramolonga;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView visor = null;
    TextView visorResultado = null;
    int resultadoParcial = 0;
    int resultadoFinal = 0;
    char operacionARealizar = 'v';

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        visor = findViewById(R.id.txtResultado);
        visorResultado = findViewById(R.id.txtResultado2);

        Button btn0 = findViewById(R.id.btn0);
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatenarPulsacionNumero("0");
            }
        });

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatenarPulsacionNumero("1");
            }
        });

        Button btn2 = findViewById(R.id.btn2);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatenarPulsacionNumero("2");
            }
        });

        Button btn3 = findViewById(R.id.btn3);
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatenarPulsacionNumero("3");
            }
        });

        Button btn4 = findViewById(R.id.btn4);
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatenarPulsacionNumero("4");
            }
        });

        Button btn5 = findViewById(R.id.btn5);
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatenarPulsacionNumero("5");
            }
        });

        Button btn6 = findViewById(R.id.btn6);
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatenarPulsacionNumero("6");
            }
        });

        Button btn7 = findViewById(R.id.btn7);
        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatenarPulsacionNumero("7");
            }
        });

        Button btn8 = findViewById(R.id.btn8);
        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatenarPulsacionNumero("8");
            }
        });

        Button btn9 = findViewById(R.id.btn9);
        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatenarPulsacionNumero("9");
            }
        });

        //--------------------------------OPERADORES -----------------------------------

        Button btnMult = findViewById(R.id.btnMult);
        btnMult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatenarPulsacionNumero("x");
                operacionARealizar = 'x';
                resultadoParcial = Integer.parseInt(visor.getText().toString());
            }
        });

        Button btnSum = findViewById(R.id.btnSUM);
        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatenarPulsacionNumero("+");
                operacionARealizar = '+';
                resultadoParcial = Integer.parseInt(visor.getText().toString());
            }
        });

        Button btnRestar = findViewById(R.id.btnRES);
        btnRestar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatenarPulsacionNumero("-");
                operacionARealizar = '-';
                resultadoParcial = Integer.parseInt(visor.getText().toString());
            }
        });

        Button btnDiv = findViewById(R.id.btnDiv2);
        btnDiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                concatenarPulsacionNumero("/");
                operacionARealizar = '/';
                resultadoParcial = Integer.parseInt(visor.getText().toString());
            }
        });

        Button btnResultado = findViewById(R.id.btnRes2);
        btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realizarOperacion(operacionARealizar);
            }
        });



    }


    public String concatenarPulsacionNumero(String i){
        String resultado = "";

        resultado = visor.getText() + i;

        visor.setText(resultado.toString());

        return resultado;
    }

    public void realizarOperacion (char operador){
        //String txtResultado = "";

        switch (operador){
            case '+':
                resultadoFinal = resultadoParcial + Integer.parseInt(visor.getText().toString());
                break;
            case '-':
                resultadoFinal = resultadoParcial - Integer.parseInt(visor.getText().toString());
                break;
            case 'x':
                resultadoFinal = resultadoParcial * Integer.parseInt(visor.getText().toString());
                break;
            case '/':
                resultadoFinal = resultadoParcial / Integer.parseInt(visor.getText().toString());
                break;

        }

        visorResultado.setText(String.valueOf(resultadoFinal));

    }

}

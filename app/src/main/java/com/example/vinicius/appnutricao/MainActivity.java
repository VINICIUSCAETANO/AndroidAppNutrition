package com.example.vinicius.appnutricao;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtNome;
    private EditText edtAltura;
    private RadioButton rbMasc;
    private RadioButton rbFem;
    private Button btCalcular;
    private Button btLimpar;
    private TextView txtResposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNome = (EditText) findViewById(R.id.edtNome);
        edtAltura = (EditText) findViewById(R.id.edtAltura);
        rbMasc = (RadioButton) findViewById(R.id.rbMasc);
        rbFem = (RadioButton) findViewById(R.id.rbFem);
        btCalcular = (Button) findViewById(R.id.btCalcular);
        btLimpar = (Button) findViewById(R.id.btLimpar);
        txtResposta = (TextView) findViewById(R.id.txtResposta);

        btCalcular.setOnClickListener(this);
        btLimpar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.btCalcular) {
            String nome = edtNome.getText().toString();
            String altura = edtAltura.getText().toString();
            Boolean masc = rbMasc.isChecked();
            Boolean fem = rbFem.isChecked();

            if(nome.trim().isEmpty() && altura.trim().isEmpty() && (!masc && !fem)) {
                Toast.makeText(this, "Preencha corretamente os dados", Toast.LENGTH_LONG).show();
                return;
            }

            try {
                double altNum = Double.parseDouble(altura);
                double peso;

                if(masc) {
                    peso = Math.pow(altNum, 2) * 22;
                }
                else {
                    peso = Math.pow(altNum, 2) * 21;
                }

                NumberFormat nf = new DecimalFormat(".000");

                txtResposta.setText(nome + "seu peso ideal é " + nf.format(peso) + " kg");

            } catch(NumberFormatException e) {
                Toast.makeText(this, "Informe corretamente a altura", Toast.LENGTH_LONG).show();
            }
        } else {
            edtNome.setText("");
            edtAltura.setText("");
            rbMasc.setChecked(false);
            rbFem.setChecked(false);
            txtResposta.setText("");
            edtNome.requestFocus();

        }
    }
}

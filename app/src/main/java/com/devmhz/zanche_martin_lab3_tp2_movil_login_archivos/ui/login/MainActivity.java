package com.devmhz.zanche_martin_lab3_tp2_movil_login_archivos.ui.login;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.devmhz.zanche_martin_lab3_tp2_movil_login_archivos.R;

public class MainActivity extends AppCompatActivity {
    private ViewModelMain lv;
    private Button btLogin, btRegistro;
    private EditText etLoginUser, etLoginPass;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelMain.class);
        //new ViewModelProvider(this).get(UnModelo.class);  FORMA Corta de iniializar el viewModel
        inicializarVista();
        lv.getMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(MainActivity.this,s, Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    protected void onStop() {
        super.onStop();

    }


    private void inicializarVista() {
        btLogin=findViewById(R.id.btLogin);
        btRegistro=findViewById(R.id.btRegistro);
        etLoginUser=findViewById(R.id.etUser);
        etLoginPass=findViewById(R.id.etPass);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv.iniciarSesion(etLoginUser.getText().toString(),etLoginPass.getText().toString());
            }
        });
        btRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lv.registrarse();

            }
        });
    }
}
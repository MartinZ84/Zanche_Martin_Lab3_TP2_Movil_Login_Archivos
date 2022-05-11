package com.devmhz.zanche_martin_lab3_tp2_movil_login_archivos.ui.registro;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import com.devmhz.zanche_martin_lab3_tp2_movil_login_archivos.R;
import com.devmhz.zanche_martin_lab3_tp2_movil_login_archivos.model.Usuario;

public class RegistroActivity extends AppCompatActivity {
    private EditText dni,apellido,nombre,email,password;
    private Button guardar;
    private  ViewModelRegistro vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        inicializar();
    }

    public void inicializar(){
        dni= findViewById(R.id.etDniPerfil);

        nombre=findViewById(R.id.etNombrePerfil);
        apellido=findViewById(R.id.etApellidoPerfil);
        email=findViewById(R.id.etEmailPerfil);
        password=findViewById(R.id.etContraseñaPerfil);
        guardar=findViewById(R.id.btGuardarPerfil);
        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(ViewModelRegistro.class);
        vm.getUsuario().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                dni.setText(usuario.getDni() + "");
                nombre.setText(usuario.getNombre());
                apellido.setText(usuario.getApellido());
                email.setText(usuario.getMail());
                password.setText(usuario.getPassword());
            }
        });

        vm.getMensaje().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.registrar(Long.valueOf(dni.getText().toString()), apellido.getText().toString(), nombre.getText().toString(), email.getText().toString(), password.getText().toString());
            }
        });

        vm.mostrar((Usuario) getIntent().getSerializableExtra("usuario"));

    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
        System.exit(0);
    }
}
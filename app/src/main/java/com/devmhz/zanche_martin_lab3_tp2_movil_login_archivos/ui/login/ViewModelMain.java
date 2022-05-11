package com.devmhz.zanche_martin_lab3_tp2_movil_login_archivos.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.devmhz.zanche_martin_lab3_tp2_movil_login_archivos.Request.ApiClient;
import com.devmhz.zanche_martin_lab3_tp2_movil_login_archivos.model.Usuario;
import com.devmhz.zanche_martin_lab3_tp2_movil_login_archivos.ui.registro.RegistroActivity;

public class ViewModelMain extends AndroidViewModel {
    private Context context;
    private MutableLiveData<String> mensaje;

    public ViewModelMain(@NonNull Application application) {
        super(application);
        this.context=application.getApplicationContext();

    }

    public LiveData<String> getMensaje(){
        if(mensaje==null){
            mensaje=new MutableLiveData<>();
        }
        return mensaje;

    }

    public void iniciarSesion(String usuario,String contraseña){

        ApiClient api=ApiClient.getApi();
        Usuario userLog=api.login(context,usuario,contraseña);
        if(userLog!=null){
            //Inicio la Activity del menú
            Intent intent=new Intent(context, RegistroActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("usuario",userLog);
            context.startActivity(intent);


        } else{
            mensaje.setValue("Usuario y/o Contraseña incorrecto!!!");
        }
    }

    public void registrarse(){
        Intent intent=new Intent(context,RegistroActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }


}


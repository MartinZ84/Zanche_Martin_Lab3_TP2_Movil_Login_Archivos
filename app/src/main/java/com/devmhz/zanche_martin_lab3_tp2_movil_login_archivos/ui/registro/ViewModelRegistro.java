package com.devmhz.zanche_martin_lab3_tp2_movil_login_archivos.ui.registro;


import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.devmhz.zanche_martin_lab3_tp2_movil_login_archivos.Request.ApiClient;
import com.devmhz.zanche_martin_lab3_tp2_movil_login_archivos.model.Usuario;

public class ViewModelRegistro extends AndroidViewModel {

    private MutableLiveData<Usuario> usuario;
    private MutableLiveData<String> mensaje;
    private Context context;
    private ApiClient apiClient;


    public ViewModelRegistro(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
        apiClient=new ApiClient();
    }

    public LiveData<Usuario> getUsuario(){
        if(usuario==null){
            usuario=new MutableLiveData<>();
        }
        return usuario;
    }
    public LiveData<String> getMensaje(){
        if(mensaje==null){
            mensaje=new MutableLiveData<>();
        }
        return mensaje;
    }

    public void registrar(Long dni, String apellido, String nombre, String email, String pass){
        Usuario u=new Usuario(dni,nombre,apellido,email,pass);
        apiClient.guardar(context,u);
        mensaje.setValue("El usuarios se genero con exito");
    }

    public void mostrar(Usuario u){
        if(u != null){
            u = apiClient.leer(context);
            usuario.setValue(u);
        }
    }
}

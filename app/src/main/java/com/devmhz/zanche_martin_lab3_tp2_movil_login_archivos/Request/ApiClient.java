package com.devmhz.zanche_martin_lab3_tp2_movil_login_archivos.Request;

import android.content.Context;
import android.content.SharedPreferences;


import com.devmhz.zanche_martin_lab3_tp2_movil_login_archivos.model.Usuario;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class ApiClient {
    private static File archivo;
    private static ApiClient api=null;

    public ApiClient(){

    }    //Método para crear una instancia de ApiClient
    public static ApiClient getApi(){
        if (api==null){
            api=new ApiClient();
        }
        return api;

    }

    private static void conectar(Context context){
        if(archivo == null){
            archivo  = new File(context.getFilesDir(),"objetos.dat");
        }
    }

    public static void guardar(Context context, Usuario usuario){
        conectar(context);
        try {
            //nodo
            FileOutputStream fo = new FileOutputStream(archivo);
            //buffer
            BufferedOutputStream bo = new BufferedOutputStream(fo);
            //Convertir de objeto a byte
            ObjectOutputStream ous = new ObjectOutputStream(bo);

            ous.writeObject(usuario);
            bo.flush();
            fo.close();
        }catch (IOException ex){
            //mandar mensaje de error
        }
    }

    public static Usuario leer(Context context){
        Usuario usuario = null;
        conectar(context);
        try {
            FileInputStream fi=new FileInputStream(archivo);

            BufferedInputStream bi = new BufferedInputStream(fi);

            ObjectInputStream ois = new ObjectInputStream(bi);

            usuario = (Usuario) ois.readObject();

            fi.close();

            return usuario;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException ex){

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public static Usuario login(Context context, String email, String pass){
        conectar(context);
        Usuario usuario = leer(context);

        if(usuario.getMail().equals(email) && usuario.getPassword().equals(pass)){
            return usuario;
        }

        return null;
    }
}

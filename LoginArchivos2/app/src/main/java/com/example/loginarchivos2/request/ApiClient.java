package com.example.loginarchivos2.request;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.loginarchivos2.model.Usuario;

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

    public static void guardar(Context context, Usuario user){
        File carpeta=context.getFilesDir();
        File archivo=new File(carpeta,"usuario.dat");

        try {
            FileOutputStream fos=new FileOutputStream(archivo);
            BufferedOutputStream bos=new BufferedOutputStream(fos);
            ObjectOutputStream ous=new ObjectOutputStream(bos);
            ous.writeObject(user);
            bos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(context,"Error al Guardar",Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context,"Error De Entrada Salida:",Toast.LENGTH_LONG).show();
        }
    }
    public static Usuario leer(Context context) {
        File carpeta = context.getFilesDir();
        File archivo = new File(carpeta, "usuario.dat");
        Usuario usuario = null;
        try {
            FileInputStream fis = new FileInputStream(archivo);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            usuario = (Usuario) ois.readObject();
            fis.close();
        } catch (FileNotFoundException exception) {
            Toast.makeText(context, "Error al Leer", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context, "Error Entrada Salida", Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context, "No es un producto", Toast.LENGTH_LONG).show();

        }
        return usuario;
    }

    public static Usuario login(Context context,String mail, String password){
        File carpeta = context.getFilesDir();
        File archivo = new File(carpeta, "usuario.dat");
        Usuario usuario = null;
        Usuario user=null;
        try {
            FileInputStream fis = new FileInputStream(archivo);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ObjectInputStream ois = new ObjectInputStream(bis);
            user = (Usuario) ois.readObject();
            fis.close();
        } catch (FileNotFoundException exception) {

            Toast.makeText(context, "Error al Leer", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(context, "Error Entrada Salida", Toast.LENGTH_LONG).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(context, "No es un producto", Toast.LENGTH_LONG).show();
        }
        if (user!=null){
            if(mail.equals(user.getMail())&&password.equals(user.getPassword())){
                usuario=new Usuario(user.getDni(),user.getApellido(), user.getNombre(), user.getMail(),user.getPassword());
            }
        }

        return usuario;
    }
}

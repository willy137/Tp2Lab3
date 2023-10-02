package com.example.loginarchivos2.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.loginarchivos2.request.ApiClient;
import com.example.loginarchivos2.ui.registro.DatosActivity;
import com.example.loginarchivos2.model.Usuario;


public class ActivityMainViewModel extends AndroidViewModel {
    private Context context;
    public ActivityMainViewModel(@NonNull Application application) {
        super(application);
        context=application;
    }
    public void inicio(String usuario,String password){
        Usuario user=ApiClient.login(context,usuario,password);
        if (user==null){
            Toast.makeText(context,"Usuario o Contraseña incorrectos",Toast.LENGTH_LONG).show();
        }else {
            Intent intent=new Intent(context, DatosActivity.class);
            intent.putExtra("login",true);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
    public void registrarse(){
        Intent intent=new Intent(context, DatosActivity.class);
        intent.putExtra("login",false);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}

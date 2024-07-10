package br.anderson.criptografia;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String Encriptar(String claro, int senha){
        String texto = "";

        for(int i=0; i<claro.length(); i++){
            texto += (char)((claro.charAt(i)+senha-'a')%26+'a');
            System.out.println(texto);
        }
        return texto;
    }

    public String Decriptar(String claro, int senha){
        String texto = "";

        for(int i=0; i<claro.length(); i++){
            texto += (char)((claro.charAt(i)-senha-97+26)%26+97);
            System.out.println(texto);
        }
        return texto;
    }


    public void Salvar(View v){
        int senha = ((SeekBar)findViewById(R.id.senha)).getProgress();
        String mensagem = ((EditText)findViewById(R.id.mensagem)).getText().toString();


        ObjectMapper json = new ObjectMapper();

        Texto cripto = new Texto(Encriptar(mensagem, senha));
        File internalStorageDir = getFilesDir();
        File arquivo = new File(internalStorageDir, "login.json");
        try {
            json.writeValue(arquivo, cripto);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "ready!", Toast.LENGTH_LONG).show();
    }
}

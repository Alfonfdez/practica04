package com.afr.accesocamara;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    //I - Declarar variables
    private Button btnAbrirCamara;
    private Button btnGuardarFoto;
    private ImageView imageView;


    private Bitmap imagenActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAbrirCamara = (Button) findViewById(R.id.idButtonCamara);
        btnGuardarFoto = (Button) findViewById(R.id.idButtonGuardar);
        imageView = (ImageView) findViewById(R.id.idImageView);

        btnAbrirCamara.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                abrirCamara();
            }
        });

        btnGuardarFoto.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                guardarFoto();
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == 1 && resultCode == RESULT_OK){

                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");

                imageView.setImageBitmap(imageBitmap);

                imagenActual = imageBitmap;
        }
    }

    //Métodos privados
    private void abrirCamara(){

        //'Intent' ya definido en el sistema
        Intent hacerFotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //Comprobamos que la aplicación de la cámara se abre con normalidad
        if(hacerFotoIntent.resolveActivity(getPackageManager()) != null){

            //Abrir la cámara
            startActivityForResult(hacerFotoIntent, 1);
        }
    }


    private File createImageFile() throws IOException {

        String strName = "name" + ((int)(Math.random() * 10000));

        //El constructor de 'File' necesita saber:
        // 1- El directorio (en este caso el directorio de nuestra app)
        // 2- Necesita conocer el nombre del archivo
        File file = new File(this.getFilesDir(), strName);

        return file;
    }

    private void guardarFoto(){

        try{
            File file = createImageFile();
            Log.d("DEBUG","file: "+ file.getAbsolutePath());

            //Un 'FileOutputStream2 ES-UN 'OutputStream especializado en archivos...
            OutputStream out = new FileOutputStream(file);

            //Enviar la imagen actual a través de 'Stream'
            imagenActual.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();


        }catch (Exception e){
            e.printStackTrace();
        }

    }


}

package com.example.implicitintentdemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.ViewManager;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
 Button btnBrowser , btnCamera , btnMap,btnPhone;
 ImageView imageView1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView1 = findViewById(R.id.imageView1);
        btnBrowser = findViewById(R.id.btnBrowser);
        btnCamera = findViewById(R.id.btnCamera);
        btnMap = findViewById(R.id.btnMap);
        btnPhone = findViewById(R.id.btnPhone);

        //browser button code
        class BrowserButtonListener implements View.OnClickListener
        {
            @Override
            public void onClick(View view) {
                Intent inBrowser = new Intent();
                inBrowser.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("http://www.microsoft.com/");
                inBrowser.setData(uri);
                startActivity(inBrowser);
            }
        }
        btnBrowser.setOnClickListener(new BrowserButtonListener());

        //Dialer button code
        class DialerButtonListener implements View.OnClickListener
        {
            @Override
            public void onClick(View view) {
                Intent inDail = new Intent();
                inDail.setAction(Intent.ACTION_DIAL);
                String str = "797452";
                Uri uri = Uri.parse("tel:"+str);
                inDail.setData(uri);
                startActivity(inDail);
            }
        }
        btnPhone.setOnClickListener(new DialerButtonListener());

        //map button code
        class MapButtonListener implements View.OnClickListener
        {
            @Override
            public void onClick(View view) {
                Intent inMap = new Intent();
                inMap.setAction(Intent.ACTION_VIEW);
                Uri uri = Uri.parse("geo:28.616925 , 77.207873");
                inMap.setData(uri);
                startActivity(inMap);
            }
        }
        btnMap.setOnClickListener(new MapButtonListener());

        //camera button code
        class CameraButtonListener implements View.OnClickListener
        {
            @Override
            public void onClick(View view) {
                Intent inCamera = new Intent();
                inCamera.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivity(inCamera)
                startActivityForResult(inCamera , 1001);

            }
        }
        btnCamera.setOnClickListener( new CameraButtonListener());

        //eof onCreate
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //display recieved picture data on imageView
        Log.e("Captured image path:" , data.toURI());
        if(requestCode==1001){
           Bundle dataBundle = data.getExtras();
           // Bundle holds all data send by second activity/camera activity
            Bitmap bmp = (Bitmap) dataBundle.get("data");
            imageView1.setImageBitmap(bmp);
    }
}
}
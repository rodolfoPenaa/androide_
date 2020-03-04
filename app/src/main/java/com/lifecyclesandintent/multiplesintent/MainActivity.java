package com.lifecyclesandintent.multiplesintent;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView logo = findViewById(R.id.logoDlatam);
        Picasso.get().load("https://desafiolatam.com/assets/home/logo-academia-bla-790873cdf66b0e681dfbe640ace8a602f5330bec301c409744c358330e823ae3.png").into(logo);
        Button buttonuploadimage = findViewById(R.id.button2);
        Button buttonchangeactivity = findViewById(R.id.button);

        buttonuploadimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null){
                startActivityForResult(intent, 1);
                onActivityResult(1, 1, getIntent());
                }
            }
        });

        buttonchangeactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView pictureView = findViewById(R.id.imageupload);
                if (pictureView.getDrawable() != null){
                    Intent intent = new Intent(MainActivity.this, ResultActivity.class);
                    intent.putExtra("BOOLEAN", true);
                    intent.putExtra("String", "http://www.desafiolatam.com/");
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, getString(R.string.noimageupload), Toast.LENGTH_SHORT).show();
            }
        }});
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        final ImageView pictureView = findViewById(R.id.imageupload);
        final int REQUEST_IMAGE_CAPTURE = 1;
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap picture = (Bitmap) extras.get("data");
            pictureView.setImageBitmap(picture);
        }

    }
}


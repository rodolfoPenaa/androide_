package com.lifecyclesandintent.multiplesintent;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.Objects;

public class ResultActivity extends AppCompatActivity {
    static final int REQUEST_SEND = 2;
    static final int REQUEST_WEB = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ImageView logoLatam = findViewById(R.id.Logolatam2);
        Picasso.get().load("https://desafiolatam.com/assets/home/logo-academia-bla-790873cdf66b0e681dfbe640ace8a602f5330bec301c409744c358330e823ae3.png").into(logoLatam);
        Button buttonGotoWebpage = findViewById(R.id.gotowebpage);
        Button buttonShare = findViewById(R.id.ShareUrl);

        Bundle dataMain = getIntent().getExtras();
        final String url = Objects.requireNonNull(dataMain).getString("String");
        final String check = Objects.requireNonNull(dataMain).toString();

        Button gotoweb = findViewById(R.id.gotowebpage);
        gotoweb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                setResult(Activity.RESULT_OK);
                startActivityForResult(intent, REQUEST_WEB);
            }
        });
        Button share = findViewById(R.id.ShareUrl);
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, check);
                intent.setType("text/plain");
                setResult(Activity.RESULT_OK);
                startActivityForResult(intent, REQUEST_SEND);
            }
        });

    }

    @Override
    protected void onActivityResult( int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SEND && resultCode == Activity.RESULT_OK) {
            Toast.makeText(ResultActivity.this, getString(R.string.sharing), Toast.LENGTH_SHORT).show();
        } else if (requestCode == REQUEST_WEB && resultCode == Activity.RESULT_OK) {
            Toast.makeText(ResultActivity.this, getString(R.string.redirecting), Toast.LENGTH_SHORT).show();

        }
    }

}


package com.example.versatileapp;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.PermissionRequest;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Map extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private WebView Map;
    private EditText Src,Dest;
    private Button Search;
    private String Mode;
    String[] modes = {"driving","walking"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        Src = findViewById(R.id.source);
        Dest = findViewById(R.id.destination);
        Search = findViewById(R.id.GetDirection);

        Map = findViewById(R.id.map);
        Map.setWebViewClient(new WebViewClient());
        WebSettings webSettings = Map.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);

        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, modes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String src = Src.getText().toString().trim();
                String dest = Dest.getText().toString().trim();
                String mode = Mode;
                showMap(src,dest,mode);
            }
        });

        showMap("Mist","Mist","driving");
    }

    private void showMap(String src, String dest, String mode) {
        Map = findViewById(R.id.map);
        WebSettings webSettings = Map.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setAppCacheEnabled(true);
        Map.loadUrl("https://directionsdebug.firebaseapp.com/embed.html?origin=" + src + "&destination=" + dest + "&mode=" + mode);

        Map.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
        Map.setWebChromeClient(new WebChromeClient(){
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onPermissionRequest(PermissionRequest request) {
                request.grant(request.getResources());
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Mode = modes[i];
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

package com.example.versatileapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

class BackgroundTask extends AsyncTask<String,Void,String> {

    AlertDialog dialog;
    Context context;
    public BackgroundTask(Context context)
    {
        this.context = context;
    }

//    @Override
//    protected void onPreExecute() {
////        dialog = new AlertDialog.Builder(context).create();
////        dialog.setTitle("Login Status");
//    }
    @Override
    protected void onPostExecute(String s) {

            Toast toast = Toast.makeText(context.getApplicationContext(),"Successfully Saved on RDS",Toast.LENGTH_SHORT);
            toast.show();
            Intent intent_name = new Intent();
            intent_name.setClass(context.getApplicationContext(),MainActivity.class);
            context.startActivity(intent_name);


    }


    @Override
    protected String doInBackground(String... params) {
        String result = "";
        String x = params[0];
        String y = params[1];
        String z = params[2];


        String connstr = "http://ec2-34-207-172-7.compute-1.amazonaws.com/SaveData.php";
        try {
            URL url = new URL(connstr);
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.setRequestMethod("POST");
            http.setDoInput(true);
            http.setDoOutput(true);

            OutputStream ops = http.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(ops,"UTF-8"));
            String data = URLEncoder.encode("x","UTF-8")+"="+URLEncoder.encode(x,"UTF-8")+"&&"
                    +URLEncoder.encode("y","UTF-8")+"="+URLEncoder.encode(y,"UTF-8")+"&&"
                    +URLEncoder.encode("z","UTF-8")+"="+URLEncoder.encode(z,"UTF-8");
            writer.write(data);
            writer.flush();
            writer.close();
            ops.close();

            InputStream ips = http.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(ips,"UTF-8"));
            String line ="";
            while ((line = reader.readLine()) != null)
            {
                result += line + "\n";
            }
            reader.close();
            ips.close();
            http.disconnect();
            return result;

        } catch (MalformedURLException e) {
            result = e.getMessage();
        } catch (IOException e) {
            result = e.getMessage();
        }


        return result;
    }
}


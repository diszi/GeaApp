package hu.ace.geaapp.data.network;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import hu.ace.geaapp.singleton.HolderSingleton;
import hu.ace.geaapp.utils.UIConstans;

public class HttpRequestAsyncTask extends AsyncTask<HttpCall, String, String> {


    private static final String UTF_8 = "UTF-8";

    private String message;
    private String response;



    @Override
    protected String doInBackground(HttpCall... httpCalls) {
        HttpURLConnection urlConnection = null;
        HttpCall httpCall = httpCalls[0];

        try {
            urlConnection = getHttpConnection(httpCall);
            urlConnection.connect();

            int statusCode = urlConnection.getResponseCode();
            Log.i("----------> ","connection: "+statusCode+"; "+urlConnection.getResponseMessage());

            // Log.i("--------->","  errorStream="+urlConnection.getErrorStream()+"; inputStream="+urlConnection.getInputStream());

            BufferedReader br = null;
            InputStreamReader streamReader_ = null;
            if (100 <= urlConnection.getResponseCode() && urlConnection.getResponseCode() <= 399) {
                //br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                streamReader_ = new InputStreamReader(urlConnection.getInputStream());
//                System.out.println(" STREAM READER [OK] = "+streamReader_);
            } else {
                streamReader_ = new InputStreamReader(urlConnection.getErrorStream());
//                System.out.println(" STREAM READER [ERROR] = "+streamReader_);
            }

            this.response = convertInputStreamToString(streamReader_);
            if (statusCode != 200){
                System.out.println(" connection response = "+this.response);//connection response = Error 400: BMXAA3851E - Connecting to the server has been temporarily disabled.
            }
            /*if (statusCode == 400){
                Toast.makeText(this,"Connection to the server has been temporarily disablet",Toast.LENGTH_SHORT).show();
            }*/
            /*if (statusCode / 100 != 2) {
                this.message = urlConnection.getResponseMessage();
                this.response = message;

            }*//*else{
                this.message = convertInputStreamToString(new InputStreamReader(urlConnection.getInputStream()));
                this.response = message;
            }*/
            // System.out.println(" ERRor stream = "+urlConnection.getErrorStream());
            /*if (urlConnection.getErrorStream() != null){
                InputStreamReader errorStreamReader = new
                        InputStreamReader(urlConnection.getErrorStream());
                String errorStringMSG =  convertInputStreamToString(errorStreamReader);

                System.out.println("errorString = "+errorStringMSG);//The status of inventory usage IU-104492 cannot be changed to COMPLETE.	BMXAA7691E - The status of inventory usage IU-104492 cannot be changed to COMPLETE.	BMXAA4563E - 1092646 Work Order is not a valid work order that is approved and not canceled.
                //System.out.println(" -----> urlConnection = "+urlConnection+" \n inputStream = "+urlConnection.getInputStream()+";errorStringMSG = "+errorStringMSG);
                //this.response = errorStringMSG;
            }*///else{


            /*InputStreamReader streamReader = new
                    InputStreamReader(urlConnection.getInputStream());
            this.response = convertInputStreamToString(streamReader);*/


            // System.out.println("doInBackground return value = "+this.response);


            return response;

        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;

    }

    public static String convertInputStreamToString(InputStreamReader inputStreamReader) throws IOException {
        if (inputStreamReader == null) {
            return "";
        }

        BufferedReader reader = new BufferedReader(inputStreamReader);
        StringBuilder stringBuilder = new StringBuilder();

        String inputLine;
        String result;

        while ((inputLine = reader.readLine()) != null) {
            stringBuilder.append(inputLine);
        }

        reader.close();
        inputStreamReader.close();
        return stringBuilder.toString();
    }




    private HttpURLConnection getHttpConnection(HttpCall httpCall) throws IOException {

        URL url = new URL(httpCall.getUrl());

        System.out.println( " URL = "+url+"; method = "+httpCall.getMethod()+"; use User AUTH = "+httpCall.isUserAuth());

        HttpURLConnection connection = (HttpURLConnection)
                url.openConnection();

        connection.setRequestMethod(httpCall.getMethod().getValue());

        connection.setRequestProperty("Accept", "application/xml");
        connection.setRequestProperty("Content-type", "application/xml");
        if (httpCall.isUserAuth()){
            connection.setRequestProperty(UIConstans.HTTP_REQUEST_PROP_AUTH_KEY, HolderSingleton.getInstance().getAuthBase64());
        }else{
            connection.setRequestProperty(UIConstans.HTTP_REQUEST_PROP_AUTH_KEY, UIConstans.HTTP_REQUEST_PROP_AUTH_VALUE_MAXADMIN);
        }


        connection.setReadTimeout(30000);
        connection.setConnectTimeout(30000);

        if (httpCall.getMethod()== HttpCall.RequestMethod.POST){
            connection.setDoInput(true);
            connection.setDoOutput(true);


            OutputStream os = connection.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, UTF_8));
            writer.flush();
            writer.close();
            os.close();
        }

        return connection;
    }



    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        onResponse(s);


    }

    public void onResponse(String response){
        // System.out.println(" onResponse = "+response); //onResponse = <?xml version="1.0" encoding="UTF-8"?><QueryACE_INVUSE_TRResponse
    }


}

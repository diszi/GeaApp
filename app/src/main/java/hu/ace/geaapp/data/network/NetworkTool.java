package hu.ace.geaapp.data.network;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import hu.ace.geaapp.utils.UIConstans;

public class NetworkTool {

    private static int CONNECTION_TIME_OUT = 5000;
    private static int READ_TIME_OUT = 5000;



    public static HttpURLConnection createConnection(String url) throws IOException {
        return createConnection(url,null,null,false,true);
    }

    public static HttpURLConnection createSOAPConnection(String url, String soapAction,String soapPayload) throws IOException {
      //  System.out.println("\n url -> soap payload =>  \n"+  soapPayload);
        return createConnection(url, soapAction,soapPayload, true,true);
    }




    private static synchronized HttpURLConnection createConnection(String url, String SOAP_ACTION, String SOAP_PAYLOAD, boolean isSoap, boolean isPost) throws IOException {

        System.out.println(" URL = "+url+"\nSOAPACTION="+SOAP_ACTION+"\nisSoap="+isSoap+"\nisPost="+isPost);

        URL serviceUrl = new URL(url);

        HttpURLConnection connection = (HttpURLConnection) serviceUrl.openConnection();
        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setRequestMethod(isPost?"POST":"GET");

        connection.setDoInput(true);
        connection.setConnectTimeout(CONNECTION_TIME_OUT);
        connection.setReadTimeout(READ_TIME_OUT);

        if (isSoap){

//            connection.setRequestProperty("Content-Type", "text/xml");
//            connection.setRequestProperty("charset", "UTF-8");
//            connection.setRequestProperty("SOAPAction", "urn:processDocument");
//            connection.setRequestProperty("MAXAUTH", "bWF4YWRtaW46SGUxMW9vNzAu");
            connection.setRequestProperty(UIConstans.HTTP_REQUEST_PROP_CONTENT_KEY, UIConstans.HTTP_REQUEST_PROP_CONTENT_TEXT_VALUE);
            connection.setRequestProperty(UIConstans.HTTP_REQUEST_PROP_CHARSET_KEY, UIConstans.HTTP_REQUEST_PROP_CHARSET_VALUE);
            connection.setRequestProperty(UIConstans.HTTP_REQUEST_PROP_ACTION_KEY, UIConstans.HTTP_REQUEST_PROP_ACTION_VALUE);
            connection.setRequestProperty(UIConstans.HTTP_REQUEST_PROP_AUTH_KEY, "bWF4YWRtaW46SGUxMW9vNzAu");
            OutputStream outputStream = connection.getOutputStream();
            outputStream.write(SOAP_PAYLOAD.getBytes());


        } else {
            connection.setRequestProperty(UIConstans.HTTP_REQUEST_PROP_CONTENT_KEY, UIConstans.HTTP_REQUEST_PROP_CONTENT_APP_ENCODED_VALUE);

        }
        return connection;

    }


}

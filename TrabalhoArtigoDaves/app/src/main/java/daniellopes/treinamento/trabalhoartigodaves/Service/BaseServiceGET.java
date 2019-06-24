package daniellopes.treinamento.trabalhoartigodaves.Service;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class BaseServiceGET extends AsyncTask<String, Void, String> {

    private final String baseUrl = "https://service.davesmartins.com.br/api/";

    //string 0 - token, 1 - rota, 2 - valor
    @Override
    protected String doInBackground(String... strings) {

        try {
            String token = strings[0];
            String route = strings[1];

            URL url = new URL(baseUrl + route);

            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(95 * 1000);
            urlConnection.setConnectTimeout(95 * 1000);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("code", token);
            urlConnection.setRequestProperty("X-Environment", "android");
            urlConnection.connect();

            String finalJson = "";
            if (urlConnection.getResponseCode() == 200) {
                InputStream responseBody = urlConnection.getInputStream();
                InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
                BufferedReader reader = new BufferedReader(responseBodyReader);
                StringBuffer buffer = new StringBuffer();
                String line = "";

                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }
                finalJson = buffer.toString();
            } else {
                finalJson = urlConnection.getResponseCode() + " " +
                        urlConnection.getResponseMessage();
            }

            return finalJson;

        } catch (MalformedURLException e) {
            return "Erro: " + e.getMessage();
        } catch (IOException e) {
            return "Erro: " + e.getMessage();
        }
    }
}

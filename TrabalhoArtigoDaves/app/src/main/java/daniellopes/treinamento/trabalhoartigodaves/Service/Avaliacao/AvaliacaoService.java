package daniellopes.treinamento.trabalhoartigodaves.Service.Avaliacao;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class AvaliacaoService extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... param) {
        URL url;
        int idArtigo = Integer.parseInt(param[1]);
        String avaliacao = param[2];
        try {
            url = new URL("https://service.davesmartins.com.br/api/artigos/"+idArtigo+"/avaliacoes");
            //url = new URL("http://localhost:8080/api/usuarios/login");

            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setReadTimeout(95 * 1000);
            urlConnection.setConnectTimeout(95 * 1000);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("X-Environment", "android");
            urlConnection.setRequestProperty("code", param[0]);

            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream().write(avaliacao.getBytes());
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
                finalJson = urlConnection.getResponseCode() + "";
            }

            return finalJson;

        } catch (MalformedURLException e) {
            return "Erro1: " + e.getMessage();
        } catch (IOException e) {
            return "Erro2: " + e.getMessage();
        }
        catch (Exception ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }
}

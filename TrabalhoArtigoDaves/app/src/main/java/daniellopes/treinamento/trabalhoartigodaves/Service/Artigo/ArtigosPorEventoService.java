package daniellopes.treinamento.trabalhoartigodaves.Service.Artigo;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import daniellopes.treinamento.trabalhoartigodaves.Model.Artigo;

public class ArtigosPorEventoService extends AsyncTask<String, Void, List<Artigo>> {
    @Override
    protected List<Artigo> doInBackground(String... param) {

        int idEvento = Integer.parseInt(param[1]);
        String queryUrl = "https://service.davesmartins.com.br/api/artigos/evento/" + idEvento;

        try {
            URL url = new URL(queryUrl);

            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setReadTimeout(95 * 1000);
            urlConnection.setConnectTimeout(95 * 1000);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("code", param[0]);
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

            Type listaType = new TypeToken<List<Artigo>>() {
            }.getType();
            Gson gson = new Gson();

            List<Artigo>result =  gson.fromJson(finalJson, listaType);

            return result;

        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
            return null;

        } catch (IOException e) {
            System.out.println(e.getMessage());
            return null;
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}

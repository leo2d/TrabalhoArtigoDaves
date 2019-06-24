package daniellopes.treinamento.trabalhoartigodaves.Service.Usuario;

import android.os.AsyncTask;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import daniellopes.treinamento.trabalhoartigodaves.Model.Usuario;

public class CadastroUsuarioService extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... param) {
        URL url = null;
        try {
            url = new URL("https://service.davesmartins.com.br/api/usuarios");


            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setReadTimeout(95 * 1000);
            urlConnection.setConnectTimeout(95 * 1000);
            urlConnection.setDoInput(true);
            urlConnection.setRequestProperty("Accept", "application/json");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            urlConnection.setRequestProperty("X-Environment", "android");
            Usuario ul = new Usuario(param[0], 1, param[1], param[2], param[3], param[4]);
            String user = new Gson().toJson(ul);

            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream().write(user.getBytes());
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
    }
}

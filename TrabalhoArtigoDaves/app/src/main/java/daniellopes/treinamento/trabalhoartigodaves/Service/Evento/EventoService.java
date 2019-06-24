package daniellopes.treinamento.trabalhoartigodaves.Service.Evento;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import daniellopes.treinamento.trabalhoartigodaves.Model.Evento;
import daniellopes.treinamento.trabalhoartigodaves.Service.BaseServiceGET;
import daniellopes.treinamento.trabalhoartigodaves.Util.TokenUtil;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class EventoService /*{ //extends BaseServiceGET {

    private Gson json = new Gson();
    private final String route = "eventos";

    public List<Evento> BuscarTodosEventos() {

        try {
            List<Evento> eventos = null;

            // AsyncTask result = super.execute(TokenUtil.getToken(), route);
            String responseJson = new BaseServiceGET()
                    .execute(TokenUtil.getToken(), route)
                    .get();

            if (null != responseJson && !responseJson.isEmpty()) {
                if (responseJson.toLowerCase().contains("erro")) {
                    return null;
                }

                Type type = new TypeToken<List<Evento>>() {
                }.getType();
                eventos = json.fromJson(responseJson, type);
            }

            return eventos;

        } catch (Exception ex) {
            return null;

        }
    }

    public List<Evento> BuscarEventosPorSituacao(String situacao) {
        try {
            List<Evento> eventos = null;

            String newRoute = route + "/status/" + situacao;
            String responseJson = new BaseServiceGET()
                    .execute(TokenUtil.getToken(), newRoute)
                    .get();

            if (null != responseJson && !responseJson.isEmpty()) {
                if (responseJson.toLowerCase().contains("erro")) {
                    return null;
                }

                Type type = new TypeToken<List<Evento>>() {
                }.getType();
                eventos = json.fromJson(responseJson, type);
            }

            return eventos;

        } catch (Exception ex) {
            return null;

        }
    }

    public Evento BuscarEventoPorId(int id) {
        try {
            Evento evento = null;

            String newRoute = route + "/" + id;
            String responseJson = new BaseServiceGET()
                    .execute(TokenUtil.getToken(), newRoute)
                    .get();

            if (null != responseJson && !responseJson.isEmpty()) {
                if (responseJson.toLowerCase().contains("erro")) {
                    return null;
                }

                Type type = new TypeToken<Evento>() {
                }.getType();
                evento = json.fromJson(responseJson, type);
            }

            return evento;

        } catch (Exception ex) {
            return null;

        }
    }

*/
    extends AsyncTask<String,Void,String> {
    @Override
    protected String doInBackground(String... param) {

        URL url;
        try {
            url = new URL("https://service.davesmartins.com.br/api/eventos");

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
                String line ="";

                while ((line=reader.readLine())!=null){
                    buffer.append(line);
                }
                finalJson = buffer.toString();
            } else {
                finalJson = urlConnection.getResponseCode()+" "+
                        urlConnection.getResponseMessage();
            }

            return finalJson;

        } catch (MalformedURLException e) {
            return "Erro1: "+e.getMessage();
        } catch (IOException e) {
            return "Erro2: "+e.getMessage();
        }


    }
}

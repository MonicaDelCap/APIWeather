package org.example;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.text.Document;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class PokemonApiExample {
    public static void main(String[] args) {
        try {
            // Crear un cliente HTTP
            HttpClient httpClient = HttpClient.newHttpClient();

            // URL de la API de Pokémon (por ejemplo, obteniendo información sobre el Pokémon con ID 1)
            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=Asturias&appid=c37f27d3b5efe8d856bf0e9fa36fc1ae";
            URI uri = new URI(apiUrl);

            // Crear una solicitud GET
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .GET()
                    .build();

            // Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            JSONObject json = new JSONObject(response.body());

            //Clima
            JSONArray weather = json.getJSONArray("weather");
            JSONObject valorClima = weather.getJSONObject(0);
            String clima = valorClima.getString("main");

            //Temperatura tranformada a celsius
            JSONObject temp= json.getJSONObject("main");
            BigDecimal temperatura = temp.getBigDecimal("temp");
            temperatura = temperatura.subtract(BigDecimal.valueOf(273.15));

            BigDecimal temperaturaMaxima = temp.getBigDecimal("temp_max");
            temperaturaMaxima = temperaturaMaxima.subtract(BigDecimal.valueOf(273.15));

            BigDecimal temperaturaMinima = temp.getBigDecimal("temp_min");
            temperaturaMinima = temperaturaMinima.subtract(BigDecimal.valueOf(273.15));



            //Viento tranformado a km/h
            JSONObject viento= json.getJSONObject("wind");
            double wind = viento.getInt("speed");
            wind = wind*3.6;

            JSONObject valorLLuvia;
            if ((valorLLuvia=json.getJSONObject("rain"))!=null) {

                int lluvia = valorLLuvia.getInt("h1");
                System.out.println(lluvia);
            }

            System.out.println(wind+ "km/h");
            System.out.println(temperatura+"ºC");
            System.out.println(temperaturaMinima+"ºC");
            System.out.println(clima);
            System.out.println(temperaturaMaxima+"ºC");



            // Imprimir la respuesta
            System.out.println("Código de estado: " + response.statusCode());
            System.out.println("Respuesta de la API: " + response.body());

        }catch (JSONException e){

            int lluvia = 0;

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
}
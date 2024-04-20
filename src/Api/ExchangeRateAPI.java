package Api;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;

public class ExchangeRateAPI {
    private static final String API_KEY = "675e5ae913f8cbdf3f97ec5d";

    public void convertCurrency(String fromCurrency, String toCurrency, int amount) {
        String apiUrl = String.format("https://v6.exchangerate-api.com/v6/%s/pair/%s/%s/%s", API_KEY, fromCurrency, toCurrency, amount);

        try {
            String jsonResponse = sendGET(apiUrl);
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            if (jsonObject.has("conversion_result")) {
                int convertedAmount = jsonObject.get("conversion_result").getAsInt();
                DecimalFormat decimalFormat = new DecimalFormat("#,###");
                String formattedAmount = decimalFormat.format(convertedAmount);
                System.out.println(amount + " " +fromCurrency +" convertidos a " + toCurrency + " son: "+ formattedAmount + " " + toCurrency );
            } else {
                System.out.println("Error occurred: " + jsonObject.get("error"));
            }
        } catch (IOException e) {
            System.out.println("Ocurrio un error");
        }
    }

    public void showList(){

        String apiUrl = String.format("https://v6.exchangerate-api.com/v6/%s/codes", API_KEY);

        try {

            String jsonResponse = sendGET(apiUrl);
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            // Acceder a la propiedad "supported_codes" del JSON
            JsonArray supportedCodesArray = jsonObject.getAsJsonArray("supported_codes");

            // Iterar sobre los elementos del array y mostrarlos en un System.out
            System.out.println("Códigos de moneda soportados:");
            for (int i = 0; i < supportedCodesArray.size(); i++) {
                JsonArray currencyCode = supportedCodesArray.get(i).getAsJsonArray();
                String currencyCodeValue = currencyCode.get(0).getAsString();
                String currencyName = currencyCode.get(1).getAsString();
                System.out.println(currencyCodeValue + " - " + currencyName);
            }

        } catch (IOException e) {
            System.out.println("Ocurrio un error");
        }

    }

    private String sendGET(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            throw new IOException("GET request failed with HTTP error code: " + responseCode);
        }
    }



}


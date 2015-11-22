package com.zaharovs.weatherapp.Helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class WHttp {

    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/forecast?q=Cherkasy&units=metric&appid=8a3ea4b3ab2bc27e4a85f3f1020301a6";

    public static String getWeatherDate() {
        StringBuffer stringBuffer = new StringBuffer("");

        try {
            URL baseUrl = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) baseUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.connect();

            InputStream inputStream = connection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String getDataString = "";

            while ((getDataString = bufferedReader.readLine()) != null) {
                stringBuffer.append(getDataString);
            }

            inputStream.close();
            connection.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuffer.toString();
    }
}

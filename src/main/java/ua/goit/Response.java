package ua.goit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

public class Response {
    public String getResponse(HttpURLConnection connection) {
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(connection.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                result.append(line);
            }

            try {
                bufferedReader.close();
            } catch (
                    IOException e) {
                e.printStackTrace();
            }

            try {
                inputStreamReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

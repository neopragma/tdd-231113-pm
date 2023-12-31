package com.neopragma.mocks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Bored {

    public String whatShouldIDo() throws IOException {
        return whatShouldIDo(getBufferedReader());
    }
    public String whatShouldIDo(BufferedReader responseData)
            throws IOException, MalformedURLException {
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = responseData.readLine()) != null) {
            response.append(inputLine);
        }
        responseData.close();
        return response.toString();
    }

    private BufferedReader getBufferedReader() throws IOException {
        URL url = new URL("https://www.boredapi.com/api/activity");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        return in;
    }

}

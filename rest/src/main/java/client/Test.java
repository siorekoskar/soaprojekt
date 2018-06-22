package client;

import com.google.gson.GsonBuilder;
import entity.ElementDto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Test {
    public static void main(String[] args) {
        try {
//            getAllCategories();
            postElement();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void getAllCategories() throws Exception {
        URL url = new URL("http://localhost:8080/rest/rest/catalog?categoryId=36");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setRequestProperty("Accept", "application/json");
        String encoded = Base64.getEncoder().encodeToString(("admin" + ":" + "admin").getBytes(StandardCharsets.UTF_8));
        httpURLConnection.setRequestProperty("Authorization", "Basic " + encoded);

        if (httpURLConnection.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : " + httpURLConnection.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader((httpURLConnection.getInputStream())));
        String output;
        System.out.println("All categories:");
        while ((output = br.readLine()) != null) {
            System.out.println(output);
        }

        httpURLConnection.disconnect();
    }

    private static void postElement() throws Exception {
        URL url = new URL("http://localhost:8080/rest/rest/catalog/36");
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setRequestProperty("Content-Type", "application/json");
        String encoded = Base64.getEncoder().encodeToString(("admin" + ":" + "admin").getBytes(StandardCharsets.UTF_8));
        httpURLConnection.setRequestProperty("Authorization", "Basic " + encoded);

        ElementDto elementDto = new ElementDto();
        elementDto.setName("elf");
        elementDto.setPower(5);
        elementDto.setIntType1(1);
        elementDto.setIntType2(3);
        elementDto.setElementTypeId(6);

        String jsonElement = new GsonBuilder().setPrettyPrinting().create().toJson(elementDto);

        OutputStream os = httpURLConnection.getOutputStream();
        os.write(jsonElement.getBytes());
        os.flush();

        if (httpURLConnection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + httpURLConnection.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (httpURLConnection.getInputStream())));

        System.out.println("Element created");

        httpURLConnection.disconnect();
    }

}

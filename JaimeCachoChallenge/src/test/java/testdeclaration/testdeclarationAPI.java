package testdeclaration;

import static org.testng.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import dev.failsafe.internal.util.Assert;
import io.restassured.response.Response;
import objects.CatalogItemPage;
import objects.OrderPage;
import objects.ShippingPage;
import objects.PaymentPage;


public class testdeclarationAPI {
	private Properties propMaster = new Properties();
	

	public void APIvalidationALL() {

	        try {
	        	
	          String key = getApiKey();
	       	  String endpoint = getEndPointAll();
		      String url =endpoint+"?access_key="+key;
		      System.out.println(url);
		      
	            // Crear una URL y abrir la conexión HTTP
	            URL apiUrl = new URL(url);
	            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

	            // Establecer el método de solicitud y leer la respuesta
	            connection.setRequestMethod("GET");
	            int responseCode = connection.getResponseCode();
	            //valida codigo de respuesta
	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	                String line;
	                StringBuilder response = new StringBuilder();

	                while ((line = reader.readLine()) != null) {
	                    response.append(line);
	                }
	                reader.close();

	                System.out.println(response.toString());
	            } else {
	                System.out.println("Error en la respuesta: " + responseCode);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	
	
  
  	public void APIvalidationCountry(String countryCode) {

 
  	        try {
  	        	
  	            String key = getApiKey();
  	       	    String endpoint = getEndPointCountry();
  	        	String url=endpoint+countryCode+"?access_key="+key;	  

  	            // Crear una URL y abrir la conexión HTTP
  	            URL apiUrl = new URL(url);
  	            HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

  	            // Establecer el método de solicitud y leer la respuesta
  	            connection.setRequestMethod("GET");
  	            int responseCode = connection.getResponseCode();

  	            if (responseCode == HttpURLConnection.HTTP_OK) {
  	                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
  	                String line;
  	                StringBuilder response = new StringBuilder();

  	                while ((line = reader.readLine()) != null) {
  	                    response.append(line);
  	                }
  	                reader.close();

  	                System.out.println(response.toString());
  	            } else {
  	                System.out.println("Error en la respuesta: " + responseCode);
  	            }
  	        } catch (IOException e) {
  	            e.printStackTrace();
  	        }
  	    }
    
 
    public void APIPostValidation(String name, String alpha2_code, String alpha3_code) {

            try {
            	String key = getApiKey();
                String webservice = getEndPointPost();

                String url = webservice;
                // Crear una URL y abrir la conexión HTTP
                URL apiUrl = new URL(url);
                HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();

                // Establecer el método de solicitud como POST
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("access_key", key);
                connection.setDoOutput(true);

                // Crear el cuerpo de la solicitud POST
          
                String requestBody = "{\"name\": \"" + name + "\", \"alpha2_code\": \"" + alpha2_code + "\", \"alpha3_code\": \"" + alpha3_code + "\"}";

                // Enviar el cuerpo de la solicitud POST
                try (OutputStream outputStream = connection.getOutputStream()) {
                    byte[] input = requestBody.getBytes("utf-8");
                    outputStream.write(input, 0, input.length);
                }

                // Leer la respuesta
                int responseCode = connection.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    String line;
                    StringBuilder response = new StringBuilder();

                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    reader.close();

                    System.out.println(response.toString());
                } else {
                    System.out.println("Error en la respuesta: " + responseCode);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
      
    }   
    

	////obtener key de un propertie
    public String getApiKey() {
            String apiKey;
            try {
                // Cargar el archivo de propiedades
  	  	      propMaster.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

            } catch (IOException e) {
                e.printStackTrace();
            }
            apiKey = StringUtils.deleteWhitespace(propMaster.getProperty("apikey"));
            return apiKey;
        
    }
////obtener key de un endpoint para todos
    public String getEndPointAll() {
        
        String endpoint;
        try {
            // Cargar el archivo de propiedades
	  	      propMaster.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        endpoint = StringUtils.deleteWhitespace(propMaster.getProperty("endpointAll"));
        return endpoint;
}
////obtener key de un endpoint por country
    public String getEndPointCountry() {
        
        String endpoint;
        try {
            // Cargar el archivo de propiedades
	  	      propMaster.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        endpoint = StringUtils.deleteWhitespace(propMaster.getProperty("endpointCountry"));
        return endpoint;
}
	
////obtener key de un endpoint post
    public String getEndPointPost() {
        
        String endpoint;
        try {
            // Cargar el archivo de propiedades
	  	      propMaster.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        endpoint = StringUtils.deleteWhitespace(propMaster.getProperty("endpointPost"));
        return endpoint;
}
	    
}

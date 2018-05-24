package io.branch.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.RandomStringUtils;

public class Global {

 /**
  * This method is to application properties file
  * @return InputStream object
  * @throws : IOException
  */
 public static InputStream loadProperties() throws IOException {
  InputStream input = null;

  String filename = "application.properties";
  input = Global.class.getClassLoader().getResourceAsStream(filename);
  if (input == null) {
   System.out.println("Not able to find " + filename);
   return null;
  } else {
   return input;
  }
 }


 /**
  * This method is to application properties file
  * @param InputStream object
  * @throws : IOException
  */
 public static void closePropertiesFile(InputStream input) {
  if (input != null) {
   try {
    input.close();
   } catch (IOException e) {
    e.printStackTrace();
   }
  }
 }

 public HashMap < String, String > checkForBrokenLinks(List < String > urllist) {
  URL url;
  HashMap < String, String > urlWithReponseMessage = new HashMap < String, String > ();
  try {
   for (String s: urllist) {
    url = new URL(s);
    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
    httpURLConnection.setConnectTimeout(1500);
    httpURLConnection.connect();
    if (!(httpURLConnection.getResponseCode() == 200)) {
     urlWithReponseMessage.put(s, httpURLConnection.getResponseMessage());
    }
 
   }
  } catch (Exception e) {
   e.printStackTrace();
  }
  return urlWithReponseMessage;

 }
 
 public int randomIntegerValue(int min, int max)
 {
	 return ThreadLocalRandom.current().nextInt(min, max + 1);
	 
 }
 
 public String randomEmailGenerator(String domain)
 {
	 return "QA_"+RandomStringUtils.randomAlphanumeric(10)+"@example"+domain;  
 }
 
 public String randomString()
 {
	return "QA_"+ RandomStringUtils.randomAlphanumeric(10);
	 
 }
 
 public String randomPhoneNumber() 
 {
	   Random rand = new Random();
	    int num1, num2, num3;
	    num1 = rand.nextInt (899) + 100;
	    num2 = rand.nextInt (899) + 100;
	    num3 = rand.nextInt (8999) + 1000;
	    return num1+"-"+num2+"-"+num3;
 }
 

 
}
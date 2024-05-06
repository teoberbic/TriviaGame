/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.normaljavaclasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.google.gson.Gson;
import java.util.List;


/**
 *
 * @author teoberbic
 */
public class APIRequestHandler {
    
    private APIRequestHandler(){};
    
    public static List<Question> makeRequest(String numQuestions, String category, String difficulty) {
        
        // Tries to make a request through Http Connection
        try {
                //"https://opentdb.com/api.php?amount=" + 10&token=YOURTOKENHERE
            URL apiURL = new URL("https://opentdb.com/api.php?amount=" + numQuestions + "&category=" + category + "&difficulty=" + difficulty + "&type=multiple"); // this is the url we will be taking info from

            HttpURLConnection URLconnection = (HttpURLConnection) apiURL.openConnection(); // Create an internet connection to the url

            URLconnection.setRequestMethod("GET"); // Set its request to getting the info from the url

            BufferedReader reader = new BufferedReader(new InputStreamReader(URLconnection.getInputStream())); // create a reader to read the info
            String line; 
            StringBuilder stringbuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) { // While the current line is not null
                stringbuilder.append(line); // Append it to stringbuilder
            }
            reader.close();


            String jsonString = stringbuilder.toString();


            URLconnection.disconnect();

            Gson gson = new Gson();
            QuestionContainer qc = gson.fromJson(jsonString, QuestionContainer.class); 
            List<Question> questions = qc.getQuestions();

            return questions;

        } catch (MalformedURLException ex) { // If the url is not valid
            Logger.getLogger(APIRequestHandler.class.getName()).log(Level.SEVERE, null, ex); 

        }   catch (IOException ex) { //if the url connection doesnt go through or building the string
            Logger.getLogger(APIRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        return null;
    }
}

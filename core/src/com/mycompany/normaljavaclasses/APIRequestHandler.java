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
import java.net.http.HttpRequest;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import com.google.gson.Gson;
//import com.mycompany.normaljavaclasses.Question;
//import com.mycompany.javafinal2.QuestionContainer;
//import normaljavaclasses.TriviaGUI;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.text.StringEscapeUtils;


/**
 *
 * @author teoberbic
 */
public class APIRequestHandler {
    
    
    
    private APIRequestHandler(){};
    
    
    public static List<Question> makeRequest(String numQuestions, String category, String difficulty) {
    
        try {
                //"https://opentdb.com/api.php?amount=" + 10&token=YOURTOKENHERE
            URL apiURL = new URL("https://opentdb.com/api.php?amount=" + numQuestions + "&category=" + category + "&difficulty=" + difficulty + "&type=multiple"); // this is the url we will be taking info from

            HttpURLConnection URLconnection = (HttpURLConnection) apiURL.openConnection(); // create an internet connection to the url

            URLconnection.setRequestMethod("GET"); // set its request to getting the info from the url

            int responseCode = URLconnection.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            BufferedReader reader = new BufferedReader(new InputStreamReader(URLconnection.getInputStream())); // create a reader to read the info
            String line; 
            StringBuilder stringbuilder = new StringBuilder();

            while ((line = reader.readLine()) != null) { // while the current line is not null
                stringbuilder.append(line); // append it to stringbuilder
            }
            reader.close();


            String jsonString = stringbuilder.toString();


            //System.out.println("API Response: " + jsonString);


            URLconnection.disconnect();





            Gson gson = new Gson();
            QuestionContainer qc = gson.fromJson(jsonString, QuestionContainer.class); 
            List<Question> questions = qc.getQuestions();


            if (questions != null) {
                for (Question question : questions) {
                    //System.out.println(StringEscapeUtils.unescapeHtml4(question.getQuestion())); // decode the json string to eliminate the HTML characters and make that the string
                }
            } else {
                System.out.println("No questions");
            }
            return questions;


        } catch (MalformedURLException ex) { //if the url is not valid
            Logger.getLogger(APIRequestHandler.class.getName()).log(Level.SEVERE, null, ex); 

        }   catch (IOException ex) { //if the url connection doesnt go through or building the string
            Logger.getLogger(APIRequestHandler.class.getName()).log(Level.SEVERE, null, ex);
        }   
        
        return null;
    }
}

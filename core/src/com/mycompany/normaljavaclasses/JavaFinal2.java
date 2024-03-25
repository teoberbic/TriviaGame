/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.normaljavaclasses;

//import com.mycompany.javafinal2.Category;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.text.StringEscapeUtils;

//import com.mycompany.normaljavaclasses.APIRequestHandler;

/**
 *
 * @author teoberbic
 */
public class JavaFinal2 {
    List<Question> player1Questions;
    public static void main(String[] args) {
        
        List<Question> player1Questions = APIRequestHandler.makeRequest("10", Category.MUSIC.getCategoryNumber(), "easy");
        for (Question q : player1Questions) {
            System.out.print(StringEscapeUtils.unescapeHtml4(q.getQuestion()) + "\n");
        }
        
        
       System.out.print("\n");
       List<Question> player2Questions = APIRequestHandler.makeRequest("10", Category.GEOGRAPHY.getCategoryNumber(), "easy");
        for (Question p : player2Questions) {
           System.out.print(StringEscapeUtils.unescapeHtml4(p.getQuestion()) + "\n");
       }
    }
}

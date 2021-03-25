package com.google.sps.servlets;
import twitter4j.TwitterException;

import com.google.gson.Gson;
import com.google.sps.Twitterer;

import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles requests sent to the /twitter-bot URL. Try running a server and navigating to /twitter-bot! */
@WebServlet("/twitter-bot")
public class TwitterBotServlet extends HttpServlet {
  
  static final long serialVersionUID = 0;
  private static PrintStream consolePrint;
  private Twitterer bigBird = new Twitterer(consolePrint);

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    List<ArrayList<String>> tweets;
    response.setContentType("application/json");
    String hashtag = request.getParameter("hashtag");

    try {
        tweets = bigBird.saQuery(hashtag);
        Gson gson = new Gson();
        String json = gson.toJson(tweets);
        response.getWriter().write(json);
    }  catch (TwitterException e) {
        e.printStackTrace();
    }
        
  }
}

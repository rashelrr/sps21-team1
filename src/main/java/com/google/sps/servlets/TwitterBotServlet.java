package com.google.sps.servlets;
import twitter4j.TwitterException;

import com.google.gson.Gson;
import com.google.sps.TwitterBot;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Handles requests sent to the /twitter-bot URL **/
@WebServlet("/twitter-bot")
public class TwitterBotServlet extends HttpServlet {
  
  static final long serialVersionUID = 0;
  private TwitterBot bigBird = new TwitterBot();

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    LinkedHashMap<String, ArrayList<String>> tweets;
    response.setContentType("application/json");
    String hashtag = request.getParameter("hashtag");

    try {
        tweets = bigBird.getTweets(hashtag);
        Gson gson = new Gson();
        String json = gson.toJson(tweets);
        response.getWriter().write(json);
    }  catch (TwitterException e) {
        e.printStackTrace();
    }    
  }

}

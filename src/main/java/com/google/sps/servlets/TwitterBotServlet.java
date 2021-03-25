package com.google.sps.servlets;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.io.IOException;
import java.io.PrintStream;
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
  private Twitterer bigBird = new Twitterer(consolePrint);;

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    List<Status> tweets;

    try {
      response.setContentType("application/json");
      String hashtag = request.getParameter("hashtag");
      tweets = bigBird.saQuery(hashtag);

      // need to convert tweets to json

      response.getWriter().write(tweets);

    }  catch (TwitterException e) {
      e.printStackTrace();
    }
        
  }
}

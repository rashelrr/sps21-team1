package com.google.sps.servlets;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.TwitterException;

import java.io.PrintStream;

import java.util.ArrayList;
import java.util.List;

public class Twitterer
   {
      private Twitter twitter;
      private PrintStream consolePrint; // reading from command-line or from a file
      private List<Status> statuses;

     
      public Twitterer(PrintStream console)
      {
         // Makes an instance of Twitter - this is re-useable and thread safe.
         // Connects to Twitter and performs authorizations.
         twitter = TwitterFactory.getSingleton(); 
         consolePrint = console;
         statuses = new ArrayList<Status>();
      }    

      // This method finds the last 100 queries of tweets since 2016 with the hashtag

      public List<Status> saQuery (String hashtag) throws TwitterException
      {

         Query query = new Query(hashtag);
         query.setCount(100);                
         query.setSince("2015-12-1");

         try {
            QueryResult result = twitter.search(query);
            return result.getTweets();
            /*System.out.println("Count : " + result.getTweets().size()) ;
            for (Status tweet : result.getTweets()) {
               System.out.println("@"+tweet.getUser().getName()+ ": " + tweet.getText()); 
            }*/
         } 
         catch (TwitterException e) {
            e.printStackTrace();
            return null;
         }
      }
   
   }  

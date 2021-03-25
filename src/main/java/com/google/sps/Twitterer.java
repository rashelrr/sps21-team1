package com.google.sps;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterException;

import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Twitterer
   {

      private Twitter twitter;
      private PrintStream consolePrint; // reading from command-line or from a file
      private List<Status> statuses;
      /*private static final String TWITTER_CONSUMER_KEY = "*****";
      private static final String TWITTER_CONSUMER_SECRET = "******";
      private static final String TWITTER_ACCESS_TOKEN = "*****";
      private static final String TWITTER_ACCESS_TOKEN_SECRET = "*****";*/
      
      public Twitterer(PrintStream console)
      {
         // Makes an instance of Twitter - this is re-useable and thread safe.
         // Connects to Twitter and performs authorizations.
         ConfigurationBuilder cb = new ConfigurationBuilder();
         cb.setDebugEnabled(true)
         .setOAuthConsumerKey(TWITTER_CONSUMER_KEY)
         .setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET)
         .setOAuthAccessToken(TWITTER_ACCESS_TOKEN)
         .setOAuthAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);
         TwitterFactory tf = new TwitterFactory(cb.build());
         twitter = tf.getInstance();
         //twitter = TwitterFactory.getSingleton(); 
         consolePrint = console;
         statuses = new ArrayList<Status>();
      }    

      // This method finds the last 100 queries of tweets with the hashtag
      public List<ArrayList<String>> saQuery (String hashtag) throws TwitterException
      {
         List<ArrayList<String>> final_tweets = new ArrayList<ArrayList<String>>();
         Query query = new Query(hashtag);
         query.setCount(100);                
         query.setSince("2015-12-1");

         try {
            QueryResult result = twitter.search(query);
            List<Status> tweets = result.getTweets();

            // sorts tweets by retweet count
            Collections.sort(tweets, new Comparator<Status>() {
               @Override
               public int compare(Status status1, Status status2) {
                  return Integer.compare(status2.getRetweetCount(), status1.getRetweetCount());
               }
            });

            // adds relevant data to a list
            for (Status tweet : tweets) {
               if (tweet.getLang().equals("en") && tweet.getRetweetCount() > 10) {
                  ArrayList<String> tw = new ArrayList<String>(Arrays.asList(tweet.getUser().getName(), tweet.getText(), String.valueOf(tweet.getRetweetCount())));
                  final_tweets.add(tw);
               }
            }
            return final_tweets;
         } 
         catch (TwitterException e) {
            e.printStackTrace();
            return null;
         }
      }
   
   }  

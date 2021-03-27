package com.google.sps;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TwitterBot {

      private Twitter twitter;
      /*
      private static final String TWITTER_CONSUMER_KEY = "*****";
      private static final String TWITTER_CONSUMER_SECRET = "******";
      private static final String TWITTER_ACCESS_TOKEN = "*****";
      private static final String TWITTER_ACCESS_TOKEN_SECRET = "*****";
      */
      
      public TwitterBot()
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
      }    

      // Returns the most popular tweets with the hashtag
      public List<ArrayList<String>> getTweets (String hashtag) throws TwitterException
      {
         List<ArrayList<String>> final_tweets = new ArrayList<ArrayList<String>>();
         Query query = new Query(hashtag);
         query.setSince("2016-12-1");
         query.setLang("en");
         query.setResultType(Query.POPULAR);

         try {
            QueryResult result = twitter.search(query);

            // appends relevant tweet data to a list
            for (Status tweet : result.getTweets()) {
               ArrayList<String> tw = new ArrayList<String>(Arrays.asList(tweet.getUser().getName(), tweet.getText(), String.valueOf(tweet.getRetweetCount())));
               final_tweets.add(tw);
            }
            return final_tweets;
         } 
         catch (TwitterException e) {
            e.printStackTrace();
            return null;
         }
      }
   
   }  

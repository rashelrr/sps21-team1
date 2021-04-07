package com.google.sps;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TwitterBot {

      private Twitter twitter;
      
      private static final String TWITTER_CONSUMER_KEY = "*****";
      private static final String TWITTER_CONSUMER_SECRET = "******";
      private static final String TWITTER_ACCESS_TOKEN = "*****";
      private static final String TWITTER_ACCESS_TOKEN_SECRET = "*****";
      
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
      public LinkedHashMap<String, ArrayList<String>> getTweets (String hashtag) throws TwitterException
      {
         LinkedHashMap<String, ArrayList<String>> tweets = new LinkedHashMap<String, ArrayList<String>>();
         
         if (TWITTER_CONSUMER_KEY.equals("*****")) {

            // Create Mock tweet for testing without API keys
            ArrayList<String> usernames = new ArrayList<String>();
            ArrayList<String> messages = new ArrayList<String>();
            ArrayList<String> retweetCounts = new ArrayList<String>();
            ArrayList<String> favCounts = new ArrayList<String>();

            usernames.add("John Smith");
            messages.add("This is a tweet message!");
            retweetCounts.add("35");
            favCounts.add("209");

            usernames.add("Sally Smith");
            messages.add("Seashells by the seashore");
            retweetCounts.add("8000");
            favCounts.add("10000");

            tweets.put("usernames", usernames);
            tweets.put("messages", messages);
            tweets.put("retweetCounts", retweetCounts);
            tweets.put("favCounts", favCounts);

            return tweets;

         } else {
            Query query = new Query(hashtag);
            query.setSince("2016-12-1");
            query.setLang("en");
            query.setResultType(Query.POPULAR);

            try {
               QueryResult result = twitter.search(query);
               ArrayList<String> usernames = new ArrayList<String>();
               ArrayList<String> messages = new ArrayList<String>();
               ArrayList<String> retweetCounts = new ArrayList<String>();
               ArrayList<String> favCounts = new ArrayList<String>();

               for (Status tweet : result.getTweets()) {
                  usernames.add(tweet.getUser().getName());
                  messages.add(tweet.getText());
                  retweetCounts.add(String.valueOf(tweet.getRetweetCount()));
                  favCounts.add(String.valueOf(tweet.getFavoriteCount()));
               }

               tweets.put("usernames", usernames);
               tweets.put("messages", messages);
               tweets.put("retweetCounts", retweetCounts);
               tweets.put("favCounts", favCounts);

               return tweets;
            } 
            catch (TwitterException e) {
               e.printStackTrace();
               return null;
            }
         }
      }
   
   }  

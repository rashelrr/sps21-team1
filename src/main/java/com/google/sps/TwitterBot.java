package com.google.sps;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.TwitterException;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.Key;
import com.google.cloud.datastore.KeyFactory;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class TwitterBot {

      private Twitter twitter;
      
      private static String TWITTER_CONSUMER_KEY = "***";
      private static String TWITTER_CONSUMER_SECRET = "***";
      private static String TWITTER_ACCESS_TOKEN = "***";
      private static String TWITTER_ACCESS_TOKEN_SECRET = "***";
      

      public TwitterBot()
      {
         createKeys();

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
      public LinkedHashMap<String, List<String>> getTweets (String hashtag) throws TwitterException
      {
         LinkedHashMap<String, List<String>> tweets = new LinkedHashMap<String, List<String>>();
         List<String> usernames = new ArrayList<String>();
         List<String> messages = new ArrayList<String>();
         List<String> retweetCounts = new ArrayList<String>();
         List<String> favCounts = new ArrayList<String>();

         // If correct keys are not present, creates mock tweet
         if (TWITTER_CONSUMER_KEY.equals("***")) {
            return getMockTweet(tweets, usernames, messages, retweetCounts, favCounts); 
         } else {
            Query query = new Query(hashtag);
            query.setSince("2016-12-1");
            query.setLang("en");
            query.setCount(5);
            query.setResultType(Query.POPULAR);

            try {
               QueryResult result = twitter.search(query);
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


      // Creates Twitter API keys
      public void createKeys() {
         Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
         KeyFactory keyFactory = datastore.newKeyFactory().setKind("Setting");
         Key key = keyFactory.newKey(5634161670881280L);       
         Entity entity = datastore.get(key);
         
         TWITTER_CONSUMER_KEY = entity.getString("TWITTER_CONSUMER_KEY");
         TWITTER_CONSUMER_SECRET = entity.getString("TWITTER_CONSUMER_SECRET");
         TWITTER_ACCESS_TOKEN = entity.getString("TWITTER_ACCESS_TOKEN");
         TWITTER_ACCESS_TOKEN_SECRET = entity.getString("TWITTER_ACCESS_TOKEN_SECRET");
      }
      

      // Creates Mock tweet 
      public LinkedHashMap<String, List<String>> getMockTweet(
         LinkedHashMap<String, List<String>> tweets, List<String> usernames, 
         List<String> messages, List<String> retweetCounts, List<String> favCounts) {         

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
      }
   
   }  
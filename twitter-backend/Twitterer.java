import twitter4j.GeoLocation;       // jar found at http://twitter4j.org/en/index.html
import twitter4j.Paging;
import twitter4j.Query;
//import twitter4j.FilterQuery;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.URLEntity;
import twitter4j.TwitterException;

import java.io.IOException;
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
   
      // This method tweets a given message.
      // @param String  a message you wish to Tweet out

      public void tweetOut(String message) throws TwitterException, IOException
      {
         Status status = twitter.updateStatus(message);
         System.out.println("Successfully updated the status to [" + status.getText() + "].");
      }  
    

      // This method finds the last 100 queries of tweets since 2016 with the hashtag

      public void saQuery (String hashtag) throws TwitterException
      {

         /*
         // Create a FilterQuery object and then set the items to track
         FilterQuery filterQuery = new FilterQuery();
         
         // Create an array of items to track
         String[] itemsToTrack = {hashtag, "donate"};
         
         // Set the items to track using FilterQuerys' track method.
         filterQuery.track(itemsToTrack);

         // Assuming you have already created Twitter/TwitterStream object, use the filter method to start streaming using the FilterQuery object just created.
         twitterstream.filter(filterQuery);
         */

         Query query = new Query(hashtag);
         query.setCount(100);                
         query.setSince("2015-12-1");

         try {
            QueryResult result = twitter.search(query);
            System.out.println("Count : " + result.getTweets().size()) ;
            for (Status tweet : result.getTweets()) {
               System.out.println("@"+tweet.getUser().getName()+ ": " + tweet.getText());  

               // print links if tweet contains keywords
               if (tweetContainsKeywords(tweet.getText())) {
                  // prints URLs in tweet (later add them to a list)
                  if (tweet.getURLEntities().length != 0) {
                     for (URLEntity urle : tweet.getURLEntities())     
                        System.out.println(urle.getDisplayURL());  
                  }
               }

            }
         } 
         catch (TwitterException e) {
            System.out.println("Getting tweet failed");
            e.printStackTrace();
         }
         System.out.println();

      }

      // check if the tweet contains keywords: donate, petition, volunteer
      public boolean tweetContainsKeywords(String text) {
         return text.contains("donate") || text.contains("petition") || text.contains("volunteer");
      }
   
   }  

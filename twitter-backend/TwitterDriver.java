/**
 * Twitter Driver and Client
 * 
 * @author Ria Galanos
 * @author Tony Potter
 * Original idea by Ria Galanos, whose documentation and source can be found at
 * https://github.com/riagalanos/cs1-twitter
 * 
 **/
import twitter4j.TwitterException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class TwitterDriver 
{
   private static PrintStream consolePrint;

   public static void main (String []args) throws TwitterException, IOException
   {
      // set up classpath and properties file
      Twitterer bigBird = new Twitterer(consolePrint);

      // Testing the API: Make a tweet via tweetOut()
      String message = "I'm testing out the twitter4j API for Java.  Thanks @cscheerleader! "
                     + "You can find out more at https://github.com/riagalanos/cs1-twitter";
      bigBird.tweetOut(message);


      // ** User enters a hashtag movement **
      Scanner scan = new Scanner(System.in);
      System.out.print("Please enter a hashtag movement, including the '#' symbol.");
      String twitter_hashtag = scan.next();

      // returns tweets containing the hashtag as a list/json
      bigBird.saQuery(twitter_hashtag);


      /** Next steps:
       * in twitter.java --- make saQuery() return a list of the tweets
       * search for tweets that contain 'donate', 'petition', 'volunteer' in .getText()
       *    if tweet contains a nonempty url, getURL() --> add to a list
       * sort by most likes/retweets
       * convert list to json?
       * send json to frontend
      */


      scan.close();      

   }         
      
}
   
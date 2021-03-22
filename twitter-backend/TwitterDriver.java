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

      // User enters a hashtag movement 
      Scanner scan = new Scanner(System.in);
      System.out.print("Please enter a hashtag movement, excluding the '#' symbol.");
      String twitter_hashtag = scan.next();
      bigBird.saQuery("#" + twitter_hashtag);
      scan.close();      

      /** Next steps:
       * in twitter.java --- make saQuery() return a list of the tweets
       * (extra) sort by most likes/retweets
       * convert list to json?
       * send json to frontend
      */

   }         
      
}
   
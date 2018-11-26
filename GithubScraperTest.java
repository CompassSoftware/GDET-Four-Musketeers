/* 
 * JUnit4 test class
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import java.io.*;
import java.io.IOException;

public class GithubScraperTest {

      @Test
      public void testAnyResponse() {
          GithubScraper git = new GithubScraper("https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/");
          String out = new String();
          try {
              out = git.requestProjectIssues();
          } catch (IOException e) {
              System.out.println("IOException");
          }
	      assertTrue(out.length() > 100);
      }

      @Test
      public void testBaseURL() {
          GithubScraper git = new GithubScraper("https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/");
          String out = new String();
          out = git.getBaseURL();
          assertEquals(out, "https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/");
      }

}

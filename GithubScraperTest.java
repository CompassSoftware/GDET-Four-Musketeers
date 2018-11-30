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
          GithubScraper git = null;
          String[] out = new String[0];
          try {
              git = new GithubScraper("https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/");
              out = git.requestProjectIssues();
          } catch (IOException e) {
              System.out.println("IOException");
          }
	      assertTrue(out.length > 0);
      }

      @Test
      public void testBaseURL() {
          GithubScraper git = null;
          try {
              git = new GithubScraper("https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/");
          } catch (IOException e) {
              System.out.println("IOException");
          }
          String out = new String();
          out = git.getBaseURL();
          assertEquals(out, "https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/");
      }

      @Test
      public void testFirstCommit() {
          GithubScraper git;
          String[] out = new String[0];
          try {
              git = new GithubScraper("https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/");
              out = git.requestProjectCommits();
              System.out.println(out.toString());
          } catch (IOException e) {
              System.out.println("IOException");
          }
          String[] pieces = out[0].split("\"");
          assertEquals("Jay Fenwick", pieces[1]);
      }

}

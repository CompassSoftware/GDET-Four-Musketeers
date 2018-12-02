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
      public void testAnyIssuesResponse() {
          String baseURL = "https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/";
          GithubScraper git = null;
          String[] out = new String[0];
          try {
              git = new GithubScraper(baseURL);
              out = git.requestProjectIssues();
          } catch (IOException e) {
              System.out.println("IOException");
              assertEquals(false, false);
          }
	      assertTrue(out.length > 0);
      }

      @Test
      public void testBaseURL() {
          String baseURL = "https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/";
          GithubScraper git = null;
          try {
              git = new GithubScraper(baseURL);
          } catch (IOException e) {
              System.out.println("IOException");
              assertEquals(false, false);
          }
          String out = new String();
          out = git.getBaseURL();
          assertEquals(out, "https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/");
      }

      @Test
      public void testAnyCommitsResponse() {
          String baseURL = "https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/";
          GithubScraper git;
          String[] out = new String[0];
          try {
              git = new GithubScraper(baseURL);
              out = git.requestProjectCommits();
          } catch (IOException e) {
              System.out.println("IOException");
              assertEquals(false, false);
          }
          assertTrue(out.length > 10);
      }

      @Test
      public void testBadBaseURL() {
          GithubScraper git = null;
          try {
              git = new GithubScraper("https://api.github.com/repos/helloadsdhfdsjfklds/");
          } catch (IOException e) {
              assertEquals(true, true);
          }
          assertEquals(false, false);
      }

      @Test
      public void testGettingIssues() {
          GithubScraper git = null;
          Issues issues = null;
          try {
              git = new GithubScraper("https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/");
              issues = git.getIssues();
          } catch (IOException e) {
              assertEquals(false, false);
          }
          assertTrue(issues != null);
      }

      @Test
      public void testGettingCommits() {
          GithubScraper git = null;
          Commits commits = null;
          try {
              git = new GithubScraper("https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/");
              commits = git.getCommits();
          } catch (IOException e) {
              assertEquals(false, false);
          }
          assertTrue(commits != null);
      }

}

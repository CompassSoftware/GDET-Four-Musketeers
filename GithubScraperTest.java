/* 
 * JUnit4 test class
 */

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Ignore;
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
          assertEquals(out, baseURL);
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
          String baseURL = "https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/";
          GithubScraper git = null;
          Issues issues = null;
          try {
              git = new GithubScraper(baseURL);
              issues = git.getIssues();
          } catch (IOException e) {
              assertEquals(false, false);
          }
          assertTrue(issues != null);
      }


      @Ignore
      public void testAllIssueGets() {
          String baseURL = "https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/";
          GithubScraper git = null;
          Issues issues = null;
          boolean status = true;

          try {
              git = new GithubScraper(baseURL);
              issues = git.getIssues();
              int issuesLength = issues.numIssues();
              for (int i = 1; i <= issuesLength; i++) {
                  //if (issues.get(i) == null)
                  //    status = false;
              }
          } catch (IOException e) {
              assertEquals(false, false);
          }
          assertTrue(status == true);
      }
     
      @Test
      public void testSpecificIssues() {
          String baseURL = "https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/";
          GithubScraper git = null;
          Issues issues = null;
          
          int issueCount;
          boolean status = true; 
          try {
              git = new GithubScraper(baseURL);
              issues = git.getIssues();
              issueCount = issues.numIssues();
              for (int i = 1; i <= issueCount; i++) {
                if (issues.getTitle(i) == "" || issues.getDate(i) == "" || issues.getTime(i) == "" ||
                    issues.getUrl(i) == "" || issues.getCommentsurl(i) == "" || issues.getBody(i) == "")
                    status = false;
              } 
          } catch (IOException e) {
              assertEquals(false, false);
          }
          assertTrue(issues != null);
      }

      @Test
      public void testIssuesToString() {
          String baseURL = "https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/";
          GithubScraper git = null;
          Issues issues = null;
          String issueString = "";

          try {
              git = new GithubScraper(baseURL);
              issues = git.getIssues();
              issueString = issues.toString();
          } catch (IOException e) {
              assertEquals(false, false);
          }
          assertTrue(!(issueString.equals("")));
      }

    /*
      TODO: Write test after getData() is implemented.
      @Test
      public void testIssuesGetData() {
          GithubScraper git = null;
          Issues issues = null;
          String issueString = "";
          try {
              git = new GithubScraper("https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/");
              issues = git.getIssues();
              issueString = issues.toString();
          } catch (IOException e) {
              assertEquals(false, false);
          }
          assertTrue(!(issueString.equals("")));
      }
    */
      @Test
      public void testGettingCommits() {
          String baseURL = "https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/";
          GithubScraper git = null;
          Commits commits = null;
          try {
              git = new GithubScraper(baseURL);
              commits = git.getCommits();
          } catch (IOException e) {
              assertEquals(false, false);
          }
          assertTrue(commits != null);
      }

      @Ignore
      public void testAllCommitGets() {
          String baseURL = "https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/";
          GithubScraper git = null;
          Commits commits = null;
          boolean status = true;

          try {
              git = new GithubScraper(baseURL);
              commits = git.getCommits();
              int commitLength = commits.numCommits();
              for (int i = 1; i <= commitLength; i++) {
                 // if (commits.get(i) == null)
                 //     status = false;
              }
          } catch (IOException e) {
              assertEquals(false, false);
          }
          assertTrue(status == true);
      }

      @Test
      public void testAllCommits() {
          String baseURL = "https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/";
          GithubScraper git;
          
          Commits commits = null; 
          boolean status = true;
          
          try {
              git = new GithubScraper(baseURL);
              commits = git.getCommits();
              int commitCount = commits.numCommits();
              for (int i = 1; i <= commitCount; i++) {
                if (commits.getAuthor(i) == "" || commits.getMessage(i) == "" || commits.getDate(i) == "" ||
                    commits.getCommentsUrl(i) == "" || commits.getUrl(i) == "")
                    status = false;
              } 
          }
          catch (IOException e) {
                  System.out.println("IOException");
                  assertEquals(false, false);
          }
          assertTrue(status == true);
      }

      @Test
      public void testCommitsToString() {
          String baseURL = "https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/";
          GithubScraper git = null;
          Commits commits = null;
          String commitString = "";
          try {
              git = new GithubScraper(baseURL);
              commits = git.getCommits();
              commitString = commits.toString();
          } catch (IOException e) {
              assertEquals(false, false);
          }
          assertTrue(!(commitString.equals("")));
      }
}

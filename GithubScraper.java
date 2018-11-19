import java.net.URL;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.lang.StringBuilder;
import java.net.URLConnection;
import java.net.HttpURLConnection;
import java.io.Reader;

/**
 * The GithubScraper tool from the four musketeers team.
 *
 * @author Patrick Beekman
 * @author Dillon Carns
 * @author Eli McGalliard
 * @author Sam Barr
 * @version 11/13/2018
 */
public class GithubScraper {

    private String baseURL = "";
    
    public GithubScraper(String url) {
        setBaseURL(url);
    }

    /*
     * Sets the base url of the project
     */
    public void setBaseURL(String url) {
        baseURL = url;
    }

    /*
     * Gets the base url of the project
     */
    public String getBaseURL() {
        return baseURL;
    }

    public String requestProjectIssues() throws IOException {
        System.out.println("Issues retrieved:");
        return curlRequest("issues");
    }

    public String requestProjectCommits() throws IOException {
        System.out.println("Commits retrieved:");
        return curlRequest("commits");
    }

    /*
     * Takes in some url extensions to curl and then returns the response
     * @param urlEXT - String the url extensions you want for your curl request
     * @return the response from the curl request
     */
    private String curlRequest(String urlExt) throws IOException {

        URL urlObj = new URL(getBaseURL() + urlExt);
        HttpURLConnection uc = (HttpURLConnection) urlObj.openConnection();
        uc.setRequestProperty("X-Requested-With", "Curl");
        InputStreamReader inputStream = new InputStreamReader(uc.getInputStream());
        // Read the input stream
        int responseCode = uc.getResponseCode();
        BufferedReader bufReader = new BufferedReader(inputStream);
        
        String inputLine;
        StringBuffer response = new StringBuffer();
        while((inputLine = bufReader.readLine()) != null) {
            response.append(inputLine);
        }
        
        bufReader.close();
        
        return buildOutput(response.toString().split(","));
        
    }
    /**
     * this method was extracted in order to create strings from built arrays.
     * This allows us to parse the json curl requests a little easier.
     * @param arguments of type String array.
     */
    private String buildOutput(String[] arguments) {
        
        final StringBuilder out = new StringBuilder();
       
        int i = 1;
        for (String arg : arguments) {
            if(arg.contains("title"))
                out.append(i++ + ". " + arg.split(":")[1] + "\n");
            
        }

        return out.toString();

    }

    public static void main(String[] args) throws IOException {
        GithubScraper scraper = new GithubScraper("https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/");
        System.out.println(scraper.requestProjectIssues());
        System.out.println(scraper.requestProjectCommits());
    }

}

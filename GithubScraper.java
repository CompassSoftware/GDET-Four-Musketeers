import java.net.URL;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.net.URLConnection;
import java.io.Reader;
import org.json.*;

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
        return curlRequest("issues");
    }

    /*
     * Takes in some url extensions to curl and then returns the response
     * @param urlEXT - String the url extensions you want for your curl request
     * @return the response from the curl request
     */
    public String curlRequest(String urlExt) throws IOException {

        URL urlObj = new URL(getBaseURL() + urlExt);
        URLConnection uc = urlObj.openConnection();

        uc.setRequestProperty("X-Requested-With", "Curl");
        JSONObject jObj;
        InputStreamReader inputStream = new InputStreamReader(uc.getInputStream());
        // Read the input stream
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        for (; ; ) {
            int rsz = inputStream.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        //return out.toString();
        
         
        try {
             jObj = HTTP.toJSONObject(out.toString());
             //jObj = new JSONObject(jObj, new String[] {"https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers"});
        } catch (Exception e) {
            return "Failed to parse as JSONObject";
        }
        
        return jObj.toString();
        
    }

    public static void main(String[] args) throws IOException {
        GithubScraper scraper = new GithubScraper("https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/");
        System.out.println(scraper.requestProjectIssues());
    }

}

import java.net.URL;
import java.io.InputStreamReader;
import java.io.IOException;
import java.lang.StringBuilder;
import java.net.URLConnection;

public class GithubScraper {
    
    public GithubScraper() {
        
    }

    public void printCURL(String url) throws IOException {

        URL urlObj = new URL(url);
        URLConnection uc = urlObj.openConnection();

        uc.setRequestProperty("X-Requested-With", "Curl");

        InputStreamReader inputStream = new InputStreamReader(uc.getInputStream());
        // Read the input stream
        final int bufferSize = 1024;
        final char[] buffer = new char[bufferSize];
        final StringBuilder out = new StringBuilder();
        //Reader in = new InputStreamReader(inputStream, "UTF-8");
        for (; ; ) {
            int rsz = inputStream.read(buffer, 0, buffer.length);
            if (rsz < 0)
                break;
            out.append(buffer, 0, rsz);
        }
        System.out.print(out.toString());
    }

    public static void main(String[] args) throws IOException {
        System.out.println("hi");
        GithubScraper scraper = new GithubScraper();
        scraper.printCURL("https://api.github.com/repos/CompassSoftware/GDET-Four-Musketeers/issues");
    }

}

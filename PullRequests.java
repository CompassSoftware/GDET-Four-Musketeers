import java.util.ArrayList;
/**
 * This class is responsible for keeping track of the pullRequest data for a given repo.
 * Used in the GithubScraper class primarily.
 * Help methods will be added to get specific data later.
 * @version 12/02/2018
 */
public class PullRequests extends GitObject {
    

    private ArrayList<PullRequest> pullRequests;

    // == constructors ==

    public PullRequests(String[] data) {
        super(data);
        pullRequests = new ArrayList<>();
        prepPullRequests();
    }

    // == public methods ==
    public void getData(String keyword) {
        //TODO: based on a keyword get specified data -- helper methods needed.   
    }

    public String toString() {
        String ret = "";
        int j = pullRequests.size();
        for (int i = 0; i < pullRequests.size(); i++) {
            ret += (j--) + ") " + pullRequests.get(i).toString() + "\n";
        }
        return ret;
    }

    public PullRequest get(int toGet) {
        return pullRequests.get(pullRequests.size() - toGet);
    }

    // == private methods ==
    private void prepPullRequests() {
        /**
        String[] temp = getData();
        int count = 0;
        String author = "";
        String message = ""; 
        String date = "";
        String comments_url = "";
        String url = "";
        for (int i = 0; i <  temp.length; i++) {
            String[] args = temp[i].split(":");
            if (args[0].toLowerCase().equals("\"commit\"")) {
                author = args[3];
                String[] dates = temp[i + 5].split(":");
                date = dates[0];
                String[] msg = temp[i + 6].split(":");
                message = msg[1];
                count++;
            }
            else if (args[0].toLowerCase().equals("{\"url\"")) {
                url = "";
                for (int j = 1; j < args.length; j++) url += args[j];
                count++;
            }
            else if (args[0].toLowerCase().contains("comments_url")) {
                comments_url = "";
                for (int j = 1; j < args.length; j++) comments_url += args[j];
                count++;
            }

            if (count == 3)
            {
                commits.add(new Commit(author, message, date, comments_url, url));
                count = 0;
                author = "";
                message = ""; 
                date = "";
                comments_url = "";
                url = "";
            }
        }**/
    }

    // == inner class ==
    private class PullRequest {
       public String author; 
       public String message; 
       public String date;
       public String comments_url;
       public String url;
       
       public PullRequest(String author, String message, String date, String comments_url, String url) {
           this.author = author;
           this.message = message;
           this.date = date;
           this.comments_url = comments_url;
           this.url = url;
       }

       public String toString() {
            return "Author: " + author + ", Message: " + message;
       }
    }
}

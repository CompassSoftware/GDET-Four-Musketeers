import java.util.ArrayList;
/**
 * This class is responsible for keepign track of the commit data for a given repo.
 * Used in the GithubScraper class primarily.
 * Help methods will be added to get specific data later.
 * @version 11/28/2018
 */
public class Commits extends GitObject {
    

    private ArrayList<Commit> commits;

    // == constructors ==

    public Commits(String[] data) {
        super(data);
        commits = new ArrayList<>();
        prepCommits();
    }

    // == public methods ==
    public void getData(String keyword) {
        //TODO: based on a keyword get specified data -- helper methods needed.   
    }

    public String toString() {
        String ret = "";
        int j = commits.size();
        for (int i = 0; i < commits.size(); i++) {
            ret += (j--) + ") " + commits.get(i).toString() + "\n";
        }
        return ret;
    }

    public Commit get(int toGet) {
        return commits.get(commits.size() - toGet);
    }

    // == private methods ==
    private void prepCommits() {
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
        }
    }

    // == inner class ==
    private class Commit {
       public String author; 
       public String message; 
       public String date;
       public String comments_url;
       public String url;
       
       public Commit(String author, String message, String date, String comments_url, String url) {
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

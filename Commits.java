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

    public Commits(String[] data, ArrayList<String> contribs) {
        super(data, contribs);
        commits = new ArrayList<>();
        prepCommits();
    }

    // == public methods ==
    public String getGeneral() {
        ArrayList<String> users = getContributors();
        int[] counts = new int[users.size()];
        /*for (int i = 0; i < counts.length; i++) {
            counts[i] = 0;
        }
        */
        String output = "Commits ("+ commits.size() +"):\n--------------\n";
        for (int i = 0; i < users.size(); i++) {
            String user = users.get(i);
            ArrayList<String> msgs = new ArrayList<>();
            for (Commit commit : commits) {
                //System.out.println(commit.author + " " + user);
                if (commit.userName.equals(user)) {
                    //System.out.println("working");
                    counts[i]++;
                    msgs.add(commit.message);
                }
            }
            output += "User: " + user 
                   + "\nNumber of Commits: " + counts[i]
                   + "\nMessages: ";
            for (String msg : msgs) {
                output +="\n>" + msg;
            }

            output += "\n-------------------------------\n";
        }

        return output;
    }

    public String getData(String keyword) {
        //TODO: based on a keyword get specified data -- helper methods needed.   
        return "";
    }

    public String toString() {
        String ret = "";
        int j = commits.size();
        for (int i = 0; i < commits.size(); i++) {
            ret += (j--) + ") " + commits.get(i).toString() + "\n";
        }
        return ret;
    }

    public String getAuthor(int toGet) {
        return commits.get(commits.size() - toGet).author;
    }

    public String getUserName(int toGet) {
        return commits.get(commits.size() - toGet).userName;
    }

    public String getDate(int toGet) {
        return commits.get(commits.size() - toGet).date;
    }

    public String getMessage(int toGet) {
        return commits.get(commits.size() - toGet).message;
    }

    public String getCommentsUrl(int toGet) {
        return commits.get(commits.size() - toGet).comments_url;
    }

    public String getUrl(int toGet) {
        return commits.get(commits.size() - toGet).url;
    }

    public int numCommits() {
        return commits.size();
    }

    // == private methods ==
    private boolean isInList(ArrayList<String> list, String keyword) {
        
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).equals(keyword))
                return true;
        }
        return false;
    }

    private void prepCommits() {
        String[] temp = getData();
        int count = 0;
        String author = "";
        String message = ""; 
        String date = "";
        String comments_url = "";
        String url = "";
        String userName = "";
        int userCount = 0;
        for (int i = 0; i <  temp.length; i++) {
            //System.out.println(temp[i]);
            String[] args = temp[i].split(":");
            if (args[0].toLowerCase().equals("\"commit\"")) {
                author = args[3];
                String[] dates = temp[i + 5].split(":");
                date = dates[1];
                String[] msg = temp[i + 6].split(":");
                message = msg[1];
                String[] r = temp[i + 9].split(":");
                url = r[1] + ":" + r[2];
                count++;
                String[] userData = temp[i + 24].split(":");
                userName = "\"" + userData[2].split("/")[3];
            }
            else if (args[0].toLowerCase().contains("comments_url")) {
                comments_url = "";
                for (int j = 1; j < args.length; j++) comments_url += args[j];
                count++;
                //System.out.println("three");
            }

            if (count == 3)
            {
                //System.out.println(url);
                commits.add(new Commit(author, message, date, comments_url, url, userName));
                count = 0;
                userName = "";
                author = "";
                message = ""; 
                date = "";
                comments_url = "";
                url = "";
            }
        }
    }

    private boolean inContribs(String name) {
        for (String c : getContributors()) {
            //System.out.println(name + " " + c);
            if(name.equals(c))
                return true;
        }
        return false;
    }

    // == inner class ==
    private class Commit {
       public String author; 
       public String message; 
       public String date;
       public String comments_url;
       public String url;
       public String userName;
       
       public Commit(String author, String message, String date, String comments_url, String url, String userName) {
           this.author = author;
           this.message = message;
           this.date = date;
           this.comments_url = comments_url;
           this.url = url;
           this.userName = userName;
       }

       public String toString() {
            return "Author: " + author 
                + ", Username: " + userName 
                + ", Message: " + message 
                + ", Date: " + date
                + ", Comments URL: " + comments_url
                + ", URL: " + url
                ;
       }
    }
}

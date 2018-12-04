import java.util.ArrayList;
/**
 * This is the gitobject class,
 * provides a base for gitobject related extensions.
 * @version 11/26/2018
 */
public abstract class GitObject {
    
    private String[] data;
    private ArrayList<String> contributors;

    private GitObject() {
        //dont allow no-arg constructors
    }

    public GitObject(String[] args, ArrayList<String> contribs) {
        data = args;
        contributors = contribs;
    }

    public ArrayList<String> getContributors() {
        return contributors;
    }

    public void setData(ArrayList<String> contributors) {
        this.contributors = contributors;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }
    
    public abstract String getGeneral();

    public abstract String getData(String keyWord);
}

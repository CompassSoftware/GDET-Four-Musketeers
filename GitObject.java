/**
 * This is the issues class,
 * handles all things issues related.
 * @version 11/26/2018
 */
public abstract class GitObject {
    
    private String[] data;

    public GitObject() {
        this(new String[100]);
    }

    public GitObject(String[] args) {
        data = args;
    }

    public String[] getData() {
        return data;
    }

    public void setData(String[] data) {
        this.data = data;
    }

    public abstract void getData(String keyWord);
}

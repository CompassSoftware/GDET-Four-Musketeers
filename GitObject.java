/**
 * This is the issues class,
 * handles all things issues related.
 * @version 11/26/2018
 */
public class abstract GitObject {
    
    private String[] data;

    public GitObject() {
        this(new String[100]);
    }

    public GitObject(String[] args) {
        data = args;
    }

    public getData() {
        return data;
    }

    public abstract getData(String keyWord);
}

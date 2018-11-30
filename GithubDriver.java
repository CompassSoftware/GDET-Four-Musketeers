import java.util.Scanner;
import java.io.IOException;

public class GithubDriver {

    public static final int ISSUES = 1;
    public static final int COMMITS = 2;
    public static final int QUIT = 3;

    public static void printUsage() {
        System.out.printf("Please indicate a valid repository.\n");
    }
    
    public static void printSelectUsage() {
        System.out.printf("Please indicate a valid choice.\n");
    }

    public static void main(String[] args) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter the repo name: ");
        String repoName = keyboard.nextLine();
        GithubScraper scraper = null; 
        try {
            scraper = new GithubScraper("https://api.github.com/repos/" + repoName + "/"); //"CompassSoftware/GDET-Four-Musketeers/");

        int choice = 0;

        while (choice != QUIT) {
            System.out.println("Please indicate what you would like to see:\n"
                + "1)\tIssues\n"
                + "2)\tCommits\n"
                + "3)\tExit\n"
                + "(More on the way soon!)\n");

            choice = keyboard.nextInt();
            keyboard.nextLine();
             
            switch(choice) {
                case ISSUES:
                    //TODO: create choices over issues
                    System.out.println(scraper.getIssues());
                    break;
                case COMMITS:
                    //TODO: create choices over commits
                    //System.out.println(scraper.requestProjectCommits());
                    System.out.println(scraper.getCommits());
                    break;
                case QUIT:
                    System.exit(0);
                default:
                    printSelectUsage();
                    break; 
                }
            }
        } catch (Exception e) {
            printUsage();
            return;
        }
    }
}

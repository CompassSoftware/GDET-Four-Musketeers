import java.util.Scanner;
import java.io.IOException;

public class GithubDriver {

    // == main menu options ==
    private  static final int ISSUES = 1;
    private static final int COMMITS = 2;
    private static final int QUIT = 3;

    // == secondary menu options ==
    private static final int GENERAL = 1;
    private static final int COMMENTS = 2;
    private static final int SPECIFIC = 3;
    private static final int LIST = 4;
    private static final int RETURN = 5;

    private static final String ISSUES_STR = "Issues";
    private static final String COMMITS_STR = "Commits";

    private static void printUsage() {
        System.out.printf("Please indicate a valid repository next time.\n");
    }
    
    private static void printSelectUsage() {
        System.out.printf("Please indicate a valid choice.\n");
    }

    private static String getOptionName(int opt) {
        switch(opt) {
            case ISSUES:
                return ISSUES_STR;
            case COMMITS:
                return COMMITS_STR;
            default:
                return "";
        }
    }

    private static void startRoutine(Scanner s, GithubScraper scraper, int opt) {
        String c = getOptionName(opt);
        System.out.println("Please select " + c + " choice:"
                           + "\n1) General Info"
                           + "\n2) Comments"
                           + "\n3) Info on specific commit."
                           + "\n4) List the " + c
                           + "\n5) Return to main menu\n"); 
        while (true) {
            int choice = s.nextInt();
            s.nextLine();
            switch(choice) {
                case GENERAL:
                    if (opt == ISSUES)
                        System.out.println(scraper.getIssues().getGeneral());
                    else if (opt == COMMITS)
                        System.out.println(scraper.getCommits().getGeneral());
                    break;
                case COMMENTS:
                    //TODO
                    break;
                case SPECIFIC:
                    //TODO
                    System.out.println("Please indicate a valid choice from the " + c);
                    int a = s.nextInt();
                    s.nextLine();
                    if (a > 0)
                    break;
                case LIST:
                    if (opt == ISSUES)
                        System.out.println(scraper.getIssues());
                    else if (opt == COMMITS)
                        System.out.println(scraper.getCommits());
                    break;
                case RETURN:
                    return;
                default:
                    break;
            }
        System.out.println("Please select " + c + "  choice:"
                           + "\n1) General Info"
                           + "\n2) Comments"
                           + "\n3) Info on specific commit."
                           + "\n4) List the " + c
                           + "\n5) Return to main menu\n"); 
        }
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
            System.out.println("Please select a repo choice:\n"
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
                    startRoutine(keyboard, scraper, ISSUES);
                    break;
                case COMMITS:
                    //TODO: create choices over commits
                    System.out.println(scraper.getCommits());
                    startRoutine(keyboard, scraper, COMMITS);
                    break;
                case QUIT:
                    System.out.println("Goodbye!");
                    return;
                default:
                    printSelectUsage();
                    break; 
                }
            }
        } catch (Exception e) {
            printUsage();
        }
    }
}

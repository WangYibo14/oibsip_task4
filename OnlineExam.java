import java.util.*;

public class OnlineExam {
    private String un;
    private String passcode;
    private boolean isLoggedIn;
    private int timeRemaining;
    private int questionCount;
    private int[] userAns;
    private int[] crtAns;

    public OnlineExam(String un, String passcode) {
        this.un = un;
        this.passcode = passcode;
        System.out.println("You have SUCCESSFULLY REGISTERED");
        this.isLoggedIn = false;
        this.timeRemaining = 10;
        this.questionCount = 10;
        this.userAns = new int[questionCount];
        this.crtAns = new int[questionCount];
        for (int i = 0; i < questionCount; i++) {
            crtAns[i] = (int) Math.round(Math.random());
        }
    }

    public void login() {
        System.out.println("LOG IN To Proceed your Exam");
        Scanner sc = new Scanner(System.in);
        System.out.print("USERNAME:-");
        String inputUsername = sc.nextLine();
        System.out.print("PASSWORD:- ");
        String inputPassword = sc.nextLine();
        if (inputUsername.equals(un) && inputPassword.equals(passcode)) {
            isLoggedIn = true;
            System.out.println("You Have SUCCESSFULLY LOGGED IN. WE WISH YOU ALL THE BEST ");
        } else {
            System.out.println("INVALID CREDINTILS! TRY AGAIN ...");
        }
    }

    public void logout() {
        isLoggedIn = false;
        System.out.println("YOU HAVE SUCCESSFULLY LOGGED OUT");
    }

    public void startExam() {
        if (!isLoggedIn) {
            System.out.println("YOU NEED TO LOG IN FIRST...");
            return;
        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("YOU HAVE " + timeRemaining + " MINUTES TO COMPLETE THE EXAM");
        for (int i = 0; i < questionCount; i++) {
            System.out.println("Question " + (i + 1) + ":");
            System.out.println("1. Option 1");
            System.out.println("2. Option 2");
            System.out.print("Your answer (1 or 2): ");
            int ans = scanner.nextInt();
            userAns[i] = ans;
        }

        System.out.println("WOULD YOU LIKE TO SUBMIT YOUR ANSWERS? \n1:Yes \n2:NO ");
        int n = scanner.nextInt();
        if (n == 1) {
            submitExam();
        } else {
            try {
                Thread.sleep(timeRemaining * 10 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                submitExam();
            }

        }

    }

    public void submitExam() {
        if (!isLoggedIn) {
            System.out.println("PLEASE LOG IN...");
            return;
        }
        int score = 0;
        for (int i = 0; i < questionCount; i++) {
            if (userAns[i] == crtAns[i]) {
                score++;
            }
        }
        System.out.println("YOU HAVE SCORED " + score + " OUT OFF " + questionCount + ".");
        System.out.println("THANK YOU !");
        logout();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("PLEASE ENTER USERNAME AND PASSWORD:-");
        String uName = sc.nextLine();
        String pWord = sc.nextLine();
        OnlineExam examSystem = new OnlineExam(uName, pWord);
        examSystem.login();
        examSystem.startExam();
    }
}
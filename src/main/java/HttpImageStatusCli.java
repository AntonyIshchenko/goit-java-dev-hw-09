import java.io.IOException;
import java.util.Scanner;

public class HttpImageStatusCli {

    public void askStatus() {
        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Enter HTTP status code:");
            boolean exit = false;

            while (!exit) {
                String text = scanner.nextLine();

                try {
                    int code = Integer.parseInt(text);

                    HttpStatusImageDownloader.downloadStatusImage(code);
                    exit = true;
                } catch (IOException ex) {
                    System.out.println("There is not image for HTTP status " + text);
                    exit = true;
                } catch (IllegalArgumentException ex) {
                    System.out.println("Please enter valid number. " + ex.getMessage());
                } catch (InterruptedException ex) {
                    System.out.println("Operation aborted: " + ex.getMessage());
                    System.out.println("Try another time");
                    exit = true;

                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}

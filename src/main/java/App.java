import java.io.IOException;

public class App {
    public static void main(String[] args) {
        try {
            HttpStatusImageDownloader.downloadStatusImage(200);
        } catch (IOException | InterruptedException | IllegalArgumentException ex) {
            ex.printStackTrace();
        }
    }
}

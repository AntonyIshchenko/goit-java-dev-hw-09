import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.nio.file.Paths;

public class HttpStatusImageDownloader {

    private HttpStatusImageDownloader() {
    }

    public static void downloadStatusImage(int code) throws IOException, InterruptedException, IllegalArgumentException {

        String url = HttpStatusChecker.getStatusImage(code);

        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url)).
                build();

        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            String fileName = url.substring(url.lastIndexOf('/')).replace("/", "");
            Path targetPath = Paths.get(fileName);

            HttpResponse<Path> response = httpClient.send(request, HttpResponse.BodyHandlers.ofFile(targetPath));
            if (response.statusCode() != 200) {
                throw new IOException("Not found");
            }
        }
    }
}

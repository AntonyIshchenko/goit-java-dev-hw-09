import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class HttpStatusChecker {
    public static final int MIN_HTTP_STATUS_CODE = 100;
    public static final int MAX_HTTP_STATUS_CODE = 599;

    private HttpStatusChecker() {
    }

    public static String getStatusImage(int code) throws IOException, InterruptedException, IllegalArgumentException {

        if (code < MIN_HTTP_STATUS_CODE || code > MAX_HTTP_STATUS_CODE) {
            throw new IllegalArgumentException("Code must be between " + MIN_HTTP_STATUS_CODE + " and " + MAX_HTTP_STATUS_CODE);
        }

        String url = "https://http.cat/" + code;
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url)).
                method("HEAD", HttpRequest.BodyPublishers.noBody()).
                build();

        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());

            if (response.statusCode() == HttpURLConnection.HTTP_OK) {
                String extension = "";
                Optional<String> contentType = response.headers().firstValue("Content-Type");
                if (contentType.isPresent()) {
                    String type = contentType.get().toLowerCase();
                    if (type.contains("jpeg") || type.contains("jpg")) {
                        extension = ".jpg";
                    } else if (type.contains("png")) {
                        extension = ".png";
                    } else if (type.contains("gif")) {
                        extension = ".gif";
                    } else if (type.contains("webp")) {
                        extension = ".webp";
                    }
                }

                return url + extension;
            } else {
                throw new IOException("Image with status code " + code + " not found");
            }
        }
    }
}

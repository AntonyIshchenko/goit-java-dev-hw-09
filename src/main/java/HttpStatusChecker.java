import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class HttpStatusChecker {
    public String getStatusImage(int code) throws IOException, InterruptedException, IllegalArgumentException {

        if (code < 100 || code > 599) {
            throw new IllegalArgumentException("Code must be between 100 and 599");
        }

        String url = "https://http.cat/" + code;
        HttpRequest request = HttpRequest.newBuilder().
                uri(URI.create(url)).
                build();

        try (HttpClient httpClient = HttpClient.newHttpClient()) {
            HttpResponse<Void> response = httpClient.send(request, HttpResponse.BodyHandlers.discarding());

            if (response.statusCode() == 200) {
                return url;
            } else {
                throw new IOException("Image with status code " + code + " not found");
            }
        }
    }
}

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class HttpStatusCheckerTest {

    @Test
    void testThatStatusCheckerFindCode200() {
        // When
        String actual = "";
        try {
            actual = HttpStatusChecker.getStatusImage(200);
        } catch (Exception ex) {
            actual = "exception";
        }

        // Then
        String expected = "https://http.cat/200.jpg";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testThatStatusCheckerFindCode404() {
        // When
        String actual = "";
        try {
            actual = HttpStatusChecker.getStatusImage(404);
        } catch (Exception ex) {
            actual = "exception";
        }

        // Then
        String expected = "https://http.cat/404.jpg";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testThatStatusCheckerThrowsIOException() {
        Assertions.assertThrows(IOException.class, () -> {
            HttpStatusChecker.getStatusImage(555);
        });
    }

    @Test
    void testThatStatusCheckerThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            HttpStatusChecker.getStatusImage(10);
        });
    }
}

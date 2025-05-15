import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class HttpStatusCheckerTest {
    private HttpStatusChecker statusChecker;

    @BeforeEach
    void beforeEach() {
        statusChecker = new HttpStatusChecker();
    }

    @Test
    void testThatStatusCheckerFindCode200() {
        // When
        String actual = "";
        try {
            actual = statusChecker.getStatusImage(200);
        } catch (Exception ex) {
            actual = "exception";
        }

        // Then
        String expected = "https://http.cat/200";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testThatStatusCheckerFindCode404() {
        // When
        String actual = "";
        try {
            actual = statusChecker.getStatusImage(404);
        } catch (Exception ex) {
            actual = "exception";
        }

        // Then
        String expected = "https://http.cat/404";
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testThatStatusCheckerThrowsIOException() {
        Assertions.assertThrows(IOException.class, () -> {
            statusChecker.getStatusImage(555);
        });
    }

    @Test
    void testThatStatusCheckerThrowsIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            statusChecker.getStatusImage(10);
        });
    }
}

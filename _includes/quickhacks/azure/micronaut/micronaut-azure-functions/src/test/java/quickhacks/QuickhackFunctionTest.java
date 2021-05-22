package quickhacks;

import com.microsoft.azure.functions.HttpStatus;
import io.micronaut.azure.function.http.HttpRequestMessageBuilder;
import io.micronaut.http.HttpMethod;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuickhackFunctionTest {

    @Test
    public void testFunction() throws Exception {
        try (QuickhackFunction function = new QuickhackFunction()) {
            HttpRequestMessageBuilder.AzureHttpResponseMessage response =
                    function.request(HttpMethod.GET, "/quickhack")
                            .invoke();

            assertEquals(HttpStatus.OK, response.getStatus());
        }
    }
}

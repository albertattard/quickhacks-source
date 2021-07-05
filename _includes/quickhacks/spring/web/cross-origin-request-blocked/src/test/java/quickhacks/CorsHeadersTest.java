package quickhacks;

import static java.util.Objects.requireNonNull;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;

@DisplayName("CORS Headers")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CorsHeadersTest {

    private static final String ORIGIN = "http://localhost:3000";

    @Autowired
    private transient TestRestTemplate restTemplate;

    static {
        /* NOTE: RestTemplate makes use of the HttpUrlConnection class, which has the 'Origin' header as one of the
           restricted headers.  The following will make the HttpUrlConnection send the 'Origin' header. */
        System.setProperty("sun.net.http.allowRestrictedHeaders", "true");
    }

    @ValueSource(strings = {ORIGIN})
    @ParameterizedTest(name = "should contain the allowed origin in the response headers when using origin {0}")
    void shouldContainTheAllowedOriginInTheResponseHeaders(final String origin) {
        /* Given */
        final HttpEntity<LinkedMultiValueMap<String, Object>> entity = createEntityWithOrigin(origin);

        /* When */
        final ResponseEntity<String> response = sendRequest(entity);

        /* Then */
        final HttpHeaders headers = response.getHeaders();
        assertThat(headers.getAccessControlAllowOrigin())
                .describedAs("The Access-Control-Allow-Origin header should be set")
                .isEqualTo(origin);
    }

    @Test
    @DisplayName("should not contain the allowed origin in the response headers as the origin is unknown")
    void shouldNotContainTheAllowedOriginInTheResponseHeadersAsTheOriginIsUnknown() {
        /* Given */
        final String origin = "https://somewhere.com";
        final HttpEntity<LinkedMultiValueMap<String, Object>> entity = createEntityWithOrigin(origin);

        /* When */
        final ResponseEntity<String> response = sendRequest(entity);

        /* Then */
        final HttpHeaders headers = response.getHeaders();
        assertThat(headers.getAccessControlAllowOrigin())
                .describedAs("The Access-Control-Allow-Origin header should not be set")
                .isNull();
    }

    @Test
    @DisplayName("should not contain the allowed origin in the response headers if the origin is missing in request")
    void shouldNotContainTheAllowedOriginInTheResponseHeadersIfTheOriginIsMissingInRequest() {
        /* Given */
        final HttpEntity<LinkedMultiValueMap<String, Object>> entity = createEntityWithoutOrigin();

        /* When */
        final ResponseEntity<String> response = sendRequest(entity);

        /* Then */
        final HttpHeaders headers = response.getHeaders();
        assertThat(headers.getAccessControlAllowOrigin())
                .describedAs("The Access-Control-Allow-Origin header should not be set")
                .isNull();
    }

    private ResponseEntity<String> sendRequest(final HttpEntity<LinkedMultiValueMap<String, Object>> entity) {
        requireNonNull(entity);

        return restTemplate.exchange("/quickhack/name", HttpMethod.POST, entity, String.class);
    }

    private HttpEntity<LinkedMultiValueMap<String, Object>> createEntityWithOrigin(final String origin) {
        requireNonNull(origin);

        final LinkedMultiValueMap<String, Object> body = createBody();
        final HttpHeaders headers = new HttpHeaders();
        headers.setOrigin(origin);
        return new HttpEntity<>(body, headers);
    }

    private HttpEntity<LinkedMultiValueMap<String, Object>> createEntityWithoutOrigin() {
        final LinkedMultiValueMap<String, Object> body = createBody();
        final HttpHeaders headers = new HttpHeaders();
        return new HttpEntity<>(body, headers);
    }

    private LinkedMultiValueMap<String, Object> createBody() {
        final LinkedMultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("name", "Quickhack");
        return body;
    }
}

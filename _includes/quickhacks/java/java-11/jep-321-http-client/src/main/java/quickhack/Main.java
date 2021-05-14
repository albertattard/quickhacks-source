package quickhack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static void printLinksInPage(final String pageLink) throws IOException, InterruptedException {
        final HttpClient client = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .followRedirects(HttpClient.Redirect.NORMAL)
                .connectTimeout(Duration.ofSeconds(5))
                .build();

        final HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(pageLink))
                .timeout(Duration.ofSeconds(5))
                .build();

        final HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        final List<URI> links = parseLinks(response.body());
        System.out.printf("Found %d links in page %s%n", links.size(), pageLink);
        for (final URI link : links) {
            System.out.printf(" > %s%n", link);
        }
    }

    private static List<URI> parseLinks(final String html) {
        final Document document = Jsoup.parse(html);
        return document.select("a[href]")
                .stream()
                .map(e -> e.attr("href"))
                .map(Main::toUri)
                .filter(URI::isAbsolute)
                .collect(Collectors.toList());
    }

    private static URI toUri(final String link) {
        try {
            return new URI(link);
        } catch (final URISyntaxException e) {
            throw new RuntimeException("Failed to parse link", e);
        }
    }

    public static void main(final String[] args) throws IOException, InterruptedException {
        printLinksInPage("https://albertattard.github.io/quickhacks/docs/java/java-11/");
    }
}

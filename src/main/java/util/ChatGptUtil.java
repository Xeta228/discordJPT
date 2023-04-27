package util;
import chatgptdto.ChatGptRequest;
import chatgptdto.ChatGptResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;


public class ChatGptUtil {

    private static Properties properties = new Properties();
    private static String API_KEY;

    public static String getAnswer(String query) throws IOException, InterruptedException {
        properties
                .load(new FileInputStream
                        ("src/main/resources/application.properties"));
        API_KEY = properties.getProperty("api_key");

        ChatGptRequest chatGptRequest =
                new ChatGptRequest("text-davinci-003",query,0.9,1900);
        ObjectMapper objectMapper = new ObjectMapper();
        String searchQuery = objectMapper.writeValueAsString(chatGptRequest);

        HttpRequest request = HttpRequest.newBuilder().uri(URI.create("https://api.openai.com/v1/completions"))
                .header("Content-Type","application/json")
                .header("Authorization","Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(searchQuery))
                .build();
        HttpClient client = HttpClient.newHttpClient();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
        ChatGptResponse chatGptResponse = objectMapper.readValue(response.body(),ChatGptResponse.class);
        return chatGptResponse.choices()[0].text();
    }


}

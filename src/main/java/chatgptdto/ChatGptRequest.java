package chatgptdto;

public record ChatGptRequest(String model, String prompt, double temperature, int max_tokens) {

}

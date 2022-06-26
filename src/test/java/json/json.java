package json;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;

import static org.assertj.core.api.Assertions.assertThat;


public class json {
    ClassLoader classLoader = json.class.getClassLoader();

    @Test
    void jsonJson() throws Exception {
        InputStream inputStream = classLoader.getResourceAsStream("json.json");
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(new InputStreamReader(inputStream));

        assertThat(jsonNode.withArray("questionnaire").findValue("gender").asText()).isEqualTo("female");
        assertThat(jsonNode.withArray("questionnaire").findValue("age").asInt()).isEqualTo(35);;
        assertThat(jsonNode.withArray("questionnaire").findValue("customer").asBoolean()).isEqualTo(true);
        assertThat(jsonNode.withArray("questionnaire").findValue("gender").asText()).isEqualTo("male");
        assertThat(jsonNode.withArray("questionnaire").findValue("age").asInt()).isEqualTo(40);;
        assertThat(jsonNode.withArray("questionnaire").findValue("ip_address").asBoolean()).isEqualTo(false);

    }
}

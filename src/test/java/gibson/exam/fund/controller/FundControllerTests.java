package gibson.exam.fund.controller;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class FundControllerTests {

    @Autowired
    private MockMvc mockMvc;

    public static MockMultipartFile getMockFile() {
                String content = "{\"id\":\"15887\",\"customer_id\":\"528\",\"load_amount\":\"$3318.47\",\"time\":\"2000-01-01T00:00:00Z\"}\n" +
                "{\"id\":\"30081\",\"customer_id\":\"154\",\"load_amount\":\"$1413.18\",\"time\":\"2000-01-01T01:01:22Z\"}\n";

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "test.txt",
                MediaType.TEXT_PLAIN_VALUE,
                content.getBytes()
        );
        return file;
    }

    @Test
    public void test_import_data() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart("/v1/fund/importData")
                .file(getMockFile()))
                .andExpect(status().isOk())
                .andReturn();

    }
}

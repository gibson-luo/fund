package gibson.exam.fund.tools;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class RecordReader {

    public static <T>List<T> loadObjectList(Class<T> type, MultipartFile file) throws IOException {
        InputStreamReader isr = new InputStreamReader(file.getInputStream(), StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isr);

        List<T> list = new ArrayList();
        String line;
        while((line = br.readLine()) != null){
            if(line.isEmpty())continue;
            ObjectMapper objectMapper = new ObjectMapper();
            T object = objectMapper.readValue(line, type);
            list.add(object);
        }
        br.close();
        return list;
    }
}

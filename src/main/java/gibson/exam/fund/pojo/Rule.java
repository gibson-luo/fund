package gibson.exam.fund.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rule implements Serializable {

    private Long id;

    private String description;

    private String key;

    private String value;

}

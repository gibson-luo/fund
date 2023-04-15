package gibson.exam.fund;

import gibson.exam.fund.rules.RuleEngine;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("gibson.exam.fund.mapper")
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		RuleEngine.loadRules();
	}

}

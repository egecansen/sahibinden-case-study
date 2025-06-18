import app.SahibindenCaseStudyApplication;
import base.BaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = SahibindenCaseStudyApplication.class)
@Import(BaseTest.class)
class SahibindenCaseStudyApplicationTests {

	@Autowired
	private BaseTest baseTest;

	@Test
	void contextLoads() {
		System.out.printf("hi");

	}

}

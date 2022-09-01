import com.example.OrderApplication;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {OrderApplication.class})
public class EncTest {
    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void encryptPwd() {
        String result = stringEncryptor.encrypt("machao");
        System.out.println("==================");
        System.out.println(result);//密文
        System.out.println("==================");
    }
}

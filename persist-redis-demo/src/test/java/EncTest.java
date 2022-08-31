import org.example.PersistRedisApplication;
import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {PersistRedisApplication.class})
public class EncTest {
    @Autowired
    StringEncryptor stringEncryptor;

    @Test
    public void encryptPwd() {
        String result = stringEncryptor.encrypt("114.132.94.160");
        System.out.println("==================");
        System.out.println(result);//密文
        System.out.println("==================");
    }
}

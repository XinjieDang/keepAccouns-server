
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
public class loginTest {
  PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
  @Test
    public void  Test(){
      String password=passwordEncoder.encode("123456");
       System.out.println(password);
   }
}

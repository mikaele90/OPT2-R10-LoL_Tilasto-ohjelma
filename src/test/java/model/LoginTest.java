package model;

import com.ryhma10.tilastoohjelma.model.Login;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    private Login login;

    @Test
    public void testProfileName() {
         login = new Login("tämäontesti2", "testpi2");
         String expectedProfileName = "tämäontesti2";
         assertEquals(expectedProfileName, login.getProfileName());
    }

}
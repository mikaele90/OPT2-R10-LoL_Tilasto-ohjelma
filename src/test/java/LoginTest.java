import com.ryhma10.tilastoohjelma.*;
import com.sun.javafx.robot.FXRobot;
import javafx.stage.Stage;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit.ApplicationTest;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import org.testfx.framework.junit.ApplicationTest;
import org.testfx.robot.Motion;

import static org.testfx.api.FxAssert.verifyThat;

public class LoginTest extends ApplicationTest {
    @Override public void start(Stage primaryStage) throws Exception {
        new MainApp().start(primaryStage);
    }



    @Test public void testTesting() {
    }

}
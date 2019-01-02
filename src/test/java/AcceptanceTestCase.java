import com.googlecode.yatspec.state.givenwhenthen.TestState;

import configuration.AppConfiguration;
import configuration.TestAppConfiguration;
import org.junit.After;

public class AcceptanceTestCase extends TestState {

    public ApplicationMain APPLICATION;
    public AppConfiguration DEFAULT_CONFIG = new TestAppConfiguration();

    @After
    public void tearDown() throws Exception {
        APPLICATION.stop();
    }

    public void andTheServerIsRunning() {
         APPLICATION = new ApplicationMain(DEFAULT_CONFIG);
         APPLICATION.start();
    }

    public void andTheServerIsRunningWithConfiguration(AppConfiguration config) {
        APPLICATION = new ApplicationMain(config);
        APPLICATION.start();
    }
}

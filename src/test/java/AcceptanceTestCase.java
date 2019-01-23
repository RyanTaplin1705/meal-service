import com.googlecode.yatspec.state.givenwhenthen.TestState;

import configuration.IConfiguration;
import configuration.TestConfiguration;
import org.junit.After;
import org.junit.Before;

public class AcceptanceTestCase extends TestState {

    public ApplicationMain APPLICATION = new ApplicationMain(new TestConfiguration());

    @Before
    public void setUp() throws Exception {
        APPLICATION.start();
    }

    @After
    public void tearDown() throws Exception {
        APPLICATION.stop();
    }

    public void theServerIsRunningWithConfiguration(IConfiguration config) {
        APPLICATION = new ApplicationMain(config);
        APPLICATION.start();
    }
}

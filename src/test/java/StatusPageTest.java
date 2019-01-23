import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.CapturedInputAndOutputs;
import configuration.TestConfiguration;
import org.junit.Test;
import org.junit.runner.RunWith;
import utils.HttpResponse;
import utils.TestHttpClient;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpecRunner.class)
public class StatusPageTest extends AcceptanceTestCase {

    private HttpResponse response;
    private TestConfiguration TEST_CONFIGURATION = new TestConfiguration();

    @Test
    public void givenServerHasAllSuccessfulProbesWeReturnOverallStatusOk() throws Exception {
        theServerIsRunningWithConfiguration(TEST_CONFIGURATION);

        when(weHitTheStatusEndpoint());

        thenTheResponseCodeIs(200);
        andTheResponseBodyIs("Something - OK");
    }

    private ActionUnderTest weHitTheStatusEndpoint() {
        String url = "http://localhost:" + TEST_CONFIGURATION.getProperties().port + "/status";
        return (interestingGivens, capturedInputAndOutputs) -> whenWeHitEndpoint(capturedInputAndOutputs, url);
    }

    private CapturedInputAndOutputs whenWeHitEndpoint(CapturedInputAndOutputs capturedInputAndOutputs, String url) throws IOException {
        response = TestHttpClient.getResponseFrom(url);
        capturedInputAndOutputs.add(String.format("Request from %s to %s", "User", "Server"), url);
        capturedInputAndOutputs.add(String.format("Response from %s to %s", "Server", "User"), response.body);
        return capturedInputAndOutputs;
    }

    private void thenTheResponseCodeIs(int statusCode) {
        assertThat(response.statusCode).isEqualTo(statusCode);
    }

    private void andTheResponseBodyIs(String body) throws IOException {
        assertThat(response.body).isEqualTo(body);
    }
}

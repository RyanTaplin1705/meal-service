import com.googlecode.yatspec.junit.SpecRunner;
import com.googlecode.yatspec.state.givenwhenthen.ActionUnderTest;
import com.googlecode.yatspec.state.givenwhenthen.CapturedInputAndOutputs;
import configuration.dependencies.ServerDependencies;
import configuration.dependencies.ServerDependency;
import configuration.dependencies.ServerDependencyBuilder;
import configuration.TestAppConfiguration;
import configuration.dependencies.StatusProbe;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import utils.HttpResponse;
import utils.TestHttpClient;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;

@RunWith(SpecRunner.class)
public class StatusPageTest extends AcceptanceTestCase {

    private HttpResponse response;
    private TestAppConfiguration TEST_CONFIGURATION = new TestAppConfiguration();

    @Test
    public void givenServerHasAllSuccessfulProbesWeReturnOverallStatusOk() throws Exception {
        givenServerHasAllSuccessfulProbes();
        andTheServerIsRunningWithConfiguration(TEST_CONFIGURATION);

        when(weHitTheStatusEndpoint());

        thenTheResponseCodeIs(200);
        andTheResponseBodyIs("Something - OK");
    }

    @Test
    public void givenServerHasFailedProbesWeReturnOverallStatusFail() throws Exception {
        givenServerHasFailedProbes();
        andTheServerIsRunningWithConfiguration(TEST_CONFIGURATION);

        when(weHitTheStatusEndpoint());

        thenTheResponseCodeIs(200);
        andTheResponseBodyIs("Something - FAIL");
        fail("Not implemented yet...");
    }

    @Test
    public void givenServerHasWarnProbesWeReturnOverallStatusWarn() throws Exception {
        givenServerHasWarnProbes();
        andTheServerIsRunningWithConfiguration(TEST_CONFIGURATION);

        when(weHitTheStatusEndpoint());

        thenTheResponseCodeIs(200);
        andTheResponseBodyIs("Something - WARN");
        fail("Not implemented yet...");
    }

    private void givenServerHasAllSuccessfulProbes() {
        ServerDependency serverDependencies = mock(ServerDependency.class);

        StatusProbe successfulProbe = createMockStatusProbeWithStatus("OK");

        Mockito.when(serverDependencies.toProbe()).thenReturn(successfulProbe);
        serverDependencies.name = "TestProbe";
        serverDependencies.connection = "testconnection:1177/endpoint";

        Optional<ServerDependencies> returnValue = ServerDependencyBuilder.create()
                .withDependency(serverDependencies)
                .withDependency(serverDependencies)
                .withDependency(serverDependencies)
                .build();
        TEST_CONFIGURATION.setDependencies(returnValue);
    }

    private void givenServerHasFailedProbes() {
        ServerDependency successfulServerDependencies = mock(ServerDependency.class);
        ServerDependency failedServerDependencies = mock(ServerDependency.class);

        StatusProbe successfulProbe = createMockStatusProbeWithStatus("OK");
        StatusProbe failedProbe = createMockStatusProbeWithStatus("FAIL");

        Mockito.when(successfulServerDependencies.toProbe()).thenReturn(successfulProbe);
        successfulServerDependencies.name = "TestProbe";
        successfulServerDependencies.connection = "testconnection:1177/endpoint";

        Mockito.when(failedServerDependencies.toProbe()).thenReturn(failedProbe);
        successfulServerDependencies.name = "FailedTestProbe";
        successfulServerDependencies.connection = "failconnection:8512/fail";

        Optional<ServerDependencies> returnValue = ServerDependencyBuilder.create()
                .withDependency(successfulServerDependencies)
                .withDependency(failedServerDependencies)
                .withDependency(successfulServerDependencies)
                .build();
        TEST_CONFIGURATION.setDependencies(returnValue);
    }

    private void givenServerHasWarnProbes() {
        ServerDependency successfulServerDependencies = mock(ServerDependency.class);
        ServerDependency warnServerDependencies = mock(ServerDependency.class);

        StatusProbe successfulProbe = createMockStatusProbeWithStatus("OK");
        StatusProbe warnProbe = createMockStatusProbeWithStatus("WARN");

        Mockito.when(successfulServerDependencies.toProbe()).thenReturn(successfulProbe);
        successfulServerDependencies.name = "TestProbe";
        successfulServerDependencies.connection = "testconnection:1177/endpoint";

        Mockito.when(warnServerDependencies.toProbe()).thenReturn(warnProbe);
        successfulServerDependencies.name = "FailedTestProbe";
        successfulServerDependencies.connection = "failconnection:8512/fail";

        Optional<ServerDependencies> returnValue = ServerDependencyBuilder.create()
                .withDependency(successfulServerDependencies)
                .withDependency(warnServerDependencies)
                .withDependency(successfulServerDependencies)
                .build();
        TEST_CONFIGURATION.setDependencies(returnValue);
    }

    private StatusProbe createMockStatusProbeWithStatus(String status) {
        StatusProbe probe = mock(StatusProbe.class);
        Mockito.when(probe.getStatus()).thenReturn(status);
        return probe;
    }

    private ActionUnderTest weHitTheStatusEndpoint() {
        String url = "http://localhost:" + DEFAULT_CONFIG.getPort() + "/status";
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

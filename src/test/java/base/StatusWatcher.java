package base;
import app.common.TestStatus;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestWatcher;
import utils.Printer;

import java.util.Objects;


public class StatusWatcher implements TestWatcher  {

    Printer log = new Printer(StatusWatcher.class);

    @Override
    public void testFailed(ExtensionContext context, Throwable cause) {
        log.warning("FAILED: " + context.getDisplayName());
        Objects.requireNonNull(context.getExecutionException().orElse(null)).printStackTrace();
        TestStatus.markFailed();
    }
    @Override
    public void testSuccessful(ExtensionContext context) {
        log.success("PASSED: " + context.getDisplayName());
        TestStatus.clear();
    }
    @Override
    public void testAborted(ExtensionContext context, Throwable cause) {
        TestStatus.clear();
    }

}

import context.ContextStore;

import java.io.IOException;

public class MainApp {

    public static void main(String[] args) {
        ContextStore.loadProperties("email.properties");
        if (Boolean.parseBoolean(ContextStore.get("send-report-email", "false"))) {
            EmailClient.sendReportEmail();
        }
    }

}
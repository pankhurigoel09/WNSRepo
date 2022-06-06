package utils;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.NetworkMode;

public class ExtentManager {
    private static ExtentReports extent;

    public synchronized static ExtentReports getReporter(String filePath) {
        if (extent == null) {
            extent = new ExtentReports(filePath, true, NetworkMode.ONLINE);

            extent
                    .addSystemInfo("Host Name", "Pankhuri")
                    .addSystemInfo("Environment", "QA");
        }

        return extent;
    }

    public synchronized static ExtentReports getReporter() {
        return extent;
    }
}

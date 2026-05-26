package base;

import org.testng.IRetryAnalyzer;

public class RetryTest implements IRetryAnalyzer {
	int count = 0;
	int retryLimit = 3;

	@Override
	public boolean retry(org.testng.ITestResult result) {
		if (count < retryLimit) {
			count++;
			return true;
		}
		return false;
	}

}

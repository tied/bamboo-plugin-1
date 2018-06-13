package com.mabl;

import com.atlassian.bamboo.build.test.TestCollectionResult;
import com.atlassian.bamboo.build.test.TestCollectionResultBuilder;
import com.atlassian.bamboo.build.test.TestReportProvider;
import com.atlassian.bamboo.results.tests.TestResults;
import com.atlassian.bamboo.resultsummary.tests.TestState;
import com.google.common.collect.Lists;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public class MablOutputProvider implements TestReportProvider {
    public Collection<TestResults> successfulTestResults = Lists.newArrayList();
    public Collection<TestResults> failedTestResults = Lists.newArrayList();

    @Override
    @NotNull
    public TestCollectionResult getTestCollectionResult() {
        TestCollectionResultBuilder builder = new TestCollectionResultBuilder();

        return builder.addSuccessfulTestResults(successfulTestResults)
                .addFailedTestResults(failedTestResults)
                .build();
    }

    public boolean addSuccess(TestResults testResults) {
        testResults.setState(TestState.SUCCESS);
        return successfulTestResults.add(testResults);
    }

    public boolean addSuccess(String className, String methodName, long duration, String appHref) {
        TestResults results = new TestResults(className, methodName, duration);
        results.setSystemOut(appHref);
        return addSuccess(results);
    }

    public boolean addFailure(TestResults testResults) {
        testResults.setState(TestState.FAILED);
        return failedTestResults.add(testResults);
    }

    public boolean addFailure(String className, String methodName, long duration, String appHref) {
        TestResults results = new TestResults(className, methodName, duration);
        results.setSystemOut(appHref);
        return addFailure(results);
    }
}

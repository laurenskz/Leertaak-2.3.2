package opgave4.test;

import junit.framework.Test;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class AllTests {

    public static void main(String[] args) {
        TestResult result = new TestResult();
        suite().run(result);
    }

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for default package");
        //$JUnit-BEGIN$
        suite.addTest(new TestSuite(TestClassifier.class));
        suite.addTest(new TestSuite(TestFileReader.class));
        suite.addTest(new TestSuite(TestItem.class));
        suite.addTest(new TestSuite(TestRepresentation.class));
        suite.addTest(new TestSuite(TestTree.class));
        suite.addTest(new TestSuite(TestTraining.class));
        //$JUnit-END$
        return suite;
    }
}

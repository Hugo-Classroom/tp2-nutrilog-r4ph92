package ca.qc.cegepst;

import org.junit.jupiter.api.Test;

public class TesterTest {
    @Test
    void testTester() {
        Tester tester = new Tester(new Pool(55000, 7.2,3,80,260));
        tester.test();
    }
}

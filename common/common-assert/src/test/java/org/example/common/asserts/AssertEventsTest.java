package org.example.common.asserts;

import org.example.common.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertEventsTest {

    @Test
    public void valueNullTest() {
        Assertions.assertThrows(NotFoundException.class, () -> AssertEvents.valueNull(null, ""));
    }

    @Test
    public void expressionTrueTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> AssertEvents.expressionTrue(null, ""));
    }

    @Test
    public void expressionTrueTest2() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> AssertEvents.expressionTrue(false, ""));
    }
}
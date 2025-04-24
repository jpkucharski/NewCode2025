package org.jpk.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class AdderService_ParameterizedTest {

    private static AdderService adderService;

    @BeforeAll
    public static void setUp() {
        adderService = new AdderService();
    }

    @ParameterizedTest
    @CsvSource({
            "1,3,4",
            "-4,6,2",
            "0,-2,-2",
            "-1,-3,-4"
    })
    public void Test1(int a, int b, int expected) {
        int actual = adderService.addTwoNumbers(a, b);
        Assertions.assertEquals(expected, actual);
    }
}
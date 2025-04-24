package org.jpk.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdderServiceTest {

    AdderService adderService;

    @BeforeEach
    void setUp() {
        adderService = new AdderService();
    }

    @Test
    public void addTwoNumbers1and2_ShouldReturn3() {
        int actual = adderService.addTwoNumbers(1, 2);
        int expected = 3;

        Assertions.assertEquals(expected, actual);
    }
}
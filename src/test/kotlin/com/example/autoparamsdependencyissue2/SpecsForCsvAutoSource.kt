package com.example.autoparamsdependencyissue2

import org.javaunit.autoparams.CsvAutoSource
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest

class SpecsForCsvAutoSource {

    @ParameterizedTest
    @CsvAutoSource("1, foo")
    fun sut_correctly_fills_arguments(value1: Int, value2: String?) {
        Assertions.assertEquals(1, value1)
        Assertions.assertEquals("foo", value2)
    }

}

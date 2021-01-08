package se.yrgo.isbnvalidator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ValidateISBNTest {

    private ValidateISBN validator = new ValidateISBN();

    @Test
    public void checkValid10DigitISBN() {
        boolean result = validator.checkISBN("0140441492");
        assertTrue(result, "First value");
        result = validator.checkISBN("0596009658");
        assertTrue(result, "Second value");
    }

    @Test
    public void checkInvalid10DigitISBN() {
        boolean result = validator.checkISBN("0140441493");
        assertFalse(result);
    }

    @Test
    public void nineDigitNotAllowed() {
        assertThrows(NumberFormatException.class, () -> {
            validator.checkISBN("123456789");
        });
    }

    @Test
    public void checkIfCharacters10DigitISBN() {
        assertThrows(NumberFormatException.class, () -> {
            validator.checkISBN("1bcdefghXj");
        }, "throws wrong X one 1 digit");
        assertThrows(NumberFormatException.class, () -> {
            validator.checkISBN("123456789j");
        }, "throws not X 9 digit");
    }

    @Test
    public void checkValidXend10DigitISBN() {
        boolean result = validator.checkISBN("069117654X");
        assertTrue(result);
    }
    
    @Test
    public void checkInvalidXend10DigitISBN() {
        boolean result = validator.checkISBN("069117655X");
        assertFalse(result);
    }

    @Test
    public void checkValid13DigitISBN() {
        boolean result = validator.checkISBN("9780691176543");
        assertTrue(result, "First value");
        result = validator.checkISBN("9780596009656");
        assertTrue(result, "Second value");
    }

    @Test
    public void checkInvalid13DigitISBN() {
          ValidateISBN validator = new ValidateISBN();
           boolean result = validator.checkISBN("9780691176544");
           assertFalse(result);
    }

}

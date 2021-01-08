package se.yrgo.isbnvalidator;

public class ValidateISBN {

    public boolean checkISBN(String isbn) {
        if (isbn.length() == 13) {
            int total = 0;
            for (int i = 0; i < 13; i++) {
                if (i % 2 == 0) {
                    total += Character.getNumericValue(isbn.charAt(i));
                } else {
                    total += Character.getNumericValue(isbn.charAt(i)) * 3;
                }
            }
            if (total % 10 == 0) {
                return true;
            } else {
                return false;
            }

        } else {
            if (isbn.length() != 10) {
                throw new NumberFormatException();
            }
            int total = 0;

            for (int i = 0; i < 10; i++) {
                // exercise-3
                if (!Character.isDigit(isbn.charAt(i))) {
                    // Check for x:
                    if (i == 9 && isbn.charAt(9) == 'X') {
                        total += 10;
                    } else {
                        throw new NumberFormatException();
                    }
                } else
                    total += Character.getNumericValue(isbn.charAt(i)) * (10 - i);
            }
            if (total % 11 == 0) {
                return true;
            } else {
                return false;
            }
        }
    }
}

package com.bernardino.desafio.domain;

import java.math.BigInteger;

public class UniqueDigit {

    private int result;

    public UniqueDigit(BigInteger number, Integer k) {
        this.result = calculateNewUniqueDigit(number, k);
    }

    public int getResult() {
        return this.result;
    }

    private int calculateNewUniqueDigit(BigInteger number, Integer k) {

        String numberString = String.valueOf(number).repeat(k);

        int uniqueDigit = this.sumOfDigits(numberString);

        while (uniqueDigit > 9) {
            uniqueDigit = this.sumOfDigits(String.valueOf(uniqueDigit));
        }

        return uniqueDigit;
    }

    private int sumOfDigits(String numberString) {
        int sum = 0;
        for (char digit : numberString.toCharArray()) {
            sum += Character.getNumericValue(digit);
        }

        return sum;
    }

}

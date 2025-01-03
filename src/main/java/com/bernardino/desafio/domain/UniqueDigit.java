package com.bernardino.desafio.domain;

import java.math.BigInteger;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "unique_digits")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UniqueDigit {

    @Id
    @Column(name = "id", updatable = false, nullable = false, columnDefinition = "varchar(36)")
    private UUID uuid = UUID.randomUUID();

    @Column(name = "result", nullable = false, columnDefinition = "int")
    private int result;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(name = "number", nullable = false, columnDefinition = "TEXT")
    private String number;

    @Column(name = "k", nullable = false, columnDefinition = "int")
    private int k;

    public UniqueDigit(BigInteger number, Integer k) {
        this.result = calculateNewUniqueDigit(number, k);
        this.number = number.toString();
        this.k = k;
    }

    public int getResult() {
        return this.result;
    }

    public void setUser(User user) {
        this.user = user;
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

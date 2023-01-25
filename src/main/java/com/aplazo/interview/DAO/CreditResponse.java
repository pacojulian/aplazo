package com.aplazo.interview.DAO;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CreditResponse {
    private Integer paymentNumber;
    private Double amount;
    private LocalDate paymentDate;
}

package com.aplazo.interview.DAO;

import lombok.Data;

@Data
public class CreditRequest {
    private Double amount;
    private Integer terms;
    private Double rate;
}

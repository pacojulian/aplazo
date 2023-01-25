package com.aplazo.interview.service;

import com.aplazo.interview.DAO.CreditRequest;
import com.aplazo.interview.DAO.CreditResponse;

import java.util.List;

public interface CreditRequestService {
      List<CreditResponse> calculateCredit(CreditRequest request);
}

package com.aplazo.interview.service.impl;

import com.aplazo.interview.DAO.CreditRequest;
import com.aplazo.interview.DAO.CreditResponse;
import com.aplazo.interview.service.CreditRequestService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreditRequestServiceImpl implements CreditRequestService {
    @Override
    public List<CreditResponse> calculateCredit(CreditRequest request) {
      try {
          LocalDate currentDate = LocalDate.now();
          // validate inputs
          List<CreditResponse> creditResponses = new ArrayList<>();
          double amountToBePaid = calculateAmount(request);
          for (int i = 0; i < request.getTerms(); i++) {
              CreditResponse creditResponse = new CreditResponse();
              creditResponse.setPaymentNumber(i);
              creditResponse.setPaymentDate(currentDate.plusWeeks(i));
              creditResponse.setAmount(amountToBePaid);
              creditResponses.add(creditResponse);
          }
          return creditResponses;
      }catch (Exception e){
          return  null;
      }

    }

    private Double calculateAmount(CreditRequest request) {
        Double weeklyRate = (request.getRate() / 100) / 52;
        return (request.getAmount() + (request.getAmount() * weeklyRate * request.getTerms())) / request.getTerms();
    }
}

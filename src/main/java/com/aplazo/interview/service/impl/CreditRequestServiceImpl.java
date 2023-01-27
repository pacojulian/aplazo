package com.aplazo.interview.service.impl;

import com.aplazo.interview.DAO.CreditRequest;
import com.aplazo.interview.DAO.CreditResponse;
import com.aplazo.interview.model.Data;
import com.aplazo.interview.repository.DataRepository;
import com.aplazo.interview.service.CreditRequestService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CreditRequestServiceImpl implements CreditRequestService {
    final
    DataRepository dataRepository;

    public CreditRequestServiceImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

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
          insertData(request,creditResponses);
          return creditResponses;
      }catch (Exception e){
          return  null;
      }

    }

    private Double calculateAmount(CreditRequest request) {
        Double weeklyRate = (request.getRate() / 100) / 52;
        return (request.getAmount() + (request.getAmount() * weeklyRate * request.getTerms())) / request.getTerms();
    }

    private void insertData(CreditRequest request, List<CreditResponse>   response){

        Gson gson = new Gson();
        Data data = new Data();
        data.setRequest(gson.toJson(request));
        data.setResponse(gson.toJson(response));
        dataRepository.save(data);
    }
}

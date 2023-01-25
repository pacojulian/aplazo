package com.aplazo.interview.service;

import com.aplazo.interview.DAO.CreditRequest;
import com.aplazo.interview.DAO.CreditResponse;
import com.aplazo.interview.service.impl.CreditRequestServiceImpl;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CreditRequestServiceImplTest {
    CreditRequestServiceImpl creditRequestService = new CreditRequestServiceImpl();

    @Test
    public void testCalculateCredit() {
        CreditRequest request = new CreditRequest();
        request.setAmount(5000.0);
        request.setRate(10.0);
        request.setTerms(52);
        List<CreditResponse> creditResponses = creditRequestService.calculateCredit(request);
        assertNotNull(creditResponses);
        assertEquals(52, creditResponses.size());
        assertEquals(105.76923076923077, creditResponses.get(0).getAmount(), 0.01);
    }

    @Test
    public void testCalculateCredit_InvalidInput() {
        CreditRequest request = new CreditRequest();
        request.setAmount(1000.0);
        request.setRate(5.0);
        request.setTerms(0);
        List<CreditResponse> creditResponses = creditRequestService.calculateCredit(request);
        assertEquals(0, creditResponses.size());

    }



}

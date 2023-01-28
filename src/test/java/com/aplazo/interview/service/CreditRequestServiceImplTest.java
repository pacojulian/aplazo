package com.aplazo.interview.service;

import static org.junit.jupiter.api.Assertions.*;

import com.aplazo.interview.DAO.CreditRequest;
import com.aplazo.interview.DAO.CreditResponse;
import com.aplazo.interview.repository.DataRepository;
import com.aplazo.interview.service.impl.CreditRequestServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

class CreditRequestServiceImplTest {
    @Mock
    private DataRepository dataRepository;

    @InjectMocks
    private CreditRequestServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void calculateCredit_validInput_returnsCorrectResponses() {
        CreditRequest request = new CreditRequest();
        request.setTerms(20);
        request.setRate(10.0);
        request.setAmount(1000.0);
        List<CreditResponse> expectedResponses = new ArrayList<>();
        for (int i = 0; i < request.getTerms(); i++) {
            CreditResponse response = new CreditResponse();
            response.setPaymentNumber(i);
            response.setAmount(51.92307692307693);
            expectedResponses.add(response);
        }

        List<CreditResponse> actualResponses = service.calculateCredit(request);

        assertNotNull(actualResponses);
        assertEquals(expectedResponses.size(), actualResponses.size());
        for (int i = 0; i < expectedResponses.size(); i++) {
            assertEquals(expectedResponses.get(i).getPaymentNumber(), actualResponses.get(i).getPaymentNumber());
            assertEquals(expectedResponses.get(i).getAmount(), actualResponses.get(i).getAmount());
        }
    }

    @Test
    void calculateCredit_nullInput_returnsNull() {
        CreditRequest request = null;
        assertNull(service.calculateCredit(request));
    }
}

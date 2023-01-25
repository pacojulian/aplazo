package com.aplazo.interview.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.aplazo.interview.DAO.CreditRequest;
import com.aplazo.interview.DAO.CreditResponse;
import com.aplazo.interview.service.CreditRequestService;

@ExtendWith(MockitoExtension.class)
public class CreditControllerTest {
    @Mock
    CreditRequestService service;

    @InjectMocks
    CreditController controller;

    CreditRequest request;
    List<CreditResponse> response;

    @BeforeEach
    public void setUp() {
        request = new CreditRequest();
        request.setAmount(1000.0);
        request.setRate(5.0);
        request.setTerms(12);

        CreditResponse creditResponse = new CreditResponse();
        creditResponse.setAmount(100.0);
        response = Arrays.asList(creditResponse);
    }

    @Test
    public void create_validParams_ok() {
        when(service.calculateCredit(request)).thenReturn(response);

        ResponseEntity<List<CreditResponse>> result = controller.create(request);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(response, result.getBody());
    }

    @Test
    public void create_invalidTerms_badRequest() {
        request.setTerms(3);
        ResponseEntity<String> result = controller.create(request);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Invalid Params", result.getBody());
    }

    @Test
    public void create_invalidRate_badRequest() {
        request.setRate(101.0);
        ResponseEntity<String> result = controller.create(request);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Invalid Params", result.getBody());
    }

    @Test
    public void create_invalidAmount_badRequest() {
        request.setAmount(-1.0);
        ResponseEntity<String> result = controller.create(request);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        assertEquals("Invalid Params", result.getBody());
    }

}

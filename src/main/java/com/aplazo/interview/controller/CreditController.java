package com.aplazo.interview.controller;

import com.aplazo.interview.DAO.CreditRequest;
import com.aplazo.interview.DAO.CreditResponse;
import com.aplazo.interview.service.CreditRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/credit")
public class CreditController {
    final CreditRequestService service;
    @Autowired
    public CreditController(CreditRequestService service) {
        this.service = service;
    }

    //Not adding something since the action will be the http method (POST)
    @PostMapping
    public ResponseEntity create(@RequestBody CreditRequest request) {

        if(validateParams(request)){
            List<CreditResponse> response = service.calculateCredit(request);
            if(response != null || !response.isEmpty()){
                return new ResponseEntity<>(response, HttpStatus.OK);
            }else {
                return  new ResponseEntity<>("error generating credit request ",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }else {
            return new ResponseEntity<>("Invalid Params", HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validateParams(CreditRequest creditRequest) {
        if (creditRequest.getTerms() > 52 || creditRequest.getTerms() < 4) return false;
        if (creditRequest.getRate() > 100.0 || creditRequest.getRate() < 1.0) return false;
        if (creditRequest.getAmount() < 1.0 || creditRequest.getAmount() > 999999.00) return false;
        return true;
    }

}

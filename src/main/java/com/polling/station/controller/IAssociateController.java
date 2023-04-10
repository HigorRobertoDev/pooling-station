package com.polling.station.controller;

import com.polling.station.dto.request.AssociateRequest;
import com.polling.station.dto.response.AssociateResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IAssociateController {

    ResponseEntity<AssociateResponse> createAssociate(AssociateRequest request);

    ResponseEntity<List<AssociateResponse>> getAssociates();

}

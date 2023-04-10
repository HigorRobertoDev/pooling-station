package com.polling.station.controller;

import com.polling.station.dto.request.AssociateRequest;
import com.polling.station.dto.response.AssociateResponse;
import com.polling.station.facade.IAssociateFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/associate")
public class AssociateController implements IAssociateController {

    @Autowired
    private IAssociateFacade associateFacade;

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Override
    public ResponseEntity<AssociateResponse> createAssociate(
            @RequestBody AssociateRequest request
    ) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.associateFacade.createAssociate(request)
        );
    }

    @GetMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Override
    public ResponseEntity<List<AssociateResponse>> getAssociates() {
        return ResponseEntity.status(HttpStatus.OK).body(
                this.associateFacade.getAssociates()
        );
    }
}

package com.polling.station.facade;

import com.polling.station.bo.AssociateBo;
import com.polling.station.dto.request.AssociateRequest;
import com.polling.station.dto.response.AssociateResponse;
import com.polling.station.model.Associate;
import com.polling.station.service.IAssociateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class AssociateFacade implements IAssociateFacade {

    @Autowired
    private IAssociateService associateService;

    @Autowired
    private AssociateBo associateBo;

    @Override
    public AssociateResponse createAssociate(AssociateRequest request) {

        log.info("Execução facade createAssociate");

        Associate associateToSave = this.associateBo.createAssociate(
                request
        );

        Associate associateSaved = this.associateService.createAssociate(
                associateToSave
        );

        return this.associateBo.createAssociateResponse(associateSaved);
    }

    @Override
    public List<AssociateResponse> getAssociates() {

        log.info("Execução facade getAssociates");

        List<Associate> associates = this.associateService.getAssociates();

        return this.associateBo.createListAssociateResponse(
                associates
        );
    }
}

package com.polling.station.facade;

import com.polling.station.dto.request.AssociateRequest;
import com.polling.station.dto.response.AssociateResponse;

import java.util.List;

public interface IAssociateFacade {

    AssociateResponse createAssociate(AssociateRequest request);

    List<AssociateResponse> getAssociates();

}

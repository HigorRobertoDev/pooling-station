package com.polling.station.service;

import com.polling.station.model.Associate;

import java.util.List;

public interface IAssociateService {

    Associate createAssociate(Associate associate);

    List<Associate> getAssociates();

    void verifyAssociate(Long codAssociate);

}

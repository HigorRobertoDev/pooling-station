package com.polling.station.service;

import com.polling.station.model.Associate;
import com.polling.station.repositories.IAssociateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociateService implements IAssociateService {

    @Autowired
    private IAssociateRepository associateRepository;

    @Override
    public Associate createAssociate(Associate associate) {
        return this.associateRepository.save(associate);
    }

    @Override
    public List<Associate> getAssociates() {
        return this.associateRepository.findAll();
    }

    @Override
    public void verifyAssociate(Long codAssociate) {

        Boolean exists = this.associateRepository.existsById(
                codAssociate
        );

        if (!exists) {
            throw new RuntimeException("Associate not found");
        }
    }
}

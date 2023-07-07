package com.polling.station.service;

import com.polling.station.api.webclient.ValidadorCpfApi;
import com.polling.station.api.webclient.response.ValidadorCepResponse;
import com.polling.station.common.exceptions.BusinessException;
import com.polling.station.common.exceptions.enums.BusinessErroEnum;
import com.polling.station.model.Associate;
import com.polling.station.repositories.IAssociateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssociateService implements IAssociateService {

    @Autowired
    private IAssociateRepository associateRepository;

    @Autowired
    private ValidadorCpfApi validadorCpfApi;

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
            throw new BusinessException(BusinessErroEnum.ASSOCIATE_NOT_FOUND);
        }
    }

    @Override
    public void verifyCpfAssociate(Long codAssociate) {

        Associate associate = this.associateRepository.getById(codAssociate);

        ResponseEntity<ValidadorCepResponse> response =
                validadorCpfApi.validarCep(associate.getCpf());

        if (response.getStatusCode().value() == 200) {
            ValidadorCepResponse validadorCepResponse = response.getBody();

            if (validadorCepResponse.getStatus().equals("UNABLE_TO_VOTE")) {
                throw new BusinessException(BusinessErroEnum.ASSOCIATE_UNABLE_TO_VOTE);
            }
        }
    }
}

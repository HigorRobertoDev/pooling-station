package com.polling.station.service;

import com.polling.station.api.webclient.ValidadorCpfApi;
import com.polling.station.api.webclient.response.ValidadorCepResponse;
import com.polling.station.common.exceptions.BusinessException;
import com.polling.station.common.exceptions.enums.BusinessErroEnum;
import com.polling.station.model.Associate;
import com.polling.station.repositories.IAssociateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
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

        log.info("Execução da service verifyCpfAssociate");

        Associate associate = this.associateRepository.getById(codAssociate);

        log.info("Execução da integração com a API de validação de cpf");

        ResponseEntity<ValidadorCepResponse> response =
                validadorCpfApi.validarCep(associate.getCpf());

        log.info("Fim da execução da integração com a API de validação de cpf");

        if (response.getStatusCode().value() == 200) {
            ValidadorCepResponse validadorCepResponse = response.getBody();

            if (validadorCepResponse.getStatus().equals("UNABLE_TO_VOTE")) {
                throw new BusinessException(BusinessErroEnum.ASSOCIATE_UNABLE_TO_VOTE);
            }
        }
    }
}

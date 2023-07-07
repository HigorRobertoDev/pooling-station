package com.polling.station.service;

import com.polling.station.common.exceptions.BusinessException;
import com.polling.station.common.exceptions.enums.BusinessErroEnum;
import com.polling.station.model.VotingAgenda;
import com.polling.station.repositories.IVotingAgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VotingAgendaService implements IVotingAgendaService {

    @Autowired
    private IVotingAgendaRepository iVotingAgendaRepository;

    @Override
    public VotingAgenda createVotingAgenda(VotingAgenda votingAgenda) {

        return this.iVotingAgendaRepository.save(votingAgenda);
    }

    @Override
    public List<VotingAgenda> getVotingAgenda() {

        return this.iVotingAgendaRepository.findAll();
    }

    @Override
    public VotingAgenda findByCodVotingAgenda(Long codVotingAgenda) {

        Optional<VotingAgenda> votingAgendaOptional =
                this.iVotingAgendaRepository.findById(codVotingAgenda);

        if (!votingAgendaOptional.isPresent()) {
            throw new BusinessException(BusinessErroEnum.VOTING_AGENDA_NOT_FOUND);
        }

        return votingAgendaOptional.get();
    }

    @Override
    public void verifyVotingAgenda(Long codVotingAgenda) {

        Boolean exists = this.iVotingAgendaRepository.existsById(
                codVotingAgenda
        );

        if (!exists) {
            throw new BusinessException(BusinessErroEnum.VOTING_AGENDA_NOT_FOUND);
        }
    }


}

package com.polling.station.service;

import com.polling.station.common.exceptions.BusinessException;
import com.polling.station.common.exceptions.enums.BusinessErroEnum;
import com.polling.station.model.VotingByAgenda;
import com.polling.station.model.VotingByAgendaPK;
import com.polling.station.repositories.IVotingByAgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VotingByAgendaService implements IVotingByAgendaService {

    @Autowired
    private IVotingByAgendaRepository votingByAgendaRepository;

    @Override
    public VotingByAgenda createVotingByAgenda(VotingByAgenda voting) {
        return this.votingByAgendaRepository.save(voting);
    }

    @Override
    public void verifyVotingByAgenda(VotingByAgendaPK pk) {

        Boolean exists = this.votingByAgendaRepository.existsById(
                pk
        );

        if (exists) {
            throw new BusinessException(BusinessErroEnum.VOTING_EXISTS);
        }
    }

    @Override
    public List<VotingByAgenda> resultVoteAgenda(Long codVotingAgenda) {

        return this.votingByAgendaRepository.findByPkCodVotingAgenda(
                codVotingAgenda
        );
    }

}

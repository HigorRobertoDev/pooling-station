package com.polling.station.service;

import com.polling.station.model.VotingByAgenda;
import com.polling.station.model.VotingByAgendaPK;

import java.util.List;

public interface IVotingByAgendaService {

    VotingByAgenda createVotingByAgenda(VotingByAgenda voting);

    void verifyVotingByAgenda(VotingByAgendaPK pk);

    List<VotingByAgenda> resultVoteAgenda(Long codVotingAgenda);

}

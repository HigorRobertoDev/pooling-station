package com.polling.station.service;

import com.polling.station.model.VotingAgenda;

import java.util.List;

public interface IVotingAgendaService {

    VotingAgenda createVotingAgenda(VotingAgenda votingAgenda);
    List<VotingAgenda> getVotingAgenda();

    VotingAgenda findByCodVotingAgenda(Long codVotingAgenda);

    void verifyVotingAgenda(Long codVotingAgenda);

}

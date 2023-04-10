package com.polling.station.service;

import com.polling.station.model.VotingSession;

import java.util.List;

public interface IVotingSessionService {

    VotingSession createVotingSession(VotingSession votingSession);
    List<VotingSession> getVotingSession();

    void verifyTimeSessionByCodVotingAgenda(Long codVotingAgenda);

}

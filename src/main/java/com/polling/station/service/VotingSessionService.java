package com.polling.station.service;

import com.polling.station.model.VotingSession;
import com.polling.station.repositories.IVotingSessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class VotingSessionService implements IVotingSessionService {

    @Autowired
    private IVotingSessionRepository iVotingSessionRepository;

    @Override
    public VotingSession createVotingSession(VotingSession votingSession) {

        return this.iVotingSessionRepository.save(
                votingSession
        );
    }

    @Override
    public List<VotingSession> getVotingSession() {

        return this.iVotingSessionRepository.findAll();
    }

    @Override
    public void verifyTimeSessionByCodVotingAgenda(Long codVotingAgenda) {

        LocalDateTime dateNow = LocalDateTime.now();

        VotingSession votingSession = this.iVotingSessionRepository.findByVotingAgendaCodVotingAgenda(
                codVotingAgenda
        );

        LocalDateTime dateRegistryVotingSession = votingSession.getRegistrationDate();

        LocalDateTime tempDateTime = LocalDateTime.from(dateRegistryVotingSession);

        long minutesOpen = tempDateTime.until(dateNow, ChronoUnit.MINUTES);

        if (minutesOpen > votingSession.getSessionOpeningTimeInMinutes().longValue()) {
            throw new RuntimeException("Tariff opening hours have already expired");
        }
    }

}

package com.polling.station.repositories;

import com.polling.station.model.VotingSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVotingSessionRepository extends JpaRepository<VotingSession, Long> {

    VotingSession findByVotingAgendaCodVotingAgenda(
            Long codVotingAgenda
    );

    Boolean existsByVotingAgendaCodVotingAgenda(
            Long codVotingAgenda
    );

}

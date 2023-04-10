package com.polling.station.repositories;

import com.polling.station.model.VotingAgenda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVotingAgendaRepository extends JpaRepository<VotingAgenda, Long> {
}

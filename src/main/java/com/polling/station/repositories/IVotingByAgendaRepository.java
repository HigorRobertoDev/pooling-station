package com.polling.station.repositories;

import com.polling.station.model.VotingByAgenda;
import com.polling.station.model.VotingByAgendaPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IVotingByAgendaRepository extends JpaRepository<VotingByAgenda, VotingByAgendaPK> {

    List<VotingByAgenda> findByPkCodVotingAgenda(Long codVotingAgenda);

}

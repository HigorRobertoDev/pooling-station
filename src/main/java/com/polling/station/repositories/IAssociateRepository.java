package com.polling.station.repositories;

import com.polling.station.model.Associate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAssociateRepository extends JpaRepository<Associate, Long> {
}

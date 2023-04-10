package com.polling.station.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "VOTING_AGENDA")
public class VotingAgenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_VOTING_AGENDA", nullable = false, precision = 4)
    private Long codVotingAgenda;

    @Column(name = "DESCRIPTION", nullable = false, length = 200)
    private String description;

    @Column(name = "REGISTRATION_DATE", nullable = false)
    private LocalDateTime registrationDate;

    @OneToOne(mappedBy = "votingAgenda")
    private VotingSession votingSession;

//    @OneToOne(mappedBy = "votingAgenda")
//    private VotingByAgenda votingByAgenda;

    @PrePersist
    private void prePersist() {

        this.registrationDate = LocalDateTime.now();
    }
}

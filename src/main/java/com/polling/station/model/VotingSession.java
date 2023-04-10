package com.polling.station.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "VOTING_SESSION")
public class VotingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COD_VOTING_SESSION", nullable = false, precision = 4)
    private Long codVotingSession;

    @Column(name = "SESSION_NAME", nullable = false, length = 200)
    private String sessionName;

    @Column(name = "SESSION_OPENING_TIME_IN_MINUTES", nullable = false, precision = 4)
    private Integer sessionOpeningTimeInMinutes;

    @Column(name = "REGISTRATION_DATE", nullable = false)
    private LocalDateTime registrationDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "COD_VOTING_AGENDA", referencedColumnName = "COD_VOTING_AGENDA")
    private VotingAgenda votingAgenda;

    @PrePersist
    private void prepersist() {
        this.registrationDate = LocalDateTime.now();
    }
}

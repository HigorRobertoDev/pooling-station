package com.polling.station.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "VOTING_BY_AGENDA")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotingByAgenda {

    @EmbeddedId
    private VotingByAgendaPK pk;

    @Column(name = "VALUE_VOTE", nullable = false, length = 1)
    private String valueVote;

//    @MapsId("COD_ASSOCIATE")
    @OneToOne
    @JoinColumn(name = "COD_ASSOCIATE", referencedColumnName = "COD_ASSOCIATE", nullable = false, updatable = false, insertable = false)
    private Associate associate;

//    @MapsId("COD_VOTING_AGENDA")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COD_VOTING_AGENDA", referencedColumnName = "COD_VOTING_AGENDA", nullable = false, updatable = false, insertable = false)
    private VotingAgenda votingAgenda;

}

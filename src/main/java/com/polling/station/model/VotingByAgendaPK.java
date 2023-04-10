package com.polling.station.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotingByAgendaPK implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "COD_ASSOCIATE", nullable = false, precision = 4)
    private Long codAssociate;

    @Column(name = "COD_VOTING_AGENDA", nullable = false, precision = 4)
    private Long codVotingAgenda;

}

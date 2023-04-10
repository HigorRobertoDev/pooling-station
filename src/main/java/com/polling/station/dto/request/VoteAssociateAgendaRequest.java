package com.polling.station.dto.request;

import com.polling.station.dto.YesOrNoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoteAssociateAgendaRequest {

    private Long codVotingAgenda;

    private Long codAssociate;

    private YesOrNoEnum valueVote;

}

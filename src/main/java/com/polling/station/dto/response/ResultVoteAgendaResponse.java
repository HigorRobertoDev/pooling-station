package com.polling.station.dto.response;

import com.polling.station.dto.YesOrNoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResultVoteAgendaResponse {

    private YesOrNoEnum valueVote;

    private Integer result;

}

package com.polling.station.bo;

import com.polling.station.dto.YesOrNoEnum;
import com.polling.station.dto.response.ResultVoteAgendaResponse;
import com.polling.station.model.VotingByAgenda;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class VotingByAgendaBo {

    public List<ResultVoteAgendaResponse> createListResultVoteAgendaResponse(
            List<VotingByAgenda> votingByAgendas
    ) {

        Long resultVoteAgendaResponseYes =
                Optional.ofNullable(votingByAgendas)
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .filter(voting -> voting.getValueVote().equals(YesOrNoEnum.S.getValue()))
                        .count();

        Long resultVoteAgendaResponseNo =
                Optional.ofNullable(votingByAgendas)
                        .orElseGet(Collections::emptyList)
                        .stream()
                        .filter(voting -> voting.getValueVote().equals(YesOrNoEnum.N.getValue()))
                        .count();

        return Arrays.asList(
                ResultVoteAgendaResponse.builder()
                        .valueVote(YesOrNoEnum.S)
                        .result(resultVoteAgendaResponseYes.intValue())
                        .build(),
                ResultVoteAgendaResponse.builder()
                        .valueVote(YesOrNoEnum.N)
                        .result(resultVoteAgendaResponseNo.intValue())
                        .build()
        );
    }

}

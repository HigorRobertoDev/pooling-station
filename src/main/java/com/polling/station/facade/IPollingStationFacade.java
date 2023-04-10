package com.polling.station.facade;

import com.polling.station.dto.request.VoteAssociateAgendaRequest;
import com.polling.station.dto.request.VotingAgendaRequest;
import com.polling.station.dto.request.VotingSessionRequest;
import com.polling.station.dto.response.ResultVoteAgendaResponse;
import com.polling.station.dto.response.VotingAgendaResponse;
import com.polling.station.dto.response.VotingSessionResponse;

import java.util.List;

public interface IPollingStationFacade {

    VotingAgendaResponse createVotingAgenda(VotingAgendaRequest request);
    List<VotingAgendaResponse> getVotingAgenda();

    VotingSessionResponse createVotingSession(VotingSessionRequest request);
    List<VotingSessionResponse> getVotingSession();

    void createVoteAssociateAgenda(VoteAssociateAgendaRequest request);

    List<ResultVoteAgendaResponse> resultVoteAgenda(Long codVotingAgenda);

}

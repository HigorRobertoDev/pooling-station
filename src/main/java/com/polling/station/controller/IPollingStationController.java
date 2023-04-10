package com.polling.station.controller;

import com.polling.station.dto.request.VoteAssociateAgendaRequest;
import com.polling.station.dto.request.VotingAgendaRequest;
import com.polling.station.dto.request.VotingSessionRequest;
import com.polling.station.dto.response.ResultVoteAgendaResponse;
import com.polling.station.dto.response.VotingAgendaResponse;
import com.polling.station.dto.response.VotingSessionResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IPollingStationController {

    ResponseEntity<VotingAgendaResponse> createVotingAgenda(VotingAgendaRequest request);
    ResponseEntity<List<VotingAgendaResponse>> getVotingAgenda();

    ResponseEntity<VotingSessionResponse> createVotingSession(VotingSessionRequest request);
    ResponseEntity<List<VotingSessionResponse>> getVotingSession();

    ResponseEntity<Void> createVoteAgenda(VoteAssociateAgendaRequest request);
    ResponseEntity<List<ResultVoteAgendaResponse>> resultVoteAgenda(Long codVotingAgenda);

}

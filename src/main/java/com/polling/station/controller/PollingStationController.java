package com.polling.station.controller;

import com.polling.station.dto.request.VoteAssociateAgendaRequest;
import com.polling.station.dto.request.VotingAgendaRequest;
import com.polling.station.dto.request.VotingSessionRequest;
import com.polling.station.dto.response.ResultVoteAgendaResponse;
import com.polling.station.dto.response.VotingAgendaResponse;
import com.polling.station.dto.response.VotingSessionResponse;
import com.polling.station.facade.IPollingStationFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/polling-station")
@Slf4j
public class PollingStationController implements IPollingStationController {

    @Autowired
    private IPollingStationFacade iPollingStationFacade;

    @PostMapping(
            value = "/voting-agenda",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Override
    public ResponseEntity<VotingAgendaResponse> createVotingAgenda(
            @RequestBody VotingAgendaRequest request
    ) {

        log.info("REQUEST Controller createVotingAgenda");

        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.iPollingStationFacade.createVotingAgenda(request)
        );
    }

    @GetMapping(
            value = "/voting-agenda",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Override
    public ResponseEntity<List<VotingAgendaResponse>> getVotingAgenda() {

        log.info("REQUEST Controller getVotingAgenda");

        return ResponseEntity.status(HttpStatus.OK).body(
                this.iPollingStationFacade.getVotingAgenda()
        );
    }

    @PostMapping(
            value = "/voting-session",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Override
    public ResponseEntity<VotingSessionResponse> createVotingSession(
            @RequestBody VotingSessionRequest request
    ) {

        log.info("REQUEST Controller createVotingSession");

        return ResponseEntity.status(HttpStatus.CREATED).body(
                this.iPollingStationFacade.createVotingSession(request)
        );
    }

    @GetMapping(
            value = "/voting-session",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Override
    public ResponseEntity<List<VotingSessionResponse>> getVotingSession() {

        log.info("REQUEST Controller getVotingSession");

        return ResponseEntity.status(HttpStatus.OK).body(
                this.iPollingStationFacade.getVotingSession()
        );
    }

    @PostMapping(
            value = "/vote-agenda",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Override
    public ResponseEntity<Void> createVoteAgenda(
            @RequestBody VoteAssociateAgendaRequest request
    ) {

        log.info("REQUEST Controller createVoteAgenda");

        this.iPollingStationFacade.createVoteAssociateAgenda(
                request
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping(
            value = "/vote-agenda-result/{codVotingAgenda}",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Override
    public ResponseEntity<List<ResultVoteAgendaResponse>> resultVoteAgenda(
            @PathVariable("codVotingAgenda") Long codVotingAgenda
    ) {

        log.info("REQUEST Controller resultVoteAgenda");

        return ResponseEntity.status(HttpStatus.OK).body(
                this.iPollingStationFacade.resultVoteAgenda(
                        codVotingAgenda
                )
        );
    }

}

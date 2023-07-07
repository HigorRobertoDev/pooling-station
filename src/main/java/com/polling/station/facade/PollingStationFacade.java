package com.polling.station.facade;

import com.polling.station.bo.VotingAgendaBo;
import com.polling.station.bo.VotingByAgendaBo;
import com.polling.station.bo.VotingSessionBo;
import com.polling.station.dto.request.VoteAssociateAgendaRequest;
import com.polling.station.dto.request.VotingAgendaRequest;
import com.polling.station.dto.request.VotingSessionRequest;
import com.polling.station.dto.response.ResultVoteAgendaResponse;
import com.polling.station.dto.response.VotingAgendaResponse;
import com.polling.station.dto.response.VotingSessionResponse;
import com.polling.station.model.VotingAgenda;
import com.polling.station.model.VotingByAgenda;
import com.polling.station.model.VotingSession;
import com.polling.station.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class PollingStationFacade implements IPollingStationFacade {

    @Autowired
    private IVotingAgendaService votingAgendaService;

    @Autowired
    private IVotingSessionService votingSessionService;

    @Autowired
    private IVotingByAgendaService votingByAgendaService;

    @Autowired
    private IAssociateService associateService;

    @Autowired
    private VotingAgendaBo votingAgendaBo;

    @Autowired
    private VotingSessionBo votingSessionBo;

    @Autowired
    private VotingByAgendaBo votingByAgendaBo;

    @Override
    public VotingAgendaResponse createVotingAgenda(VotingAgendaRequest request) {

        log.info("Execução facade createVotingAgenda");

        VotingAgenda votingAgendaToSave = this.votingAgendaBo.createVotingAgenda(
                request
        );

        VotingAgenda votingAgendaToSaved = this.votingAgendaService.createVotingAgenda(
                votingAgendaToSave
        );

        return this.votingAgendaBo.createVotingAgendaResponse(
                votingAgendaToSaved
        );
    }

    @Override
    public List<VotingAgendaResponse> getVotingAgenda() {

        log.info("Execução facade getVotingAgenda");

        List<VotingAgenda> votingAgendas = this.votingAgendaService.getVotingAgenda();

        return this.votingAgendaBo.createListVotingAgendaResponse(
                votingAgendas
        );
    }

    @Override
    public VotingSessionResponse createVotingSession(VotingSessionRequest request) {

        log.info("Execução facade createVotingSession");

        VotingAgenda votingAgenda = this.votingAgendaService.findByCodVotingAgenda(
                request.getCodVotingAgenda()
        );

        this.votingSessionService.validateExistsVotingAgendaInVotingSessionBy(
                request.getCodVotingAgenda()
        );

        VotingSession votingSessionToSave = this.votingSessionBo.createVotingSession(
                request,
                votingAgenda
        );

        VotingSession votingSessionToSaved = this.votingSessionService.createVotingSession(
                votingSessionToSave
        );

        return this.votingSessionBo.createVotingSessionResponse(
                votingSessionToSaved
        );
    }

    @Override
    public List<VotingSessionResponse> getVotingSession() {

        log.info("Execução facade getVotingSession");

        List<VotingSession> votingSessions = this.votingSessionService.getVotingSession();

        return this.votingSessionBo.createListVotingSessionResponse(
                votingSessions
        );
    }

    @Override
    public void createVoteAssociateAgenda(VoteAssociateAgendaRequest request) {

        log.info("Execução facade createVoteAssociateAgenda");

        this.votingAgendaService.verifyVotingAgenda(
                request.getCodVotingAgenda()
        );

        this.associateService.verifyAssociate(
                request.getCodAssociate()
        );

        this.associateService.verifyCpfAssociate(
                request.getCodAssociate()
        );

        VotingByAgenda votingToSave = this.votingAgendaBo.createVotingByAgenda(
                request
        );

        this.votingByAgendaService.verifyVotingByAgenda(
                votingToSave.getPk()
        );

        this.votingSessionService.verifyTimeSessionByCodVotingAgenda(
                request.getCodVotingAgenda()
        );

        this.votingByAgendaService.createVotingByAgenda(
                votingToSave
        );
    }

    @Override
    public List<ResultVoteAgendaResponse> resultVoteAgenda(Long codVotingAgenda) {

        log.info("Execução facade resultVoteAgenda");

        List<VotingByAgenda> votingByAgendas = this.votingByAgendaService.resultVoteAgenda(
                codVotingAgenda
        );

        return this.votingByAgendaBo.createListResultVoteAgendaResponse(
                votingByAgendas
        );
    }

}

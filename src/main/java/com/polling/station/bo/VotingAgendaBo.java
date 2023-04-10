package com.polling.station.bo;

import com.polling.station.dto.request.VoteAssociateAgendaRequest;
import com.polling.station.dto.request.VotingAgendaRequest;
import com.polling.station.dto.response.VotingAgendaResponse;
import com.polling.station.model.VotingAgenda;
import com.polling.station.model.VotingByAgenda;
import com.polling.station.model.VotingByAgendaPK;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VotingAgendaBo {

    @Autowired
    private ModelMapper modelMapper;

    public VotingAgenda createVotingAgenda(VotingAgendaRequest request) {

        return this.modelMapper.map(request, VotingAgenda.class);
    }

    public VotingAgendaResponse createVotingAgendaResponse(VotingAgenda votingAgenda) {

        return this.modelMapper.map(votingAgenda, VotingAgendaResponse.class);
    }

    public List<VotingAgendaResponse> createListVotingAgendaResponse(List<VotingAgenda> votingAgendas) {

        return Optional.ofNullable(votingAgendas)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(votingAgenda -> this.createVotingAgendaResponse(votingAgenda))
                .collect(Collectors.toList());
    }

    public VotingByAgenda createVotingByAgenda(VoteAssociateAgendaRequest request) {

        return VotingByAgenda.builder()
                .pk(
                        VotingByAgendaPK.builder()
                                .codAssociate(request.getCodAssociate())
                                .codVotingAgenda(request.getCodVotingAgenda())
                                .build()
                ).valueVote(request.getValueVote().getValue())
                .build();
    }

}

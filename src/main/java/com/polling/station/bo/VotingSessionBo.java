package com.polling.station.bo;

import com.polling.station.dto.request.VotingSessionRequest;
import com.polling.station.dto.response.VotingSessionResponse;
import com.polling.station.model.VotingAgenda;
import com.polling.station.model.VotingSession;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class VotingSessionBo {

    @Autowired
    private ModelMapper modelMapper;

    @Value("${session.opening.time.default}")
    private Integer sessionOpeningTimeDefault;

    public VotingSession createVotingSession(VotingSessionRequest request, VotingAgenda votingAgenda) {

        VotingSession votingSession = this.modelMapper.map(request, VotingSession.class);

        votingSession.setVotingAgenda(votingAgenda);

        if (Objects.isNull(request.getSessionOpeningTimeInMinutes())) {
            request.setSessionOpeningTimeInMinutes(this.sessionOpeningTimeDefault);
        }

        return votingSession;
    }

    public VotingSessionResponse createVotingSessionResponse(VotingSession votingSession) {

        return this.modelMapper.map(votingSession, VotingSessionResponse.class);
    }

    public List<VotingSessionResponse> createListVotingSessionResponse(List<VotingSession> votingSessions) {

        return Optional.ofNullable(votingSessions)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(votingSession -> this.createVotingSessionResponse(votingSession))
                .collect(Collectors.toList());
    }

}

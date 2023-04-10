package com.polling.station.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotingSessionRequest {

    private Long codVotingAgenda;

    private String sessionName;

    private Integer sessionOpeningTimeInMinutes;

}

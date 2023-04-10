package com.polling.station.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VotingSessionResponse {

    private Long codVotingSession;

    private Long codVotingAgenda;

    private String sessionName;

    private Integer sessionOpeningTimeInMinutes;

    private LocalDateTime registrationDate;
}

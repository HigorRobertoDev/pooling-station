package com.polling.station.common.exceptions.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum BusinessErroEnum {

    RUN_TIME_ERROR(0, "Ocorreu um erro inesperado", 500),
    CAMPO_TIPO_INVALIDO(1, "Formato do campo invalido", 400),
    ASSOCIATE_UNABLE_TO_VOTE(2, "This Associate is unable to vote", 400),
    VOTING_EXISTS(3, "Voting exists", 400),
    ASSOCIATE_NOT_FOUND(4, "Associate not found", 404),
    VOTING_AGENDA_NOT_FOUND(5, "Voting Agenda not found", 404),
    TARIFF_OPENING_HOURS_HAVE_ALREADY_EXPIRED(6, "Tariff opening hours have already expired", 400),
    CANT_NOT_CREATE_SESSION(7, "Cant not create session, because already exist a session with the same codVotingAgenda", 400);

    private Integer code;
    private String message;
    private Integer statusCode;

}

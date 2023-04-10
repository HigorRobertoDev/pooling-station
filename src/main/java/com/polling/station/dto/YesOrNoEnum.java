package com.polling.station.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum YesOrNoEnum {

    S("S"),
    N("N");

    String value;

}

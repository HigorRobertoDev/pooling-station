package com.polling.station.common.exceptions;

import com.polling.station.common.exceptions.enums.BusinessErroEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class BusinessException extends RuntimeException {

    private static final long serialVersionUID = -6425620447255243845L;

    private Long timeStamp;
    private Integer code;
    private Integer statusCode;
    private String message;
    private Object[] args;
    private BusinessErroEnum erroEnum;

    public BusinessException (BusinessErroEnum erroEnum, Object... args){
        this.code = erroEnum.getCode();
        this.statusCode = erroEnum.getStatusCode();
        this.message = erroEnum.getMessage();
        this.args = args;
        this.erroEnum = erroEnum;
    }

}

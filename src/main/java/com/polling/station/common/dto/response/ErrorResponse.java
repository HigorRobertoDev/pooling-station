package com.polling.station.common.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ErrorResponse extends Response {

    private Long timeStamp;
    private Integer code;
    private Object value;
    private Class<?> targetType;
    private String paths;

    public ErrorResponse(Long timeStamp, Integer code, Integer statusCode, String message){
        this.timeStamp = timeStamp;
        this.code = code;
        this.statusCode = statusCode;
        this.message = message;
    }

    public ErrorResponse(Integer statusCode, Integer code, String message, Long timeStamp,  Object value,
                         Class<?> targetType, String paths) {
        super(statusCode, message);
        this.timeStamp = timeStamp;
        this.code = code;
        this.value = value;
        this.targetType = targetType;
        this.paths = paths;
    }

}

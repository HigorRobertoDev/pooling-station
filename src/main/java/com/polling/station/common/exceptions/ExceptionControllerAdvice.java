package com.polling.station.common.exceptions;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.polling.station.common.dto.response.ErroFormularioResponse;
import com.polling.station.common.dto.response.ErrorResponse;
import com.polling.station.common.exceptions.enums.BusinessErroEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice {

    @Autowired
    private MessageSource messageSource;


    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ErroFormularioResponse>> handle(MethodArgumentNotValidException exception) {
        List<ErroFormularioResponse> dtos = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e -> {
            String mensagem = messageSource.getMessage(e, Locale.getDefault());
            ErroFormularioResponse erro = new ErroFormularioResponse(e.getField(), mensagem,
                    Instant.now().toEpochMilli(), 400,5);

            dtos.add(erro);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(dtos);
    }

    @ResponseBody
    @ExceptionHandler(BusinessException.class)
    ResponseEntity<ErrorResponse> handlerPessoaException(BusinessException e){

        String message = e.getMessage();// this.messageSource.getMessage(e.getMessage(), e.getArgs(),  LocaleContextHolder.getLocale());

        log.warn("Não foi possivel concluir a operação devido ao {}", message);

        return ResponseEntity.status(e.getStatusCode())
                .body(new ErrorResponse(Instant.now().toEpochMilli(), e.getCode(),
                        e.getStatusCode(), message));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleBindException(HttpMessageNotReadableException exception){
        exception.printStackTrace();

        String message = this.messageSource.getMessage(BusinessErroEnum.CAMPO_TIPO_INVALIDO.getMessage(), new Object[0],
                LocaleContextHolder.getLocale());

        if(exception.getCause() instanceof InvalidFormatException){
            InvalidFormatException invalidFormatException = (InvalidFormatException) exception.getCause();

            String fields = invalidFormatException.getPath().stream().map(JsonMappingException.Reference::getFieldName)
                    .filter(Objects::nonNull)
                    .collect(Collectors.joining(" -> "));

            ErrorResponse errorResponse = new ErrorResponse(BusinessErroEnum.CAMPO_TIPO_INVALIDO.getStatusCode(),
                    BusinessErroEnum.CAMPO_TIPO_INVALIDO.getCode(), message, Instant.now().toEpochMilli(),
                    null,
                    invalidFormatException.getTargetType(), fields);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(errorResponse);

        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

}

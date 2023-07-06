package com.polling.station.api.webclient;

import com.polling.station.api.webclient.response.ValidadorCepResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "${api.validacaocpf.name}", url = "${api.validacaocpf.baseurl}")
public interface ValidadorCpfApi {

    @GetMapping(value = "/users/{cpf}")
    ResponseEntity<ValidadorCepResponse> validarCep(
            @PathVariable("cpf") String cpf
    );

}

package com.polling.station.bo;

import com.polling.station.dto.request.AssociateRequest;
import com.polling.station.dto.response.AssociateResponse;
import com.polling.station.model.Associate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AssociateBo {

    @Autowired
    private ModelMapper modelMapper;

    public Associate createAssociate(AssociateRequest request) {

        return this.modelMapper.map(request, Associate.class);
    }

    public AssociateResponse createAssociateResponse(Associate associate) {

        return this.modelMapper.map(associate, AssociateResponse.class);
    }

    public List<AssociateResponse> createListAssociateResponse(List<Associate> associates) {

        return Optional.ofNullable(associates)
                .orElseGet(Collections::emptyList)
                .stream()
                .map(associate -> this.createAssociateResponse(associate))
                .collect(Collectors.toList());
    }

}

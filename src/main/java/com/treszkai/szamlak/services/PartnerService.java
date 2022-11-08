package com.treszkai.szamlak.services;

import com.treszkai.szamlak.data.pojo.PartnerDTO;

import java.util.List;
import java.util.Optional;

public interface PartnerService {

    List<PartnerDTO> findAll();

    Optional<PartnerDTO> findById(Long id);
}

package com.treszkai.szamlak.services;

import com.treszkai.szamlak.data.pojo.PartnerDTO;

import java.util.List;
import java.util.Optional;

public interface PartnerService {

    List<PartnerDTO> findAll();

    Optional<PartnerDTO> findById(Long id);

    // film mentése. A létrehozott filmet szertnénk visszaadni az EndPoint felé -> MovieDTO visszatérési értéke lesz az elején
    PartnerDTO create(PartnerDTO partnerDTO);

    // delete
    void delete(Long id);

    PartnerDTO update(PartnerDTO partnerDTO);
}

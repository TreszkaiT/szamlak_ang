package com.treszkai.szamlak.services;

import com.treszkai.szamlak.data.pojo.CurrencyDTO;
import com.treszkai.szamlak.data.pojo.PartnerDTO;
import com.treszkai.szamlak.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {

    List<CurrencyDTO> findAll();

    Optional<CurrencyDTO> findById(Long id);

    // film mentése. A létrehozott filmet szertnénk visszaadni az EndPoint felé -> MovieDTO visszatérési értéke lesz az elején
    CurrencyDTO create(CurrencyDTO currencyDTO);

    // delete
    void delete(Long id);

    CurrencyDTO update(CurrencyDTO currencyDTO);

}

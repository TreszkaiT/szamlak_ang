package com.treszkai.szamlak.services;

import com.treszkai.szamlak.data.pojo.CurrencyDTO;
import com.treszkai.szamlak.data.pojo.PartnerDTO;
import com.treszkai.szamlak.repository.CurrencyRepository;

import java.util.List;
import java.util.Optional;

public interface CurrencyService {

    List<CurrencyDTO> findAll();

    Optional<CurrencyDTO> findById(Long id);

}

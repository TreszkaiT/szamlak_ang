package com.treszkai.szamlak.services;

import com.treszkai.szamlak.data.pojo.CurrencyDTO;
import com.treszkai.szamlak.repository.CurrencyRepository;

import java.util.List;

public interface CurrencyService {

    List<CurrencyDTO> findAll();
}

package com.treszkai.szamlak.services.Impl;

import com.treszkai.szamlak.data.entity.Currency;
import com.treszkai.szamlak.data.pojo.CurrencyDTO;
import com.treszkai.szamlak.repository.CurrencyRepository;
import com.treszkai.szamlak.services.CurrencyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final CurrencyRepository currencyRepository;

    private final ModelMapper modelMapper;          // külső osztály így Configuration-on keresztül lesz ebből Bean  (Configuration/UtilConfiguration)

    public CurrencyServiceImpl(CurrencyRepository currencyRepository, ModelMapper modelMapper) {
        this.currencyRepository = currencyRepository;
        this.modelMapper = modelMapper;

        // kezdeti adatok most csak ide
        Currency currency = new Currency();
        currency.setId(1L);
        currency.setShortName("HUF");
        currency.setLongName("Magyar forintok");

        currencyRepository.save(currency);

    }

    @Override
    public List<CurrencyDTO> findAll() {
        List<Currency> currencyList = currencyRepository.findAll();

        return currencyList.stream()
                .map(currency -> modelMapper.map(currency, CurrencyDTO.class))
                .collect(Collectors.toList());
    }
}

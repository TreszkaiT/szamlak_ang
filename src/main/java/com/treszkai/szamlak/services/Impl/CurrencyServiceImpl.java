package com.treszkai.szamlak.services.Impl;

import com.treszkai.szamlak.data.entity.Currency;
import com.treszkai.szamlak.data.pojo.CurrencyDTO;
import com.treszkai.szamlak.repository.CurrencyRepository;
import com.treszkai.szamlak.services.CurrencyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
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

    @Override
    public Optional<CurrencyDTO> findById(Long id) {
        Optional<Currency> optionalMovie = currencyRepository.findById(id);           // Optional<Movie> :  a Move Entity-t becsomagolja egy Optional generikus metódusba. A nullkezeléssel kapcsolatos dolgokat meg tudunk oldani (if(null)-al így már nem kell fogalalkozni)... keresékeknél használható, hoy megtaláltunk-e valamit vagy sem
        return optionalMovie.map(movie -> modelMapper.map(movie, CurrencyDTO.class));
    }


}

package com.treszkai.szamlak.services.Impl;

import com.treszkai.szamlak.data.entity.Currency;
import com.treszkai.szamlak.data.pojo.CurrencyDTO;
import com.treszkai.szamlak.exception.CurrencyNotFoundException;
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
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);           // Optional<Currency> :  a Move Entity-t becsomagolja egy Optional generikus metódusba. A nullkezeléssel kapcsolatos dolgokat meg tudunk oldani (if(null)-al így már nem kell fogalalkozni)... keresékeknél használható, hoy megtaláltunk-e valamit vagy sem
        return optionalCurrency.map(currency -> modelMapper.map(currency, CurrencyDTO.class));
    }

    @Override
    public CurrencyDTO create(CurrencyDTO currencyDTO) {
        Currency currencyToSave = modelMapper.map(currencyDTO, Currency.class);
        currencyToSave.setId(null);
        Currency currency = currencyRepository.save(currencyToSave);
        return modelMapper.map(currency, CurrencyDTO.class);
    }

    @Override
    public CurrencyDTO update(CurrencyDTO currencyDTO) {
        Long id = currencyDTO.getId();                                             // létezik-e már ilyen id, mert ha nem, akkor új sort szúrna be, és ezt nem szeretnénk
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);           // currencyRepository-val megpróbálom megkerestetni az id-t

        if (optionalCurrency.isEmpty()){
            //throw new RuntimeException();                                       // ezt az Exceptiont nem kötelező elkapnunk, ezért jó  ->>így ezen a ponton megszakad a metódus futása... ez elkezd felfelé gyűrűzni, és valahol a Spring csinál belőle egy általános hibaüzenetet
            throw new CurrencyNotFoundException("Currency not found with id="+id);
        }

        Currency currencyToUpdate = modelMapper.map(currencyDTO, Currency.class);           // CurrencyDTO -> Currency
        Currency saveCurrency = currencyRepository.save(currencyToUpdate);                  // elmentem a Respository segítségével
        return modelMapper.map(saveCurrency, currencyDTO.getClass());                 // visszaadjuk CurrencyDTO-ként
    }

    @Override
    public void delete(Long id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);           // előbb megkeresem, hogy van-e ilyen film

        if (optionalCurrency.isPresent()){                                         // ha ez létezik ilyen movie
            Currency currencyDelete = optionalCurrency.get();                          // optionalCurrency.get(); :: kicsomagolom a get()-el
            currencyRepository.delete(currencyDelete);                              // Entity-t átadva törli
        } else {
            //throw new RuntimeException();                                     // ezen általános Except. helyett felveszek egy sajátot, az exception csomagba
            throw new CurrencyNotFoundException("Currency not found with id="+id);    // de itt így még csak a terminálban írja ki ezt a hibát, így csinálunk egy ControllerAdvice-t, ami elkapja a Controllerből kirepülő Exceptiont
        }
    }

    // tehát mégegyszer:
    // a Service lehív az adatbázishoz
    // a movieRepository által megkaptuk az összes filmet, ami benne van
    // utána a Steam-el a listát átalakítom movieDTO típusúvá, és ezt fogom visszaadni
    // így megszületett a findAll() metódus implementációja
}

package com.treszkai.szamlak.services.Impl;

import com.treszkai.szamlak.data.entity.Partner;
import com.treszkai.szamlak.data.pojo.PartnerDTO;
import com.treszkai.szamlak.exception.PartnerNotFoundException;
import com.treszkai.szamlak.repository.PartnerReposiroty;
import com.treszkai.szamlak.services.PartnerService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PartnerServiceImpl implements PartnerService {

    private final PartnerReposiroty partnerReposiroty;

    private final ModelMapper modelMapper;          // külső osztály így Configuration-on keresztül lesz ebből Bean  (Configuration/UtilConfiguration)

    public PartnerServiceImpl(PartnerReposiroty partnerReposiroty, ModelMapper modelMapper) {
        this.partnerReposiroty = partnerReposiroty;
        this.modelMapper = modelMapper;

        Partner partner = new Partner();
        partner.setId(1L)
                .setFirstName("Jani")
                .setLastName("Kiss")
                .setCountry("Hungary")
                .setZip(4400)
                .setCity("Nyíregyháza")
                .setDistrict(2)
                .setHouseNumber(112)
                .setHouseBuilding(2)
                .setHouseStaircase(3)
                .setHouseFloor(2)
                .setHouseDoor(1)
                .setBankAccNum1(123456)
                .setBankAccNum2(321654)
                .setBankAccNum3(987456)
                .setTaxNum1(123456)
                .setTaxNum2(546897)
                .setTaxNum3(524873)
                .setContact1Name("Géza")
                .setContact1Tel1(0630)
                .setContact1Tel2(321546)
                .setContact1Email("valaki@gmail.com")
                .setContact2Name("jani")
                .setContact2Tel1(0370)
                .setContact2Tel2(3265489)
                .setContact2Email("valaki2@gmail.com");

        this.partnerReposiroty.save(partner);
    }


    @Override
    public List<PartnerDTO> findAll() {
        List<Partner> partnerList = this.partnerReposiroty.findAll();

        return partnerList.stream()
                .map(partner -> modelMapper.map(partner, PartnerDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PartnerDTO> findById(Long id) {

        Optional<Partner> optionalPartner = partnerReposiroty.findById(id);
        return optionalPartner.map(partner -> modelMapper.map(partner, PartnerDTO.class));
    }

    @Override
    public PartnerDTO create(PartnerDTO partnerDTO) {
        Partner partnerToSave = modelMapper.map(partnerDTO, Partner.class);
        partnerToSave.setId(null);
        Partner partner = partnerReposiroty.save(partnerToSave);
        return modelMapper.map(partner, PartnerDTO.class);
    }

    @Override
    public PartnerDTO update(PartnerDTO partnerDTO) {
        Long id = partnerDTO.getId();                                             // létezik-e már ilyen id, mert ha nem, akkor új sort szúrna be, és ezt nem szeretnénk
        Optional<Partner> optionalPartner = partnerReposiroty.findById(id);           // currencyRepository-val megpróbálom megkerestetni az id-t

        if (optionalPartner.isEmpty()){
            //throw new RuntimeException();                                       // ezt az Exceptiont nem kötelező elkapnunk, ezért jó  ->>így ezen a ponton megszakad a metódus futása... ez elkezd felfelé gyűrűzni, és valahol a Spring csinál belőle egy általános hibaüzenetet
            throw new PartnerNotFoundException("Partner not found with id="+id);
        }

        Partner partnerToUpdate = modelMapper.map(partnerDTO, Partner.class);           // PartnerDTO -> Partner
        Partner savePartner = partnerReposiroty.save(partnerToUpdate);                  // elmentem a Respository segítségével
        return modelMapper.map(savePartner, partnerDTO.getClass());                 // visszaadjuk PartnerDTO-ként
    }

    @Override
    public void delete(Long id) {
        Optional<Partner> optionalPartner = partnerReposiroty.findById(id);           // előbb megkeresem, hogy van-e ilyen film

        if (optionalPartner.isPresent()){                                         // ha ez létezik ilyen movie
            Partner partnerDelete = optionalPartner.get();                          // optionalPartner.get(); :: kicsomagolom a get()-el
            partnerReposiroty.delete(partnerDelete);                              // Entity-t átadva törli
        } else {
            //throw new RuntimeException();                                     // ezen általános Except. helyett felveszek egy sajátot, az exception csomagba
            throw new PartnerNotFoundException("Partner not found with id="+id);    // de itt így még csak a terminálban írja ki ezt a hibát, így csinálunk egy ControllerAdvice-t, ami elkapja a Controllerből kirepülő Exceptiont
        }
    }

    // tehát mégegyszer:
    // a Service lehív az adatbázishoz
    // a movieRepository által megkaptuk az összes filmet, ami benne van
    // utána a Steam-el a listát átalakítom movieDTO típusúvá, és ezt fogom visszaadni
    // így megszületett a findAll() metódus implementációja
}

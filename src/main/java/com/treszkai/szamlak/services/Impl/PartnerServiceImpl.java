package com.treszkai.szamlak.services.Impl;

import com.treszkai.szamlak.data.entity.Partner;
import com.treszkai.szamlak.data.pojo.PartnerDTO;
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
}

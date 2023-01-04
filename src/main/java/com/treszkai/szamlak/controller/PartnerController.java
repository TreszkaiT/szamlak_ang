package com.treszkai.szamlak.controller;

import com.treszkai.szamlak.data.pojo.PartnerDTO;
import com.treszkai.szamlak.services.Impl.PartnerServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@RequestMapping(path = "/partner")
public class PartnerController {

    private PartnerServiceImpl partnerService;

    public PartnerController(PartnerServiceImpl partnerService) {
        this.partnerService = partnerService;
    }

//    @CrossOrigin(origins = "http://localhost:4200")
    // usereket olvas DB-ból: ez pedig már a UserDTO-t fog visszaadni, és a userService-t fog használni
    @RequestMapping(path = "/partner/all", method = RequestMethod.GET, produces = "application/json")      // RequestMapping: megmondjuk, hogy ez a metódus a GET/users  hívásra alkalmas;;;; azaz ez egy kérés Mappalése, ha bejön egy kérés, akkor azt le tudjuk mappelni erre a metódusra  CTRL+P metódusainak kilistázása
    public List<PartnerDTO> findAll(){
        return partnerService.findAll();
    }

//    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/partner/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<PartnerDTO> findById(@PathVariable(name = "id") Long identifier){                    // @PathVariable Long id ezzel a kapott /{id}-t tudjuk kezelni ezen metóduson belül    // ResponseEntity : a HTTP válaszon tudunk módosítani vele. 200,201... úgy hogy a MovieDTO-t becsomagoljuk ebbe a ResponseEntity generikus osztályba; HTTP headereket is bele tudunk még e mellett pakolni   ;;;  @Valid a MovieDTO-ban használja így a validációt  ;;  , BindingResult bindingResult  a validációs hibákat ebbe teszi bele, és mi azokat le tudjuk innen kérni

        Optional<PartnerDTO> optionalPartnerDTO = partnerService.findById(identifier);

        ResponseEntity<PartnerDTO> response;
        if (optionalPartnerDTO.isPresent()) {                                                     // ha van benne bármilyen érték, azaz megtalálltuk a filmet
            response = ResponseEntity.ok(optionalPartnerDTO.get());                               // akkor kiszedem az értékét
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);                  // amúgy meg nem találtam meg
        }

        return response;
//        return null;
    }

    //    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/partner", method = RequestMethod.PUT)
    public ResponseEntity<PartnerDTO> create(@RequestBody @Valid PartnerDTO partnerDTO, BindingResult bindingResult){      // ResponseEntity : a HTTP válaszon tudunk módosítani vele. 200,201... úgy hogy a CurrencyDTO-t becsomagoljuk ebbe a ResponseEntity generikus osztályba; HTTP headereket is bele tudunk még e mellett pakolni   ;;;  @Valid a CurrencyDTO-ban használja így a validációt  ;;  , BindingResult bindingResult  a validációs hibákat ebbe teszi bele, és mi azokat le tudjuk innen kérni
//        checkErrors(bindingResult);                                                             // alul alapmetódust írunk a validációs hibák lekezelésére

        PartnerDTO savedPartner = partnerService.create(partnerDTO);
        return ResponseEntity.status(HttpStatus.CREATED)                                        // beállítom a 201-es stásusz kódot: HttpStatus.CREATED
                .body(savedPartner);                                                                  // HTTP body beállítása
        //return movieService.create(currencyDTO);                                                 // itt meg meghívom az Implementáció create metódusát
    }
}

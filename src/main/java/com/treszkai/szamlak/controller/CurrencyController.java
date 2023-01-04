package com.treszkai.szamlak.controller;

import com.treszkai.szamlak.data.pojo.CurrencyDTO;
import com.treszkai.szamlak.exception.InvalidCurrencyException;
import com.treszkai.szamlak.services.CurrencyService;
import com.treszkai.szamlak.services.Impl.CurrencyServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Ebben vannak az EndPoint-ok
 * Entity-k és adatbázis művelet itt nem lehet!!! meg egyetlen más controller-ben sem!!
 * ezek a metódusok fogják lekezelni a kéréseket az egyes endpointokra pl. GET/movies  a https: //editor.swagger.io/ -oldalon GET/POST/....
 * a UserDTO bevezetésével ez az osztály már a UserService-en fog függni; az implementációt meg majd a Spring magától intézi, nekünk nem kell
 */

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(path = "/currency")
public class CurrencyController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyController.class);        // az osztályhoz elkérek egy loggert,

    public final CurrencyServiceImpl currencyService;

    public CurrencyController(CurrencyServiceImpl currencyService) {
        this.currencyService = currencyService;
    }

    //Cors policy
//    @CrossOrigin(origins = "http://localhost:3000")


//    @CrossOrigin(origins = "http://localhost:4200")
    // usereket olvas DB-ból: ez pedig már a UserDTO-t fog visszaadni, és a userService-t fog használni
    @RequestMapping(path = "/all", method = RequestMethod.GET, produces = "application/json")      // RequestMapping: megmondjuk, hogy ez a metódus a GET/users  hívásra alkalmas;;;; azaz ez egy kérés Mappalése, ha bejön egy kérés, akkor azt le tudjuk mappelni erre a metódusra  CTRL+P metódusainak kilistázása
    public List<CurrencyDTO> findAll(){
        return currencyService.findAll();
    }

//    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CurrencyDTO> findById(@PathVariable(name = "id") Long identifier){                    // @PathVariable Long id ezzel a kapott /{id}-t tudjuk kezelni ezen metóduson belül    // ResponseEntity : a HTTP válaszon tudunk módosítani vele. 200,201... úgy hogy a CurrencyDTO-t becsomagoljuk ebbe a ResponseEntity generikus osztályba; HTTP headereket is bele tudunk még e mellett pakolni   ;;;  @Valid a CurrencyDTO-ban használja így a validációt  ;;  , BindingResult bindingResult  a validációs hibákat ebbe teszi bele, és mi azokat le tudjuk innen kérni

        Optional<CurrencyDTO> optionalCurrencyDTO = currencyService.findById(identifier);

        ResponseEntity<CurrencyDTO> response;
        if (optionalCurrencyDTO.isPresent()) {                                                     // ha van benne bármilyen érték, azaz megtalálltuk a filmet
            response = ResponseEntity.ok(optionalCurrencyDTO.get());                               // akkor kiszedem az értékét
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);                  // amúgy meg nem találtam meg
        }

        return response;
//        return null;
    }

//    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<CurrencyDTO> create(@RequestBody @Valid CurrencyDTO currencyDTO, BindingResult bindingResult){      // ResponseEntity : a HTTP válaszon tudunk módosítani vele. 200,201... úgy hogy a CurrencyDTO-t becsomagoljuk ebbe a ResponseEntity generikus osztályba; HTTP headereket is bele tudunk még e mellett pakolni   ;;;  @Valid a CurrencyDTO-ban használja így a validációt  ;;  , BindingResult bindingResult  a validációs hibákat ebbe teszi bele, és mi azokat le tudjuk innen kérni
        checkErrors(bindingResult);                                                             // alul alapmetódust írunk a validációs hibák lekezelésére

        CurrencyDTO savedCurrency = currencyService.create(currencyDTO);
        return ResponseEntity.status(HttpStatus.CREATED)                                        // beállítom a 201-es stásusz kódot: HttpStatus.CREATED
                .body(savedCurrency);                                                                  // HTTP body beállítása
        //return movieService.create(currencyDTO);                                                 // itt meg meghívom az Implementáció create metódusát
    }

    // id alapján törlés
    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Long id){                                  // <Void>  : üres response Entity-t ad vissza, így ezen tudjuk állítgatni a HTTP status kódot
        currencyService.delete(id);
        return ResponseEntity.noContent().build();                                              // 204-es HTTP status code
    }

    // frissítés id alapján
    @RequestMapping(path = "/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<CurrencyDTO> update(@RequestBody @Valid CurrencyDTO currencyDTO, BindingResult bindingResult){                     // @RequestBody CurrencyDTO movieDTO:  a Request Body-ban várja az infót
        checkErrors(bindingResult);                                                             // alul alapmetódust írunk a validációs hibák lekezelésére

        CurrencyDTO updatedCurrency = currencyService.update(currencyDTO);
        return ResponseEntity.ok(updatedCurrency);                                                 // 200-as hiba
    }


    //-----------------------------------------------------------------------------------------
    // validáció hibáinak kiíratásához egy segésmetódus felülre POST-ba
    private void checkErrors(BindingResult bindingResult){
        LOGGER.info("bindingResult has errors = {}", bindingResult.hasErrors());                // a stringet kiírja {}-ebbe meg behettesíti ezt bindingResult.hasErrors() hogy van-e hiba
        LOGGER.info("errors = {}", bindingResult.getAllErrors());                               // kiírjuk az összes hibát, amit csak talál   a hibaválaszt a response package-be tesszük bele
        // itt dobunk egy Exceptiont, mely a controllerAdvice-ben lévő hibát fogja meg,, ehhez kell egy Invalid

        if(bindingResult.hasErrors()){
            List<String> messages = new ArrayList<>();                                          // listát hozok létre

            for(FieldError fieldError : bindingResult.getFieldErrors()){
                messages.add(fieldError.getField() + " - " + fieldError.getDefaultMessage());  // majd kiírja, melyik field, és feltöltöm a hibákkal
            }

            throw new InvalidCurrencyException("Invalid movie", messages);                         // ha hibát látok, akkor dobok egy Exceptiont, ami a hiba okát is tartalmazza
        }
    }
}

package com.treszkai.szamlak.controller;

import com.treszkai.szamlak.data.pojo.CurrencyDTO;
import com.treszkai.szamlak.services.CurrencyService;
import com.treszkai.szamlak.services.Impl.CurrencyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Ebben vannak az EndPoint-ok
 * Entity-k és adatbázis művelet itt nem lehet!!! meg egyetlen más controller-ben sem!!
 * ezek a metódusok fogják lekezelni a kéréseket az egyes endpointokra pl. GET/movies  a https: //editor.swagger.io/ -oldalon GET/POST/....
 * a UserDTO bevezetésével ez az osztály már a UserService-en fog függni; az implementációt meg majd a Spring magától intézi, nekünk nem kell
 */

@RestController
public class CurrencyController {

    public final CurrencyServiceImpl currencyService;

    public CurrencyController(CurrencyServiceImpl currencyService) {
        this.currencyService = currencyService;
    }

    //Cors policy
//    @CrossOrigin(origins = "http://localhost:3000")


    @CrossOrigin(origins = "http://localhost:4200")
    // usereket olvas DB-ból: ez pedig már a UserDTO-t fog visszaadni, és a userService-t fog használni
    @RequestMapping(path = "/currency/all", method = RequestMethod.GET, produces = "application/json")      // RequestMapping: megmondjuk, hogy ez a metódus a GET/users  hívásra alkalmas;;;; azaz ez egy kérés Mappalése, ha bejön egy kérés, akkor azt le tudjuk mappelni erre a metódusra  CTRL+P metódusainak kilistázása
    public List<CurrencyDTO> findAll(){
        return currencyService.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(path = "/currency/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<CurrencyDTO> findById(@PathVariable(name = "id") Long identifier){                    // @PathVariable Long id ezzel a kapott /{id}-t tudjuk kezelni ezen metóduson belül    // ResponseEntity : a HTTP válaszon tudunk módosítani vele. 200,201... úgy hogy a MovieDTO-t becsomagoljuk ebbe a ResponseEntity generikus osztályba; HTTP headereket is bele tudunk még e mellett pakolni   ;;;  @Valid a MovieDTO-ban használja így a validációt  ;;  , BindingResult bindingResult  a validációs hibákat ebbe teszi bele, és mi azokat le tudjuk innen kérni

        Optional<CurrencyDTO> optionalMovieDTO = currencyService.findById(identifier);

        ResponseEntity<CurrencyDTO> response;
        if (optionalMovieDTO.isPresent()) {                                                     // ha van benne bármilyen érték, azaz megtalálltuk a filmet
            response = ResponseEntity.ok(optionalMovieDTO.get());                               // akkor kiszedem az értékét
        } else {
            response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);                  // amúgy meg nem találtam meg
        }

        return response;
//        return null;
    }

}

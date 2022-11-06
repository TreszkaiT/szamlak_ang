package com.treszkai.szamlak.controller;

import com.treszkai.szamlak.data.pojo.CurrencyDTO;
import com.treszkai.szamlak.services.CurrencyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Ebben vannak az EndPoint-ok
 * Entity-k és adatbázis művelet itt nem lehet!!! meg egyetlen más controller-ben sem!!
 * ezek a metódusok fogják lekezelni a kéréseket az egyes endpointokra pl. GET/movies  a https: //editor.swagger.io/ -oldalon GET/POST/....
 * a UserDTO bevezetésével ez az osztály már a UserService-en fog függni; az implementációt meg majd a Spring magától intézi, nekünk nem kell
 */

@RestController
public class CurrencyController {

    public final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    //Cors policy
//    @CrossOrigin(origins = "http://localhost:3000")


    @CrossOrigin(origins = "http://localhost:4200")
    // usereket olvas DB-ból: ez pedig már a UserDTO-t fog visszaadni, és a userService-t fog használni
    @RequestMapping(path = "/currency/all", method = RequestMethod.GET,produces = "application/json")      // RequestMapping: megmondjuk, hogy ez a metódus a GET/users  hívásra alkalmas;;;; azaz ez egy kérés Mappalése, ha bejön egy kérés, akkor azt le tudjuk mappelni erre a metódusra  CTRL+P metódusainak kilistázása
    public List<CurrencyDTO> findAll(){
        return currencyService.findAll();
    }
}

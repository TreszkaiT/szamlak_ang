package com.treszkai.szamlak.repository;

import com.treszkai.szamlak.data.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyRepository extends JpaRepository<Currency, Long> {
}

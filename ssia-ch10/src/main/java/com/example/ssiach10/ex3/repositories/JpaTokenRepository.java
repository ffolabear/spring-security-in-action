package com.example.ssiach10.ex3.repositories;

import com.example.ssiach10.ex3.entities.Token;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTokenRepository extends JpaRepository<Token, Integer> {

    Optional<Token> findTokenByIdentifier(String identifier);

}

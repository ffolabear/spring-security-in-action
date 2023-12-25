package org.example.ssiach16ex6.service;


import jakarta.annotation.security.RolesAllowed;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

@Service
public class NameService {

//    @RolesAllowed("ROLE_ADMIN")
    @Secured("ROLE_ADMIN")
    public String getName() {
        return "Fantastico";
    }

}

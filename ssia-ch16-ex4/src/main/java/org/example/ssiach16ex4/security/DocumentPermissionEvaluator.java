package org.example.ssiach16ex4.security;

import java.io.Serializable;
import org.example.ssiach16ex4.model.Document;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class DocumentPermissionEvaluator implements PermissionEvaluator {

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        Document document = (Document) targetDomainObject;
        String permissionString = (String) permission;

        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(permissionString));
        System.out.println("=======================================");
        System.out.println("document = " + document);
        System.out.println("permissionString = " + permissionString);
        System.out.println("authentication = " + authentication);
        System.out.println("=======================================");
        return isAdmin || document.getOwner().equals(authentication.getName());
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
            Object permission) {
        return false;
    }
}

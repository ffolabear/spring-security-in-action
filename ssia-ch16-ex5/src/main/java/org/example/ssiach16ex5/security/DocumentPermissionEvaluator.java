package org.example.ssiach16ex5.security;

import java.io.Serializable;
import org.example.ssiach16ex5.model.Document;
import org.example.ssiach16ex5.repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class DocumentPermissionEvaluator implements PermissionEvaluator {

    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
            Object permission) {
        String code = targetId.toString();
        Document document = documentRepository.findDocument(code);
        String permissionString = (String) permission;

        boolean isAdmin = authentication.getAuthorities()
                .stream()
                .anyMatch(a -> a.getAuthority().equals(permissionString));
        return isAdmin || document.getOwner().equals(authentication.getName());
    }
}

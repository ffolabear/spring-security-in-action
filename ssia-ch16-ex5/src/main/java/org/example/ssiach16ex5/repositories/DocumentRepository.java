package org.example.ssiach16ex5.repositories;

import java.util.Map;

import org.example.ssiach16ex5.model.Document;
import org.springframework.stereotype.Repository;

@Repository
public class DocumentRepository {

    private final Map<String, Document> documents = Map.of(
            "abc123", new Document("natalie"),
            "qwe123", new Document("natalie"),
            "asd123", new Document("emma")
            );


    public Document findDocument(String code) {
        return documents.get(code);
    }

}

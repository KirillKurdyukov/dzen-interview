package org.example;

import java.util.List;

public interface DocumentDao<Document> {

    void addDocument(Document document);

    List<Document> findAll();
}

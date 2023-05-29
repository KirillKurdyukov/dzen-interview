package org.example.impl;

import org.example.DocumentDao;

import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

public class DocumentDaoImpl<Document> implements DocumentDao<Document> {

    private final ConcurrentLinkedQueue<Document> dao = new ConcurrentLinkedQueue<>();

    @Override
    public void addDocument(Document document) {
        dao.add(document);
    }

    @Override
    public List<Document> findAll() {
        return dao.stream().toList();
    }
}

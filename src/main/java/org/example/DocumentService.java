package org.example;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DocumentService<Document, User> {

    private final Scorer<Document, User> scorer;
    private final DocumentDao<Document> documentDao;

    public DocumentService(
            Scorer<Document, User> scorer,
            DocumentDao<Document> documentDao
    ) {
        this.scorer = scorer;
        this.documentDao = documentDao;
    }

    public void addDocument(Document document) {
        documentDao.addDocument(document);
    }

    public void addDocuments(List<Document> documents) {
        documents.forEach(this::addDocument);
    }

    public List<Document> getPreferDocuments(User user, int n) { // m * log n
        Comparator<Data<Document>> dataComparator = Comparator.comparing(Data::priority);

        return documentDao.findAll().stream()
                .map(document -> new Data<>(scorer.getScore(document, user), document))
                .sorted(dataComparator.reversed())
                .limit(n)
                .map(Data::document)
                .collect(Collectors.toList());
    }

    public record Data<Document>(
            double priority,
            Document document
    ) {}
}

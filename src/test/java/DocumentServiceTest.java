import org.example.DocumentService;
import org.example.impl.DocumentDaoImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DocumentServiceTest {

    private final DocumentService<Document, User> documentService = new DocumentService<>(
            (document, user) -> document.id() * user.id(),
            new DocumentDaoImpl<>()
    );


    @Test
    void simpleTest() {
        List<Document> documents = List.of(
                new Document("1", 1),
                new Document("2", 2),
                new Document("3", 3)
        );

        User user = new User(2, "Kirill");

        documentService.addDocuments(documents);

        List<Document> userDocuments = documentService.getPreferDocuments(user, 2);

        Assertions.assertEquals(2, userDocuments.size());

        Assertions.assertEquals(List.of(
                new Document("3", 3),
                new Document("2", 2)
        ), userDocuments);
    }
}

package org.example;

public interface Scorer<TDocument, TUser> {

    double getScore(TDocument document, TUser user);
}
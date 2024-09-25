package org.example.liquibaseexample.core.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserType {

    ADMIN("ADMIN")
    , USER("USER")
    , ABRAKADABRA("ABRAKADABRA")
    ;

    private final String typeString;
}

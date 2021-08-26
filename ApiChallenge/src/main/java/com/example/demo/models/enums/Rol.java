package com.example.demo.models.enums;

import com.example.demo.exceptions.NotValidRolException;

public enum Rol {
    client, admin;

    public static Rol getRol(String rol) throws NotValidRolException {

        switch (rol) {
            case "client":
                return client;

            case "admin":
                return admin;

            default:
                throw new NotValidRolException("Invalid rol");
        }
    }
}

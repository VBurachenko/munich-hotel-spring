package com.burachenko.munichhotel;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Main {

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("customer"));
    }

//    $2a$10$oYi8Gnfl5kZgcSZQxtx72.6bkZ9uPN0dTEfGMn2bJBnan84yR56tS
}

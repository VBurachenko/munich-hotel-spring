package com.burachenko.munichhotel.enumeration;

public enum UserRole {

    GUEST, CUSTOMER, ADMIN, MODER;

    public static String[] getAuthoritedRoles(){
        String [] roles = new String[UserRole.values().length];
        final UserRole[] values = UserRole.values();
        for (int i = 1; i < values.length; i++) {
            roles[i] = UserRole.values()[i].toString();
        }
        return roles;
    }

}

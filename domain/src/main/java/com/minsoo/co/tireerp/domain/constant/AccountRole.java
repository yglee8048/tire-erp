package com.minsoo.co.tireerp.domain.constant;

import java.util.Arrays;
import java.util.List;

public enum AccountRole {
    ROOT, ADMIN, CLIENT, GUEST;

    public boolean isAdmin() {
        final List<AccountRole> ADMIN_ROLES = Arrays.asList(ROOT, ADMIN);
        return ADMIN_ROLES.contains(this);
    }
}

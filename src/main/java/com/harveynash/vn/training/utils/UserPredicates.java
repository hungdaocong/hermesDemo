package com.harveynash.vn.training.utils;

import com.harveynash.vn.training.model.QUser;
import com.mysema.query.types.Predicate;

public final class UserPredicates {

    private UserPredicates() {
    }

    public static Predicate usernameEquals(final String username) {
        return QUser.user.username.equalsIgnoreCase(username);
    }

    public static Predicate firstNameAndLastNameEquals(final String firstName, final String lastName) {
        return QUser.user.firstName.eq(firstName).and(QUser.user.lastName.eq(lastName));
    }

    public static Predicate firstNameOrLastNameEquals(final String firstName, final String lastName) {
        return QUser.user.firstName.eq(firstName).or(QUser.user.lastName.eq(lastName));
    }
}

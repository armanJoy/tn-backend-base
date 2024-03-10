package com.technonext.transport.model.user;

public enum RoleEnum {

    ADMIN(1l),
    OFFICER(2l),
    MAINTAINER(3l),
    VIEWER(4l);

    private final Long value;

    RoleEnum(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }
}

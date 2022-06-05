package com.yazy.rest.Logic;

import java.util.UUID;

public class MyId {
    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MyId{" +
                "id=" + id +
                '}';
    }
}

package com.dbolshak.prototype.dao.domain;

public class Item {

    private final long id;
    private final String content;

    public Item(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }
}

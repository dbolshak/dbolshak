package com.dbolshak.prototype.dao.model.domain

class Item {
    long id
    String content

    public Item(long id, String content) {
        this.id = id;
        this.content = content;
    }
}

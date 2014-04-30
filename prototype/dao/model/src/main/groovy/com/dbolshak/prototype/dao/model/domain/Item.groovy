package com.dbolshak.prototype.dao.model.domain

/**
 * Simple persistent item
 */
class Item {
    long id
    String content

    Item(long id, String content) {
        this.id = id;
        this.content = content;
    }
}

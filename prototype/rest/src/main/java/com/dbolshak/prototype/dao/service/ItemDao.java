package com.dbolshak.prototype.dao.service;

import com.dbolshak.prototype.dao.model.domain.Item;

/**
 * Created by dbolshak on 28.04.2014.
 */
public interface ItemDao {
    Item create(String content);
    Item read  (long id);
    boolean  update(Item item);
    boolean  delete(long id);
}

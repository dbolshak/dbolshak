package com.dbolshak.prototype.dao.service;

import com.dbolshak.prototype.dao.model.domain.Item;

/**
 * Dao API to perform CRUD operations on Item.
 */
public interface ItemDao {

    /**
     * Create a new item and saves it to storage
     *
     * @param content content of item
     * @return just created item
     */
    Item create(String content);

    /**
     * Fetches item from storage
     *
     * @param id item id of element which is requested
     * @return item from storage or null if not found
     */
    Item read(long id);

    /**
     * Saves changes of item.
     *
     * @param item which has updates
     * @return true if success or false otherwise
     */
    boolean update(Item item);

    /**
     * Removes item from storage
     *
     * @param id of item which should be removed
     * @return true if success or false otherwise
     */
    boolean delete(long id);
}

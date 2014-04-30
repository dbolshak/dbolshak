package com.dbolshak.prototype.dao.service;

import com.dbolshak.prototype.dao.model.domain.Item;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple implementation of ItemDao based on memory.
 */
@Service("itemDao")
class ItemDaoMemDB implements ItemDao {
    private final Map<Long, Item> storage = new HashMap<Long, Item>();
    private final AtomicLong counter = new AtomicLong(0);

    @Override
    public Item create(String content) {
        long id = counter.incrementAndGet();
        Item item = new Item(id, content);
        storage.put(id, item);
        return item;
    }

    @Override
    public Item read(long id) {
        return storage.get(id);
    }

    @Override
    public boolean update(Item item) {
        if (read(item.getId()) != null) {
            storage.put(item.getId(), item);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        if (read(id) != null) {
            storage.remove(id);
            return true;
        }
        return false;
    }
}

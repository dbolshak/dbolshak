package com.dbolshak.prototype.dao.service.impl;

import com.dbolshak.prototype.dao.model.domain.Item;
import com.dbolshak.prototype.dao.service.ItemDao;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple implementation of ItemDao based on memory.
 */
@Service("itemDao")
class ItemDaoMemDB implements ItemDao {
    private final Map<Long, Item> storage = new HashMap<>();
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
        if (has(item.getId())) {
            storage.put(item.getId(), item);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(long id) {
        if (has(id)) {
            storage.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean has(long id) {
        return storage.containsKey(id);
    }
}

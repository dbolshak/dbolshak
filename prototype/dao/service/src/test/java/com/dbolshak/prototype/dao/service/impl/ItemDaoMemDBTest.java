package com.dbolshak.prototype.dao.service.impl;

import com.dbolshak.prototype.dao.model.domain.Item;
import com.dbolshak.prototype.dao.service.ItemDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * unit tests for ItemDaoMemDB
 */
public class ItemDaoMemDBTest {
    private static final String CONTENT = "Test";
    private ItemDao itemDao;

    @Before
    public void setUp() {
        itemDao = new ItemDaoMemDB();
    }

    @Test
    public void testCreate() throws Exception {
        Item item = itemDao.create(CONTENT);
        long itemId = item.getId();
        Assert.assertEquals(CONTENT, itemDao.read(itemId).getContent());

        long anotherItemId = itemDao.create(CONTENT).getId();
        Assert.assertEquals(CONTENT, itemDao.read(anotherItemId).getContent());

        Assert.assertTrue(anotherItemId != itemId);
    }

    @Test
    public void testUpdate() throws Exception {
        Item item = itemDao.create(CONTENT);
        long itemId = item.getId();

        String newContent = "another test";
        item.setContent(newContent);
        Assert.assertEquals(true, itemDao.update(item));
        Assert.assertEquals(newContent, itemDao.read(itemId).getContent());

        item = itemDao.create(CONTENT);
        item.setId(Long.MAX_VALUE);
        Assert.assertEquals(false, itemDao.update(item));
    }

    @Test
    public void testDelete() throws Exception {
        long itemId = itemDao.create(CONTENT).getId();
        Assert.assertEquals(itemId, itemDao.read(itemId).getId());
        Assert.assertTrue(itemDao.read(itemId) != null);

        Assert.assertEquals(true, itemDao.delete(itemId));
        Assert.assertTrue(itemDao.read(itemId) == null);

        Assert.assertEquals(false, itemDao.delete(Long.MAX_VALUE));
        Assert.assertTrue(itemDao.read(Long.MAX_VALUE) == null);
    }

    @Test
    public void has() {
        Assert.assertEquals(false, itemDao.has(0));
        Assert.assertEquals(false, itemDao.has(-1));
        Assert.assertEquals(false, itemDao.has(1));
        Assert.assertEquals(false, itemDao.has(Long.MAX_VALUE));
        Assert.assertEquals(false, itemDao.has(Long.MIN_VALUE));

        long itemId = itemDao.create(CONTENT).getId();
        Assert.assertEquals(true, itemDao.has(itemId));
    }
}

package com.dbolshak.prototype.rest.controller;

import com.dbolshak.prototype.dao.domain.Item;
import com.dbolshak.prototype.dao.service.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {
    static int counter = 0;
    @Autowired
    ItemDao itemDao;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Item get(@PathVariable long id) {
        return itemDao.read(id);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String generate() {
        ++counter;
        itemDao.create(String.format("test{%s}", counter));
        return "done";
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public String delete(@PathVariable long id) {
        itemDao.delete(id);
        return "done";
    }
}
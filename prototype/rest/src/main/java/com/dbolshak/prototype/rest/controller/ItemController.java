package com.dbolshak.prototype.rest.controller;

import com.dbolshak.prototype.dao.model.domain.Item;
import com.dbolshak.prototype.dao.service.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
public class ItemController {
    private static int counter = 0;
    @Autowired
    ItemDao itemDao;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Item> get(@PathVariable long id) {
        if (itemDao.has(id)) {
            return new ResponseEntity<Item>(itemDao.read(id), HttpStatus.OK);
        }
        return new ResponseEntity<Item>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Boolean> delete(@PathVariable long id) {
        if (itemDao.has(id)) {
            return new ResponseEntity<Boolean>(itemDao.delete(id), HttpStatus.OK);
        }
        return new ResponseEntity<Boolean>(HttpStatus.NOT_FOUND);
    }


    /** For testing */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String generate() {
        ++counter;
        itemDao.create(String.format("test{%s}", counter));
        return String.valueOf(counter);
    }
}
package com.dbolshak.prototype.rest.controller;

import com.dbolshak.prototype.dao.model.domain.Item;
import com.dbolshak.prototype.dao.service.ItemDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
            return new ResponseEntity<>(itemDao.read(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseEntity<Boolean> delete(@PathVariable long id) {
        if (itemDao.has(id)) {
            return new ResponseEntity<>(itemDao.delete(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /**
     * For testing
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String generate() {
        ++counter;
        itemDao.create(String.format("test{%s}", counter));
        return String.valueOf(counter);
    }
}
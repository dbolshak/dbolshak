package com.dbolshak.prototype.rest.controller;

import com.dbolshak.prototype.dao.model.domain.Item;
import com.dbolshak.prototype.dao.service.ItemDao;

import org.junit.Before;
import org.junit.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by dbolshak
 */
public class ItemControllerTest {
    private MockMvc mockMvc;

    @InjectMocks
    ItemController controller;

    @Mock
    ItemDao itemDao;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(controller).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void getItemFound() throws Exception {

        when(itemDao.read(1)).thenReturn(new Item(1, "test content"));
        when(itemDao.has(1)).thenReturn(true);

        this.mockMvc.perform(
                get("/item/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

        verify(itemDao).read(1);
    }

    @Test
    public void getItemNotFound() throws Exception {

        when(itemDao.read(1)).thenReturn(null);
        when(itemDao.has(1)).thenReturn(false);

        this.mockMvc.perform(
                get("/item/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(itemDao).read(1);
    }
}

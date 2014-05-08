package com.dbolshak.prototype.rest.controller;

import com.dbolshak.prototype.dao.model.domain.Item;
import com.dbolshak.prototype.dao.service.ItemDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by dbolshak
 */
public class ItemControllerTest {
    @InjectMocks
    ItemController controller;
    @Mock
    ItemDao itemDao;
    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(controller).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void getItemFound() throws Exception {

        when(itemDao.read(1)).thenReturn(new Item(1, "test content"));
        when(itemDao.has(1)).thenReturn(true);

        ResultActions resultActions = this.mockMvc.perform(
                get("/item/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        Assert.assertEquals("{\"id\":1,\"content\":\"test content\"}", resultActions.andReturn().getResponse().getContentAsString());

        verify(itemDao).read(1);
        verify(itemDao).has(1);
    }

    @Test
    public void getItemNotFound() throws Exception {

        when(itemDao.read(1)).thenReturn(null);
        when(itemDao.has(1)).thenReturn(false);

        this.mockMvc.perform(
                get("/item/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(itemDao).has(1);
    }

    @Test
    public void deleteItemFoundSuccess() throws Exception {
        deleteItem(true);
    }

    @Test
    public void deleteItemFoundNotSuccess() throws Exception {
        deleteItem(false);
    }

    @Test
    public void deleteItemNotFound() throws Exception {
        when(itemDao.has(1)).thenReturn(false);

        this.mockMvc.perform(
                delete("/item/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNotFound());

        verify(itemDao).has(1);
    }

    private void deleteItem(boolean found) throws Exception {
        when(itemDao.delete(1)).thenReturn(found);
        when(itemDao.has(1)).thenReturn(true);

        ResultActions resultActions = this.mockMvc.perform(
                delete("/item/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        Assert.assertEquals(String.valueOf(found), resultActions.andReturn().getResponse().getContentAsString());
        verify(itemDao).delete(1);
    }
}

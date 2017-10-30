package com.xwch;

import com.xwch.bean.Spittle;
import com.xwch.web.controller.HomeController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by xwch on 2017/10/18.
 */
public class HomeControllerTest {

    /**
     * 断言home方法返回的是字符窜“home”
     */
    @Test
    public void testHello(){
        HomeController homeController = new HomeController();
        assertEquals("home",homeController.home());
    }

    /**
     * 模拟http对“/”的GET请求，并断言结果视图名为home
     * @throws Exception
     */
    @Test
    public void testHomePage() throws Exception {
        HomeController controller = new HomeController();
        MockMvc mockMvc = standaloneSetup(controller).build();
        mockMvc.perform(get("/")).andExpect(view().name("home"));
    }


}

package com.xwch;

import com.xwch.bean.Spittle;
import com.xwch.data.SpittleRepository;
import com.xwch.data.impl.JdbcSpittleRepository;
import com.xwch.web.controller.SpittleController;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * Created by xwch on 2017/10/19.
 */
public class SpittleComtrollerTest {

    @Resource
    SpittleRepository repository;

    @Test
    public void test(){
        List<Spittle> list =  repository.findSpittles(Long.MAX_VALUE,20);
    }

    @Test
    public void shouldShowRecentSpittles() throws Exception {
        List<Spittle> expectedSpittles = createSpittleList(20);
        //对SpittleRepository接口的mock，mock可以是类/接口
        SpittleRepository mockRepository = mock(SpittleRepository.class);
        //设置参数的预期返回值
        when(mockRepository.findSpittles(Long.MAX_VALUE,20))
                .thenReturn(expectedSpittles);

        SpittleController controller = new SpittleController(mockRepository);

        //mockMVC，指定视图
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
                .build();

        /**
         * 这个测试对“/spittles”发起GET请求，
         * 然后断言视图的名称为spittles并且模型中包含名为spittleList的属性，
         * 在spittleList中包含预期的内容。
         */
        mockMvc.perform(get("/spittles"))
                .andExpect(MockMvcResultMatchers.view().name("spittles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
                .andExpect(MockMvcResultMatchers.model().attribute("spittleList",
                        Matchers.hasItems(expectedSpittles.toArray())));


    }


    @Test
    public void shouldShowPagedSpittles() throws Exception {
        //创建预期结果
        List<Spittle> expectedSpittles = createSpittleList(5);
        //mock 接口SpittleRepository
        SpittleRepository spittleRepository = mock(SpittleRepository.class);
        //设定调用findSpittles方法时返回的预期结果
        when(spittleRepository.findSpittles(238900,5)).thenReturn(expectedSpittles);

        //创建Controller，并注入spittleRepository bean
        SpittleController controller = new SpittleController(spittleRepository);

        //mockMVC，使用standaloneSetup，并且设定视图
        MockMvc mockMvc = standaloneSetup(controller)
                .setSingleView(new InternalResourceView("/WEB-INF/views/spittles.jsp"))
                .build();

        //模拟请求路径和参数/spittles?max=238900&count=5，方法为GET
        //预期视图名为spittles
        //预期model key为spittleList
        //预期model value为第一步创建的预期结果expectedSpittles
        mockMvc.perform(get("/spittles?max=238900&count=5"))
                .andExpect(MockMvcResultMatchers.view().name("spittles"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("spittleList"))
                .andExpect(MockMvcResultMatchers.model().attribute("spittleList",
                        Matchers.hasItems(expectedSpittles.toArray())));
    }


    @Test
    public void testSpittle() throws Exception {
        Spittle exceptedSpittle = new Spittle("hello",new Date());
        SpittleRepository spittleRepository = mock(SpittleRepository.class);
        when(spittleRepository.findOne(12345)).thenReturn(exceptedSpittle);

        SpittleController controller = new SpittleController(spittleRepository);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/spittles/12345"))
                .andExpect(MockMvcResultMatchers.view().name("spittle"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("spittle"))
                .andExpect(MockMvcResultMatchers.model().attribute("spittle",exceptedSpittle));


    }


    /**
     * 根据入参创建N个Spittle，并且返回
     * @param count  创建的Spittle个数
     * @return
     */
    private List<Spittle> createSpittleList(int count) {
        List<Spittle> spittles = new ArrayList<Spittle>();
        for (int i=0; i < count; i++) {
            spittles.add(new Spittle("Spittle " + i, new Date()));
        }
        return spittles;
    }
}

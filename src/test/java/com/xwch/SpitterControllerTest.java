package com.xwch;

import com.xwch.data.SpitterRepository;
import com.xwch.web.controller.SpitterController;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

/**
 * Created by xwch on 2017/10/22.
 */
public class SpitterControllerTest {

    /**
     * test method showRegistrationForm
     * @throws Exception
     */

    @Test
    public void shouldShowRegistration() throws Exception {
        SpitterRepository spitterRepository = mock(SpitterRepository.class);
        SpitterController controller = new SpitterController(spitterRepository);

        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/spitter/register"))
                .andExpect(MockMvcResultMatchers.view().name("registerForm"));
    }
}

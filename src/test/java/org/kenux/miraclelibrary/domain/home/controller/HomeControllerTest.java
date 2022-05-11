package org.kenux.miraclelibrary.domain.home.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.kenux.miraclelibrary.domain.book.controller.response.BookResponse;
import org.kenux.miraclelibrary.domain.book.controller.response.NewBookResponse;
import org.kenux.miraclelibrary.domain.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @BeforeEach
    void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

    @Test
    @DisplayName("GET / 요청 -> views/home/main 페이지 반환")
    void homeMainPage() throws Exception {
        // given
        List<NewBookResponse> newBookResponses = new ArrayList<>();
        given(bookService.getNewBooks()).willReturn(newBookResponses);

        // when
        final ResultActions resultActions = mockMvc.perform(get("/"));

        // then
        resultActions.andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("views/home/main"))
                .andExpect(model().size(2));
    }
}
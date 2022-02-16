package com.example.demo.controller;

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.demo.service.MemberService;
import com.example.demo.vo.MemberVo;

//https://mangkyu.tistory.com/145

@ExtendWith(MockitoExtension.class)
class TestJpaRestControllerTest {

	@InjectMocks
	private TestJpaRestController testJpaRestController;
	@Mock
	private MemberService memberService;

	private MockMvc mockMvc;

	@BeforeEach
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(testJpaRestController).build();
	}

	@DisplayName("회원 조회")
	@Test
	void signUpSuccess() throws Exception {
		// given
		final MemberVo memberVo = new MemberVo();
		//doReturn(false).when(memberService).findById(3L);
		doReturn(Optional.of(new MemberVo("a", "b"))).when(memberService).findById(3L);
		
		// when    
		final ResultActions resultActions = mockMvc.perform(            
				MockMvcRequestBuilders.get("/memberTest/3")                   
				.contentType(MediaType.APPLICATION_JSON)                   
				//.content(new Gson().toJson(signUpDTO))   
		);
		
		// then
		final MvcResult mvcResult = resultActions.andExpect(status().isOk()).andReturn();


		//출처: https://mangkyu.tistory.com/145 [MangKyu's Diary]
	}
	/*
	 * private SignUpDTO signUpDTO() { final SignUpDTO signUpDTO = new SignUpDTO();
	 * signUpDTO.setEmail("test@test.test"); signUpDTO.setPw("test"); return
	 * signUpDTO; }
	 */
}

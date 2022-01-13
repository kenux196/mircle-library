package org.kenux.miraclelibrary.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kenux.miraclelibrary.domain.Member;
import org.kenux.miraclelibrary.domain.enums.MemberRole;
import org.kenux.miraclelibrary.exception.CustomException;
import org.kenux.miraclelibrary.exception.ErrorCode;
import org.kenux.miraclelibrary.repository.MemberRepository;
import org.kenux.miraclelibrary.rest.dto.LoginRequest;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    MemberRepository memberRepository;

    @InjectMocks
    LoginService loginService;

    @Test
    @DisplayName("이메일에 해당하는 회원 없으면, 로그인 실패")
    void test_errorLogin_whenNotExistMember() throws Exception {
        // given
        LoginRequest loginRequest = new LoginRequest("user@test.com", "password");
        given(memberRepository.findByEmail(any())).willReturn(Optional.empty());
        // when
        // then
        assertThatThrownBy(() -> loginService.login(loginRequest))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.MEMBER_NOT_FOUND.getMessage());
    }

    @Test
    @DisplayName("패스워드 틀리면, 로그인 실패")
    void test_errorLogin_whenWrongPassword() throws Exception {
        // given
        LoginRequest loginRequest = new LoginRequest("user@test.com", "password");
        Member member = Member.builder()
                .name("user")
                .email("user@test.com")
                .password("wrongPassword")
                .memberRole(MemberRole.CUSTOMER)
                .build();
        given(memberRepository.findByEmail(any())).willReturn(Optional.ofNullable(member));

        // when
        // then
        assertThatThrownBy(() -> loginService.login(loginRequest))
                .isInstanceOf(CustomException.class)
                .hasMessage(ErrorCode.PASSWORD_WRONG.getMessage());
    }


    @Test
    @DisplayName("정상 입력에 대해서 로그인 성공")
    void test_successLogin() throws Exception {
        // given
        LoginRequest loginRequest = new LoginRequest("user@test.com", "password");
        Member member = Member.builder()
                .name("user")
                .email("user@test.com")
                .password("password")
                .memberRole(MemberRole.CUSTOMER)
                .build();
        given(memberRepository.findByEmail(any())).willReturn(Optional.ofNullable(member));
        
        // when
        final Member result = loginService.login(loginRequest);

        // then
        assertThat(result).isNotNull();
    }


}
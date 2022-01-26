package org.kenux.miraclelibrary.domain.member.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.kenux.miraclelibrary.domain.member.domain.Member;
import org.kenux.miraclelibrary.domain.member.domain.MemberRole;
import org.kenux.miraclelibrary.domain.member.domain.MemberStatus;

@NoArgsConstructor
@Getter
public class LibrarianAddRequest {

    private String name;
    private String email;
    private String password;

    @Builder
    public LibrarianAddRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Member toEntity() {
        Member member = Member.builder()
                .name(name)
                .email(email)
                .memberRole(MemberRole.LIBRARIAN)
                .status(MemberStatus.NORMAL)
                .build();
        member.changePassword(password);
        return member;
    }
}
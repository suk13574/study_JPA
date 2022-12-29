package jpabasic.hellojpa.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberTestDTO {
    private String username;
    private int age;

    public MemberTestDTO(String username, int age) {
        this.username = username;
        this.age = age;
    }
}

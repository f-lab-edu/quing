package flab.quing.repository;

import flab.quing.user.User;
import flab.quing.user.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @Test
    void 새로운사용자추가_성공() {
        User member = new User();
        member.setName("chanwoo");
        member.setPassword("chanwoo1234");
        member.setPhone("010-0000-0000");
        memberRepository.save(member);
        assertThat(memberRepository.findAll().size()).isEqualTo(1);
        assertThat(memberRepository.findByPhone("010-0000-0000").get().getName()).isEqualTo(member.getName());
    }
}
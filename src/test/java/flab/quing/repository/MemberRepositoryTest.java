package flab.quing.repository;

import flab.quing.domain.Member;
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
        Member member = new Member();
        member.setName("chanwoo");
        member.setPassword("chanwoo1234");
        member.setPhone("010-0000-0000");
        memberRepository.save(member);
        assertThat(memberRepository.findAll().size()).isEqualTo(1);
        assertThat(memberRepository.findByPhone("010-0000-0000").get().getName()).isEqualTo(member.getName());
    }
}
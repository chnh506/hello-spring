package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository repository;

    @BeforeEach
    public void beforeEach() {
        repository = new MemoryMemberRepository();
        memberService = new MemberService(repository);
    }

    // 메서드가 실행이 끝날 때마다 어떤 동작을 하는 콜백 메서드
    // 하나의 test가 끝날 때마다 저장소(공용 데이터?)를 깔끔하게 지워줘야 문제가 발생하지 않는다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    // given - when - then 문법 추천 !!!
    @Test
    void join() {
        // given (어떠한 상황이 주어졌어)
        Member member = new Member();
        member.setName("spring");

        // when (무엇을 실행했을 때)
        Long savedID = memberService.join(member);

        // then (어떤 결과가 나와야해)
        Member findMember = memberService.findOne(savedID).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        // given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        // when
        memberService.join(member1);

        // 굳이 거창하게 try-catch 말고, assertThrows라는 멋진 문법을 씁시다.
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        // 메시지가 올바른지 검증은 이렇게 ~
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
        */

        // then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
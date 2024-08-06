package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 메서드가 실행이 끝날 때마다 어떤 동작을 하는 콜백 메서드
    // 하나의 test가 끝날 때마다 저장소(공용 데이터?)를 깔끔하게 지워줘야 문제가 발생하지 않는다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);
        Member result = repository.findById(member.getId()).get();  // Optional에서 값을 꺼낼 때는 .get()으로 바로 꺼낼 수 있다.

        // System.out.println("result: " + (result == member));    // 근데 매번 내가 글자로 확인할 수는 없음 ..
        // Assertions.assertEquals(result, member);    // org.junit.jupiter.api.Assertions

        // Assertions를 좀 더 편하게 쓸 수 있다!
        assertThat(member).isEqualTo(result);    // org.assertj.core.api.Assertions
    }


    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}

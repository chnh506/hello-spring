package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;

import java.util.List;
import java.util.Optional;

// 강의의 상황 가정 : 아직 어떤 DB를 쓸지 정해지지 않은 상황.
// 따라서, 인터페이스로 만들어 둔 다음, 구현체가 정해지면 그때 적용해 줄 계획
// 개발 과정에서는 간단하게 메모리 저장소로 구현 및 테스트해 본다.
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}

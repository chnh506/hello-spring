package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

// 이전에 만들었던 MemberRepository의 구현체
public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;  // 0, 1, 2, ... key 값을 생성해 주는 역할

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // 구현해 둔 store에서 꺼내면 OK
        // store.get, .put 등등은 Java의 Map에 구현돼있는 메서드인 듯 ~
        // Optional - Java8에 추가된 기능
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // Java8 - lambda 기능 활용 (자바 강의 아닙니다 ... 영한좌 왈)
        return store.values().stream()
                .filter(m -> m.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        // 실무에서는 List를 많이 쓴다. 루프를 돌리기도 편하고 해서 ...?
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}

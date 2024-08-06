package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

    // final 키워드는 무슨의미지 ..?
    private final MemberRepository memberRepository;

    // MemberService 입장에서..
    // -> memoryMemberRepository를 내가 직접 new하지 않고 외부에서 넣어 준다.
    // -> 우리는 이런 걸 '의존성 주입(Dependency Injection)'이라 한다.
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
     *  회원 가입
     *  비즈니스 로직 - 같은 이름이 있으면 안 된다.
     */
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //        Optional<Member> result = memberRepository.findByName(member.getName());
        //        result.ifPresent(m -> {
        //            throw new IllegalStateException("이미 존재하는 회원입니다.");
        //        });

        // 위처럼 해도되지만.. 옵셔널 반환하고 하니 별로 안이쁨. 가독성 ..
        // 어차피 Optional을 return하니, 아래처럼 작성하자.
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long id) {
        return memberRepository.findById(id);
    }
}

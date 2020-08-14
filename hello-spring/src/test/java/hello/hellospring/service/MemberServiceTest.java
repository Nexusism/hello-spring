package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
// 테스트는 과감하게 한글로 바꿔줘도됨
    // 빌드될때 테스트는 포함되지 않기떄문

    MemberService memberService;
    MemoryMemberRepository memberRepository;


    @BeforeEach
    public void BeforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);

    }

    @AfterEach //전체 테스트 진행할때 findAll이 먼저실행되서 findByName에 다른 객체가 들어가져서
    // 테스트가 오류나니까 한번 테스트 진행하고 스토어를 클리어해주는 작업 필요
    // 순서에 의존하지않고 작동되야함
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given 이런상황이 주어졌는데
        Member member = new Member();
        member.setName("Spring");

        //when 이걸 실행했을때
        Long saveId = memberService.join(member);
        //then 결과가 이게 나와야되
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복_회원_예외 () {
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //than
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}
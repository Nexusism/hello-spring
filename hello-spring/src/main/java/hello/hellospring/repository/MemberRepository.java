package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
//**********DB요청하는 동작을 정의해논게 모델

public interface MemberRepository { //class에 어떤 메소드를 넣을지 규약을 정하는거
    Member save(Member member);
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);
    List<Member> findAll();
}

package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest //스프링 띄운 상태로 테스트 해야하니까 이거 없으면 autowired안돼
@Transactional //데이터를 변경해야하니까 이거 있어야 rollback이 됨.
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    //@Rollback(false)
    public void 회원가입() throws Exception{
        //given
        Member member=new Member();
        member.setName("lee");

        //when
        Long savedId = memberService.join(member);

        //then
        em.flush();
        assertEquals(member,memberService.findOne(savedId));
    }

    @Test(expected=IllegalStateException.class)
    public void 중복_회원_예외() throws Exception{
        //given
        Member member1=new Member();
        member1.setName("kim");

        Member member2=new Member();
        member2.setName("kim");
        //when
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 한다.

        /*try{
            memberService.join(member2); //예외가 발생해야 한다.
        }catch (IllegalStateException e){
            return;
        }*/
        //then
        fail("예외가 발생해야 한다."); // 예외가 발생하면 여기 안옴. 예외가 발생하지 않았을 경우 여기까지 옴. 테스트 실패!
    }

}
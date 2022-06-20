package jpabook.jpashop.controller;

import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model){
        model.addAttribute("memberForm",new MemberForm()); //컨트롤러에서 뷰로 넘어갈 때 이 데이터를 실어서 넘김, 화면에서 MemberForm이라는 객체에 접근 가능
        return "members/createMemberForm";

    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result){ //validate하고 오류가 있으면 오류를 담아서 코드가 실행됨

        if(result.hasErrors()){
            return "members/createMemberForm"; //스프링이 binding result를 폼에 가져가서 쓸 수 있게 해줌
        }
        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member=new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);

        return "redirect:/"; //첫번째 페이지로 넘어감
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();

        model.addAttribute("members",members); //모델에 담아서 화면에 넘김

        return "members/memberList";
    }
}

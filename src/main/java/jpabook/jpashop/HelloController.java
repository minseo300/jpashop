package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    //모델에 데이터를 실어서 view에 데이터를 넘길 수 있음 (controller에서 view로 데이터를 넘길 수 있음)
    public String hello(Model model){
        model.addAttribute("data","hello!!!");
        return "hello"; //화면 이름
    }
}

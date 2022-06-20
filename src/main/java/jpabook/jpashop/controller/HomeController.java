package jpabook.jpashop.controller;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

    //Logger log= LoggerFactory.getLogger(getClass());

    @RequestMapping("/")
    public String home(){
        log.info("home controller"); // 컨트롤러 연 다음에, 컨트롤러에서 뷰로 렌더링 했는데 그때 문제 발생
        return "home";
    }
}

package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
//*********************요청 - 응답을 뷰로 보내주고 연결해주는거
@Controller
public class HelloController {

    @GetMapping("hello") //url 의 헬로? localhost:8080/hello 매핑?
    public String hello(Model model) { //파라미터로 모델을 날리는 이유?
        model.addAttribute("data", "hello!!");
        System.out.println("asd");
        return "hello"; //이건 resources/templates/hello.html 로 보내는건가 attribute data를?

    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) { //name 파라미터를 어디서 받아오는건가?
        model.addAttribute("name", name);
        return "hello-template";
    } //그럼 여기가 controller, hello-template.html이 View가 되는건가
    // Model에 대한 개념과 사용이유?
    // static에 있는 페이지는 파일자체를 서버에서 웹브라우저로 보내지만,
    // templates에 있는거는 controller를 통해서 viewResolver가 thymeleaf문법을 처리한 후
    // html화시켜서 값변환후에 웹브라우저로 전달 하는거.

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name; //"hello spring" http body부로 html이아니라 문자 자체가 나감
        // View를 거치지않음, 거의 안씀
    }

    @GetMapping("hello-api")
    @ResponseBody // 기존 template는 viewResolver로 던지는데 이게있으면
    // httpMessageConverter 응답으로 넘김 근데 객체를줌 여기선
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello(); // 객체를줄때 기본은 json으로 객체를줌
        hello.setName(name);     //{name:값} 형식으로
        return hello;
    }



    static class Hello {
        private String name; // private라 외부에서 바로 못꺼냄
        // getter and setter 생성 단축키 컨트롤+N =All 에서 검색
        // java 빈 규약 , 프로퍼티 접근방식

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }


}




package hello.hello_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "Spring 연습중");
        return "hello";
        // resources/templates 폴더 밑의 'hello'를 찾아서 렌더링한다.
        // 찾아서 thymeleaf 템플릿 엔진이 처리를 해 준다.
    }

    @GetMapping("hello-mvc")    // url path에서 /hello-mvc에 대한 Controller 작성..? 을 의미하는 듯.
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);   // 웹사이트에서 URL 파라미터를 넘겨줘야 한다. (http get방식)
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody   // HTTP 프로토콜의 response body부에 내가 이 데이터를 직접 넣어 주겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }


    // API 방식 !!
    // 처음으로 문자가 아닌 객체(여기서는 Hello)를 넘긴다.
    // JSON 방식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        // ResponseBody를 사용했는데 return값으로 객체가 왔다?
        // Default: json 방식으로 데이터를 만들어서 HTTP 응답에 반환하겠다는 것이 기본 정책.
    }

    static class Hello {
        // JavaBean 표준 방식
        // 변수(name)은 private으로 외부에서 접근하지 못하게 하고, getter/setter라는 public 메서드를 통해 접근한다.
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}

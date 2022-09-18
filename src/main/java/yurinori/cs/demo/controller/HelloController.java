package yurinori.cs.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

	@RequestMapping("/")
	@ResponseBody
	public String get() {
		System.out.println("hihi+__________+_+_+_+_+_+__");
		return "Welcome to spring api project";
	}
}

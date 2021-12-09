package in.ameya.bankfair.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloRestController {
	@GetMapping("/api/hello")
	//System.out.println("Start");
public String welcome() {
	System.out.println("In_Start");
	return "Welcome To Ameya";
}
	
}

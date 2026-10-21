package com.project.eudocare.registration.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.project.eudocare.registration.service.OTPService;
import com.project.eudocare.registration.service.passwordService;

@Controller
@RequestMapping("/password")
public class passwordController 
{
	@Autowired
	private passwordService ps;
	@Autowired
	private OTPService os;
    @PostMapping("/sendOtp")
    public ModelAndView emailVerification(@RequestParam("email") String email)
	{
	   String check=ps.emailCheck(email);
	     if(check.equals("true"))
	     {
	    	 os.generateOtp(email);
	    	 Map<String,String> map=new HashMap<String, String>();
			 map.put("email",null);
			 map.put("otp",email);
			 map.put("error",null);
			 return new ModelAndView("forgetPassword").addObject("data",map);
	     }
	     else
	     {
	     Map<String,String> map=new HashMap<String, String>();
		 map.put("email","true");
		 map.put("otp",null);
		 map.put("error",check);
		 return new ModelAndView("forgetPassword").addObject("data",map);
	     }
	}
    @PostMapping("/otpCheck")
    public String checkOtp(@RequestParam("email") String email ,@RequestParam("otp") String otp,@RequestParam("password") String password,Model m)
    {
    	String out=ps.otpCheck(email, otp, password);
    	if(out!=null)
    	{
    		return out;
    	}
    	else
    	{
    	 Map<String,String> map=new HashMap<String, String>();
       	 map.put("email","true");
   		 map.put("otp",null);
   		 map.put("error","Invalid or expired OTP");  
   		 m.addAttribute("data",map);
       	 return "forgetPassword";	
    	}
    
    }
}

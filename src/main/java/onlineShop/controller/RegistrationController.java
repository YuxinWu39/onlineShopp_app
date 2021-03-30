package onlineShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import onlineShop.entity.Customer;
import onlineShop.service.CustomerService;

@Controller
public class RegistrationController {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customer/registration", method = RequestMethod.GET)//gain information from jsp
    public ModelAndView getRegistrationForm() {
        return new ModelAndView("register", "customer", new Customer());
    }

    @RequestMapping(value = "/customer/registration", method = RequestMethod.POST)
    //把表单上的内容填进Customer中 绑定复制
    public ModelAndView registerCustomer(@ModelAttribute Customer customer) {
        ModelAndView modelAndView = new ModelAndView();
        customerService.addCustomer(customer);
        modelAndView.setViewName("login");
        modelAndView.addObject("registrationSuccess", "Registered Successfully. Login using username and password");
        return modelAndView;
    }
}

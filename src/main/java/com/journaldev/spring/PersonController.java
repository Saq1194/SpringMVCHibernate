package com.journaldev.spring;

import com.journaldev.spring.model.Address;
import com.journaldev.spring.model.Person;
import com.journaldev.spring.service.AddressService;
import com.journaldev.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PersonController {

	private PersonService personService;
	private AddressService addressService;

	@Autowired(required=true)
	@Qualifier(value="personService")
	public void setPersonService(PersonService ps){
		this.personService = ps;
	}

	@Autowired(required=true)
	@Qualifier(value="addressService")
	public void setAddressService(AddressService as){
		this.addressService = as;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String defaultPath() {
		return "redirect:/persons";
	}

	@RequestMapping(value = "/persons", method = RequestMethod.GET)
	public String listPersons(Model model) {
		model.addAttribute("person", new Person());
		model.addAttribute("listPersons", this.personService.listPersons());
		return "person";
	}

	//For add and update person both
	@RequestMapping(value= "/person/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("person") Person p){

		if(p.getId() == 0){
			//new person, add it
			this.personService.addPerson(p);
		}else{
			//existing person, call update
			this.personService.updatePerson(p);
		}

		return "redirect:/persons";

	}

	@RequestMapping("/remove/{id}")
    public String removePerson(@PathVariable("id") int id){

        this.personService.removePerson(id);
        return "redirect:/persons";
    }

    @RequestMapping("/edit/{id}")
    public String editPerson(@PathVariable("id") int id, Model model){
        model.addAttribute("person", this.personService.getPersonById(id));
        model.addAttribute("listPersons", this.personService.listPersons());
        return "person";
    }


    @RequestMapping("/viewa_addresses/{id}")
    public ModelAndView viewAddresses(@PathVariable("id") int id, Model model){
		ModelAndView modelAndView = new ModelAndView("addresses");

		modelAndView.addObject("listAddress", this.personService.getPersonWithAddressesById(id).getAddresses());
		Address a = new Address();
		a.setPersonId(id);

		model.addAttribute("addresses", a);


//		modelMap.addAttribute("listAddress", this.personService.getPersonWithAddressesById(id).getAddresses());

        return modelAndView;
    }

	@RequestMapping(value= "/address/add", method = RequestMethod.POST)
	public String addAddress(@ModelAttribute("address") Address address){


			this.addressService.addAddress(address);


		return "redirect:/viewa_addresses/" + address.getPersonId();

	}


}

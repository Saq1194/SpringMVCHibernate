package com.journaldev.spring;

import com.journaldev.spring.model.Address;
import com.journaldev.spring.model.Person;
import com.journaldev.spring.service.AddressService;
import com.journaldev.spring.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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


    @RequestMapping(value = "/viewa_addresses/{id}", method = RequestMethod.GET)
    public ModelAndView viewAddresses(@PathVariable("id") int id, Model model){
		ModelAndView modelAndView = new ModelAndView("addresses");

		modelAndView.addObject("listAddress", this.personService.getPersonWithAddressesById(id).getAddresses());
		Address a = new Address();
		a.setPersonId(id);

		model.addAttribute("addresses", a);
		model.addAttribute("personId", id);

        return modelAndView;
    }

	@RequestMapping(value= "/addresses/add", method = RequestMethod.POST)
	public String addAddress(@ModelAttribute("addresses") Address addresses){


        if(addresses.getId() == 0) {
            this.addressService.addAddress(addresses);
        }else{
            this.addressService.updateAddress(addresses);
        }

		return "redirect:/viewa_addresses/" + addresses.getPersonId();

	}

    @RequestMapping("/remove_address/person/{personId}/address/{addressId}" )
    public String removeAddress(@PathVariable("personId") int personId,@PathVariable("addressId") int addressId){

        this.addressService.removeAddress(addressId);

        return "redirect:/viewa_addresses/"+personId;
    }

    @RequestMapping("/edit_address/{id}")
    public ModelAndView editAddress(@PathVariable("id") int id){
        ModelAndView modelAndView=new ModelAndView("addresses");
        modelAndView.addObject("addresses",  this.addressService.getAddressById(id));


        return modelAndView;
    }





}

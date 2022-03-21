package com.cognizant.portal.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.ModelAndView;

import com.cognizant.portal.client.AuthClient;
import com.cognizant.portal.client.DrugClient;
import com.cognizant.portal.client.RefillClient;
import com.cognizant.portal.client.SubscriptionClient;
import com.cognizant.portal.model.AllDrugsForSubscription;
import com.cognizant.portal.model.Drug;
import com.cognizant.portal.model.Drugs;
import com.cognizant.portal.model.JwtResponse;
import com.cognizant.portal.model.Login;
import com.cognizant.portal.model.Prescription;
import com.cognizant.portal.model.RefillOrderLine;
import com.cognizant.portal.service.LoginServiceImpl;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class PortalController {

	private List<Drug> temporary = new ArrayList<>();
	@Autowired
	private LoginServiceImpl loginService;
	@Autowired
	private AuthClient authClient;
	@Autowired
	private SubscriptionClient subClient;
	@Autowired
	private RefillClient rflClient;

	@Autowired
	private DrugClient drugClient;
	private Map<String, AllDrugsForSubscription> drugsOfUser = new HashMap<>();

	@RequestMapping("/")
	public String index(@SessionAttribute("login") Login login) {
		return "index";
	}
	
	@RequestMapping("/drugview")
	public String drugview(ModelMap model, @SessionAttribute("login") Login login, @RequestParam int id) {
		String response = loginService.validateUserNameAndPassword(login);
		
		model.addAttribute("drugs", drugClient.searchDrugsByID(response, id).getBody());
		return "drugview";
	}

	@RequestMapping("/drugviewname")
	public String drugview(ModelMap model, @SessionAttribute("login") Login login, @RequestParam String name) {
		String response = loginService.validateUserNameAndPassword(login);

		model.addAttribute("drugs", drugClient.searchDrugsByName(response, name).getBody());
		return "drugview";
	}

	@GetMapping("/drugs")
	public String drugsid(@SessionAttribute("login") Login login) {
		String response = loginService.validateUserNameAndPassword(login);
		return "drugs";
	}

	@GetMapping("/drugsname")
	public String drugs(@SessionAttribute("login") Login login) {
		String response = loginService.validateUserNameAndPassword(login);
		return "drugsname";
	}

//	@GetMapping("/subscribe")
//	public ModelAndView subscribe(@SessionAttribute("login") Login login) {
//		String response = loginService.validateUserNameAndPassword(login);
//		
//		return new ModelAndView("subscribe","pres",new Prescription());
//	}
	@GetMapping("/subscribe")
	public ModelAndView beforeSubscribe(@SessionAttribute("login") Login login) {
		String response = loginService.validateUserNameAndPassword(login);

		return new ModelAndView("beforeSubscribe");
	}

	@GetMapping("/addDrugs")
	public String count(@SessionAttribute("login") Login login, @RequestParam String count, Model model) {
		String response = loginService.validateUserNameAndPassword(login);

		List<Drug> drugForSubscription = new ArrayList<>();
		int c = Integer.parseInt(count);
		for (int i = 0; i < c; i++) {
			drugForSubscription.add(new Drug("", 0));
		}
		AllDrugsForSubscription allDrugsForSubscription = new AllDrugsForSubscription();
		allDrugsForSubscription.setAllDrugsList(drugForSubscription);

		model.addAttribute("drugs", allDrugsForSubscription);
		return "drugsAddForSubscription";
	}

	@PostMapping("/prescription")
	public ModelAndView prescription(@SessionAttribute("login") Login login,
			@ModelAttribute("drugs") AllDrugsForSubscription allDrugs, BindingResult res) {

		String response = loginService.validateUserNameAndPassword(login);
		List<Drug> x = allDrugs.getAllDrugsList();

		drugsOfUser.put(login.getUsername(), allDrugs);
		ModelAndView modelAndView = new ModelAndView("subscribe", "drugs", allDrugs);
		Prescription prescription = new Prescription();

		modelAndView.addObject("pres", prescription);
		return modelAndView;
	}
	
	@PostMapping("/subscribedo")
	public ModelAndView subscribedo(@RequestParam String location, @RequestParam String frequency,
			@SessionAttribute("login") Login login, @ModelAttribute("pres") Prescription pres, BindingResult result) {
		String response = loginService.validateUserNameAndPassword(login);
		log.info(location);
		AllDrugsForSubscription x = drugsOfUser.get(login.getUsername());
		List<Drug> z = x.getAllDrugsList();
		pres.setDrugs(z);
		log.info(pres.toString());
		ResponseEntity<?> re = subClient.subscribe(pres, location, frequency, response);
		if (re.getStatusCodeValue() == 201)
			return new ModelAndView("subscription");
		else
			return new ModelAndView("unsuccessfull");

	}

	@GetMapping("/prescribedDrugs")
	public String prescribeDrugs(ModelMap model, @SessionAttribute("login") Login login) {
		String response = loginService.validateUserNameAndPassword(login);
		model.addAttribute("drugs", temporary);
		return "prescribedDrugs";
	}

	@RequestMapping("/landing")
	public String landing() {
		return "landing";
	}

	

	@RequestMapping("/unsubscribe")
	public String unsubscribe(@SessionAttribute("login") Login login) {
		String response = loginService.validateUserNameAndPassword(login);
		return "unsubscribe";
	}
	@RequestMapping("/doUnsubscribe")
	public String doUnsubscribe(@SessionAttribute("login") Login login,@RequestParam int subscriptionId) {
		String response = loginService.validateUserNameAndPassword(login);
		ResponseEntity<?> unsubscribe=subClient.deleteSubscription(subscriptionId, response);
		if(unsubscribe.getStatusCodeValue()==200) {
			return "subscription";
		}
		return "unsuccessful";
	}
	
	
	
	@RequestMapping("/beforeAdhoc")
	public String beforeAdhoc(@SessionAttribute("login") Login login) {
		return "beforeAdhoc";
	}
	

	@RequestMapping("/requestAdhoc")
	public String requestAdhoc(@SessionAttribute("login") Login login) {
		String response = loginService.validateUserNameAndPassword(login);
		return "requestAdhoc";
	}
	@PostMapping("/addDrugsToAdhoc")
	public String adhocDrugsCount(@SessionAttribute("login") Login login, @RequestParam String subscriptionId, Model model) {
		String response = loginService.validateUserNameAndPassword(login);

		List<Drug> drugForSubscription = new ArrayList<>();
		int c = Integer.parseInt(subscriptionId);
		Map<String,Integer> map=subClient.getAllDrugsOfMember(c);
		 for (Map.Entry<String, Integer> entry : map.entrySet()) {
			 log.info(entry.getKey() + ":" + entry.getValue());
		        drugForSubscription.add(new Drug(entry.getKey(),entry.getValue()));
		    }
		 AllDrugsForSubscription allDrugsForSubscription = new AllDrugsForSubscription();
			allDrugsForSubscription.setAllDrugsList(drugForSubscription);
		model.addAttribute("drugs", allDrugsForSubscription);
		model.addAttribute("subId", subscriptionId);
		return "drugsAddForAdhoc";
	}
	@RequestMapping("/adhoc")
	public String adhoc(@SessionAttribute("login") Login login,
			@ModelAttribute("drugs") AllDrugsForSubscription allDrugs,@RequestParam int subscriptionId,@RequestParam String location,@RequestParam int policyId, BindingResult res) {
		String response = loginService.validateUserNameAndPassword(login);
		List<RefillOrderLine> rfl=new ArrayList<>();
		
		List<Drug> drugs=allDrugs.getAllDrugsList();
		for(Drug drug:drugs) {
			RefillOrderLine refillLine= new RefillOrderLine();
			refillLine.setDrug(drug.getDrugName());
			refillLine.setDrugQuantity(drug.getQuantity());
			rfl.add(refillLine);
			log.info(drug.getDrugName());
			
		}
		log.info(policyId +"     "+location);
	
		rflClient.requestAdhocRefill(subscriptionId,policyId,location,rfl,response );
		return "subscription";
	}


	@RequestMapping("/refillDue")
	public String refillDue(@SessionAttribute("login") Login login) {
		String response = loginService.validateUserNameAndPassword(login);

		return "refillDue";
	}
	
	@RequestMapping("/refillDueView")
	public String refillDueView(ModelMap model, @SessionAttribute("login") Login login,
			@RequestParam int subscriptionId,@RequestParam String date) throws ParseException {
		String response = loginService.validateUserNameAndPassword(login);
		log.info(subscriptionId);
		//String newDate=new SimpleDateFormat("yyyy/MM/dd");
		model.addAttribute("refill", rflClient.getRefillDuesAsOfDate(date, subscriptionId, response));
		return "refillDueView";
	}

	@RequestMapping("/refillStatus")
	public String refillStatus(@SessionAttribute("login") Login login) {
		String response = loginService.validateUserNameAndPassword(login);
		return "refillStatus";
	}

	@RequestMapping("/refillstatusview")
	public String refillstatusview(ModelMap model, @SessionAttribute("login") Login login,
			@RequestParam int subscriptionId) {
		String response = loginService.validateUserNameAndPassword(login);
		log.info(subscriptionId);
		model.addAttribute("refill", rflClient.viewRefillStatus(subscriptionId, response));
		return "refillstatusview";
	}
	
	@RequestMapping("/subscriptionIdsOfMember")
	public ModelAndView subscriptionIdsOfMember(@SessionAttribute("login") Login login) {
		String token = loginService.validateUserNameAndPassword(login);
		log.info(token);
		long memID=authClient.memberId(token);
		List<Integer> subIds =subClient.getAllSubIdOfMember(token,memID);
		
		for(Integer Sub: subIds) {
			log.info(Sub);
		}
		
		
		return new ModelAndView("subscriptionIdsOfMember","subscriptionIds",subIds);
	}
	
	
	

}

package com.cognizant.subscription.Service;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;

import com.cognizant.subscription.Model.Drug;
import com.cognizant.subscription.Model.Prescription;
import com.cognizant.subscription.Model.Subscription;
import com.cognizant.subscription.Repository.SubscriptionRepository;

@org.springframework.stereotype.Service
public class Service implements SubscriptionDAO {
	@Autowired SubscriptionRepository subscriptionrepository;
	@Transactional
	public Subscription save(Subscription subscription) {
		return subscriptionrepository.save(subscription);
	}
	
	@Transactional
	public void delete(int subscriptionid) {
		subscriptionrepository.deleteBySubscriptionId(subscriptionid);
		
	}
	public Map<String, Integer> getAllDrugsOfMember(int subscriptionId) {
		Map<String,Integer> map=new HashMap<>();
		 Subscription subscription=subscriptionrepository.findBySubscriptionId(subscriptionId);
		 Prescription prescription=subscription.getPrescription();
		 List<Drug> drugs=prescription.getDrugs();
		 for(Drug drug:drugs) {
			 map.put(drug.getDrugName(), drug.getQuantity());
		 }
		 return map;
	}
	
	public List<Integer> getSubIdByMemberId(long memberId){
		List<Subscription> subcriptions=subscriptionrepository.findAllByMemberId(memberId);
		List<Integer> resultSubscriptions=new ArrayList<>();
		for(Subscription subscription:subcriptions) {
			resultSubscriptions.add(subscription.getSubscriptionId());
		}
		return resultSubscriptions;
	}

	public String findRefillOccurenceBySubscriptionId(int subscriptionId) {
		
		Subscription sub=subscriptionrepository.findSubscriptionBySubscriptionId(subscriptionId);
		return sub.getRefillOccurence();
		
	
	}
	
}

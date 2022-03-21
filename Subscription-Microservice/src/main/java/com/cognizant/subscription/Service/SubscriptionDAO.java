package com.cognizant.subscription.Service;

import java.util.List;
import java.util.Map;

import com.cognizant.subscription.Model.Subscription;

public interface SubscriptionDAO {

	public Subscription save(Subscription subscription);

	public void delete(int subscriptionid);

	public Map<String, Integer> getAllDrugsOfMember(int subscriptionId);

	public List<Integer> getSubIdByMemberId(long memberId);

	public String findRefillOccurenceBySubscriptionId(int subscriptionId);
}

package com.cognizant.subscription.Repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cognizant.subscription.Model.Subscription;

@Repository
public interface SubscriptionRepository extends CrudRepository<Subscription, Integer>{
	
	void deleteBySubscriptionId(int subscriptionid);
	
	Subscription findBySubscriptionId(int subscriptionId);

	List<Subscription> findAllByMemberId(long memberId);


	Subscription findSubscriptionBySubscriptionId(int subscriptionId);
	
	
}
	
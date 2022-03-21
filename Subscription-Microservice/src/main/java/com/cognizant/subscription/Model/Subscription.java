package com.cognizant.subscription.Model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="subscription")
public class Subscription {

	@Id
	@GeneratedValue
	@Column(name="subscription_id")
	private int subscriptionId;
	
	@Column(name="member_id")
	private long memberId;
	
	@Column(name="subscription_date")
	private Date subscriptionDate;
	
	@Column(name="refill_occurence")
	private String refillOccurence;
	
	@Column(name="member_location")
	private String memberLocation;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	Prescription prescription;

	public int getSubscriptionId() {
		return subscriptionId;
	}

	public void setSubscriptionId(int subscriptionId) {
		this.subscriptionId = subscriptionId;
	}

	public long getMemberId() {
		return memberId;
	}

	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}

	public Date getSubscriptionDate() {
		return subscriptionDate;
	}

	public void setSubscriptionDate(Date subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}

	public String getRefillOccurence() {
		return refillOccurence;
	}

	public void setRefillOccurence(String refillOccurence) {
		this.refillOccurence = refillOccurence;
	}

	public String getMemberLocation() {
		return memberLocation;
	}

	public void setMemberLocation(String memberLocation) {
		this.memberLocation = memberLocation;
	}



	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}


}
package com.cognizant.refill.Interface;


import java.util.Date;
import java.util.List;
import com.cognizant.refill.Model.RefillOrder;

public interface RefillOrderDAO {
	public List<RefillOrder> viewRefillStatus(int subscriptionId) ;
	public RefillOrder save(RefillOrder order);
	public List<RefillOrder> getRefillDuesAsOfDate(Date date, int subscriptionid);
	public List<RefillOrder> viewRefillStatusLatest(int subscription_id);
	public List<RefillOrder> viewAllData();
    public void checkDaily();
	public boolean anyPendings(int subscriptionid) ;
}

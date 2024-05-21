package com.carolina.data;

import com.carolina.model.Order;

public interface OrderRepository {
	
	Order save(Order order);
}

package com.service;

import com.enums.StatusAccess;

public interface AccessFunctions {
	
	StatusAccess accessOn(String client_id, String password);

}

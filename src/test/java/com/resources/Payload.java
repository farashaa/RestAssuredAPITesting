package com.resources;

import java.util.HashMap;

public class Payload {
public static HashMap<String, String> createUserPayload(String name, String job){
	HashMap<String, String> createUserPayload= new HashMap<>();
	createUserPayload.put("name","ainam");
	createUserPayload.put("job", "developer");
	return createUserPayload;
}
}

package com.resources;

import java.util.HashMap;

public class Payload {
public static HashMap<String, String> UserPayload(String name, String job){
	HashMap<String, String> createUserPayload= new HashMap<>();
	createUserPayload.put("name",name);
	createUserPayload.put("job", job);
	return createUserPayload;
}
}

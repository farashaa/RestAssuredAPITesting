package com.resources;

import java.util.HashMap;

public class Payload {
	public static HashMap<String, String> UserPayload(String name, String job){
		HashMap<String, String> createUserPayload= new HashMap<>();
		createUserPayload.put(name,name);
		createUserPayload.put(job, job);
		return createUserPayload;
	}
	public static HashMap<String, String> studentPayload(String firstName, String lastName) {
		HashMap<String, String> createStudentPayload= new HashMap<>();
		createStudentPayload.put(firstName,firstName);
		createStudentPayload.put(lastName,lastName);
		return createStudentPayload;
	}
	public static HashMap<String, String> subjectPayload(String subjectName) {
		HashMap<String, String> createSubjectPayload= new HashMap<>();
		createSubjectPayload.put(subjectName, subjectName);
		return createSubjectPayload;

	}
}

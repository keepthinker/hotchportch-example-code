package com.keepthinker.example.general.classloader;

import java.security.AccessController;
import java.security.PrivilegedAction;

public class SystemProperty {

	public void doStuff() {
		try {
			/*
			 * this will fail even if this class has permission via the policy file
			 * IF any caller does not have permission
			 */
			System.out.println(System.getProperty("java.home")); 
		} catch (Exception e1) {
			System.out.println(e1.getMessage());
		}
		AccessController.doPrivileged(new PrivilegedAction<Boolean>() {
			public Boolean run() {
				try {
					/*
					 * this will be allowed if this class has permission via the policy
					 * file even if no caller has permission
					 */
					System.out.println(System.getProperty("java.home"));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

				return Boolean.TRUE;
			}
		});
	}
}

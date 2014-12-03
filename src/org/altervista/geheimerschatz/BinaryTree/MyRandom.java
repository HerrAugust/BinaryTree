package org.altervista.geheimerschatz.BinaryTree;

import java.util.Calendar;

public class MyRandom {

	private int seed;
	
	public MyRandom(int max) {
		seed = max;
	}
	
	public int nextInt() {
		Calendar c = Calendar.getInstance();
		return (int) (c.getTimeInMillis() % seed);
	}
	
}

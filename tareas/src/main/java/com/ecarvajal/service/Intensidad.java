package com.ecarvajal.service;

public class Intensidad {

	public void start(int tiempo) {

		float z1 = (10 * tiempo) / 100;
		float z2 = (29 * tiempo) / 100;
		float z3 = (34 * tiempo) / 100;
		float z4 = (20 * tiempo) / 100;
		float z5 = (7 * tiempo) / 100;
		System.out.println(" Calculo de intensidades para "+tiempo+" minutos");
		System.out.println(" Tiempo en z1: "+z1);
		System.out.println(" Tiempo en z2: "+z2);
		System.out.println(" Tiempo en z3: "+z3);
		System.out.println(" Tiempo en z4: "+z4);
		System.out.println(" Tiempo en z5: "+z5);
	}
}

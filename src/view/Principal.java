package view;

import controller.ThreadController;
import java.util.concurrent.Semaphore;

public class Principal {
	static int tot = 10;
	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(5);
		for(int i = 1; i <= 10; i++) {
			Thread td = new ThreadController(i, tot, semaforo);
			td.start();
		}
	}

}
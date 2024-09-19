package controller;

import java.util.concurrent.Semaphore;

public class ThreadController extends Thread{ 
	private int tid, distcorr = 500, passo, tot, tempo, pontiro, totiro, tot2, dist = 500, passo2, pontos;
	private Semaphore semaforo, semaforochegada = new Semaphore(1);
	private static int cheg = 1, mult;
	private String[] ordem = {"primeiro", "segundo", "terceiro"};
	
	public ThreadController(int tid,int i, Semaphore semaforo) {
		this.tid = tid;
		this.semaforo = semaforo;
		mult = i;
	}
	
	@Override
	public void run() {
		corrida();
		try {
			semaforo.acquire();
			tiroalvo();
		}catch(Exception e2) {
			e2.printStackTrace();
		}finally {
			semaforo.release();
		}
		bicicleta();
	}
	
	private void corrida() {
		while(tot < distcorr) {
			passo = (int)((Math.random()*5.1) + 20);
			tot += passo;
			if(tot >= distcorr) {
				System.out.println("O atleta "+tid+" andou "+passo+" metros e ja percorreu "+distcorr+" metros.");
			}else {
			System.out.println("O atleta "+tid+" andou "+passo+" metros e ja percorreu "+tot+" metros.");
			}
			try {
				sleep(30);
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("O atleta "+tid+" finalizou o percurso da corrida.");
	}
	
	private void tiroalvo() {
		System.out.println("O atleta "+tid+" chegou ao tiro ao alvo.");
		for(int i = 0; i < 3; i++) {
			pontiro = (int)(Math.random()*10.1);
			System.out.println("O atleta "+tid+" deu o "+ordem[i]+" tiro e ganhou "+pontiro+" pontos.");
			totiro += pontiro;
			tempo = (int)((Math.random()* 2501)+ 500);
			try{
				sleep(tempo);
			}catch(Exception e4){
				e4.printStackTrace();
			}
		}
		System.out.println("O atleta "+tid+" saiu do tiro ao alvo.");
	}
	
	private void bicicleta() {
		System.out.println("O atleta "+tid+" entrou no percurso se ciclismo.");
		while(tot2 < dist) {
			passo2 = (int)((Math.random()* 10.1)+ 30);
			tot2 += passo2;
			if(tot2 >= dist) {
				System.out.println("O atleta "+tid+" pedalou "+passo2+" metros e ja percorreu "+dist+" metros.");
			}else {
				System.out.println("O atleta "+tid+" pedalou "+passo2+" metros e ja percorreu "+tot2+" metros.");
			}
			try {
				sleep(40);
			}catch(Exception e3) {
				e3.printStackTrace();
			}
		}
		try {
			semaforochegada.acquire();
			pontos = totiro + (mult * 10);
			System.out.println("O atleta "+tid+" foi o "+cheg+"º lugar com "+pontos+" pontos.");
			cheg++;
			mult--;
		}catch(Exception f) {
			f.printStackTrace();
		}finally {
			semaforochegada.release();
		}
	}
}
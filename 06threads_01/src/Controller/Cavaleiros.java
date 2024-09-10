//1. Simular em Java:
//4 cavaleiros caminham por um corredor, simultaneamente, de 2 a 4 m por 50 ms. O corredor é
//escuro, tem 2 km e em 500 m, há uma única tocha. O cavaleiro que pegar a tocha, aumenta sua
//velocidade, somando mais 2 m por 50 ms ao valor que já fazia. Em 1,5 km, existe uma pedra
//brilhante. O cavaleiro que pegar a pedra, aumenta sua velocidade, somando mais 2 m por 50 ms
//ao valor que já fazia (O cavaleiro que já detém a tocha não poderá pegar a pedra). Ao final dos 2
//km, abrem uma porta randômica km, os cavaleiros se separam com 4 portas e, um por vez pega
//uma porta aleatória (que não pode repetir) e entra nela. Apenas uma porta leva à saída. As outras
//3 tem monstros que os devoram.

package Controller;

import java.util.concurrent.Semaphore;

public class Cavaleiros extends Thread
{
	int idCavaleiro;
	Semaphore tochaPega;
	Semaphore pedraPega;
	static boolean tocha = false;
	static boolean pedra = false;
	private int anda;
	static boolean [] portas = new boolean [4];
	final int PORTA_CORRETA;
	
	public Cavaleiros (int idCavaleiro, Semaphore tochaPega, Semaphore pedraPega, int portaCorreta)
	{
		this.idCavaleiro = idCavaleiro;
		this.tochaPega = tochaPega;
		this.pedraPega = pedraPega;
		this.PORTA_CORRETA = portaCorreta; 
	}
	
	public void run ()
	{
		cavaleirosAndando();
	}
	
	public void cavaleirosAndando()
	{
		int distanciaTotal = 2000;
		this.anda = (int) ((Math.random() * 3) + 2);
		int distanciaPercorrida = 0;
		int tempo = 50;
		
		while (distanciaPercorrida < distanciaTotal)
		{
			distanciaPercorrida +=anda;
			
			if (distanciaPercorrida >=500 && !tocha)
			{
				pegarTocha();
			}
			
			try 
			{
				sleep(tempo);	
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			
			System.out.println("#" + idCavaleiro + "já andou: " + distanciaPercorrida + "m.>>> " + anda);
		}
			escolhaPorta(idCavaleiro, PORTA_CORRETA);
	}
	
	public void pegarTocha() 
	{
		try
		{
			tochaPega.acquire();
			System.out.println(idCavaleiro + " pegou tocha ");
			
			this.anda +=2;
			this.tocha = true;
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		finally
		{
			tochaPega.release();
		}
	}
	
	public void escolhaPorta (int idCavaleiro, int PORTA_CORRETA)
	{
		int portaEscolhida = (int) ((Math.random()* 4));
		
		while (portas[portaEscolhida])
		{
			portaEscolhida = (int) ((Math.random() * 4));
		}
		
		portas [portaEscolhida] = true;
		
		System.out.println("O #" + idCavaleiro + " cavaleiro está diante das 4 portas");
		
		System.out.println("escolheu a porta: " + portaEscolhida);
		
		if (portaEscolhida == PORTA_CORRETA)
		{
			System.out.println("Sobreviveu");
		}
		else
		{
			System.out.println("Não sobreviveu");
		}
	}
	
	
}

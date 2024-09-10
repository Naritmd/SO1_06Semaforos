package view;

import java.util.concurrent.Semaphore;

import Controller.Cavaleiros;

public class principalCavaleiros 
{
	public static void main (String args[])
	{
		Semaphore tocha = new Semaphore(1);
		Semaphore pedra = new Semaphore(1);
		int portaCorreta = gerarPortaCorreta();
		
		System.out.println("A porta correta é a: " + portaCorreta);
		
		for (int i = 0; i < 4; i++)
		{
			Thread Cavaleiros = new Cavaleiros (i, tocha, pedra, portaCorreta);
			Cavaleiros.start();
		}
	}
	
	public static int gerarPortaCorreta()
	{
		return (int) (Math.random() * 4);
	}
}

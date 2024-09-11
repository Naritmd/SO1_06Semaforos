//2. Um aeroporto tem 2 pistas (norte e sul) e, em cada pista, apenas um avi�o pode fazer a
//decolagem. O procedimento de decolagem tem 4 fases ( taxiar, decolagem e afastamento da �rea).
//A fase de manobra pode durar de 300 a 700 milissegundos A fase de taxiar, de 500 a 1000
//milissegundos. A fase de decolagem, de 600 a 800 milissegundos. A fase de afastamento, de 300 a
//800 milissegundos. O aeroporto re�ne, por ciclo, 12 aeronaves que podem decolar pela pista norte
//ou pela pista sul (decis�o aleat�ria) mas, apenas 2 avi�es podem circular pela �rea de decolagem
//ao mesmo tempo. Fazer uma aplica��o em Java que resolva o problema.

package view;

import java.util.concurrent.Semaphore;
import controller.ThreadAeroporto;

public class ThreadAeroportoPrincipal 
{
	public static void main (String args[])
	{
		Semaphore semaforo = new Semaphore(2);
		
		for (int i = 0; i < 3; i++)
		{
			Thread t = new ThreadAeroporto(semaforo, i);
			t.start();
		}
	}
}

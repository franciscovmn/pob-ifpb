package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import modelo.Carro;


public class Alterar {
	private ObjectContainer manager;

	public Alterar(){
		manager = Util.conectarBanco();
		alterarPotencia();
		Util.desconectar();

		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}

	public void alterarPotencia(){
		//alterar a potencia do motor do carro de placa AAA... 
		Query q = manager.query();
		q.constrain(Carro.class);
		q.descend("placa").constrain("AAA1111");
		List<Carro> resultados = q.execute();
		
		if(resultados.size() > 0){
			Carro c = resultados.getFirst();
			System.out.println("localizou o carro: " + c);
			c.getMotor().setPotencia(1.3);
			manager.store(c);
			manager.commit();
			System.out.println("carro atualizado");
		}
		else
			System.out.println("carro nao localizado");
		
		
	}



	//=================================================
	public static void main(String[] args) {
		new Alterar();
	}
}


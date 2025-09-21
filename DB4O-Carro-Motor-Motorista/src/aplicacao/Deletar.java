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


public class Deletar {
	private ObjectContainer manager;

	public Deletar(){
		manager = Util.conectarBanco();
		apagar();
		Util.desconectar();
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}

	public void apagar(){
		//apagar o carro placa CCC... sem apagar motor e motorista
		Query q = manager.query();
		q.constrain(Carro.class);
		q.descend("placa").constrain("CCC3333");
		List<Carro> resultados = q.execute();
		
		if(resultados.size() > 0){
			Carro c = resultados.getFirst();
			System.out.println("localizou o carro: " + c);
			manager.delete(c);
			manager.commit();
			System.out.println("carro apagado (sem cascata)");
		}
		else
			System.out.println("carro nao localizado");

	}



	//=================================================
	public static void main(String[] args) {
		new Deletar();
	}
}


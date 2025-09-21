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
import modelo.Motor;
import modelo.Motorista;


public class Listar {
	private ObjectContainer manager;

	public Listar(){
		manager = Util.conectarBanco();
		listar();
		Util.desconectar();
		
		System.out.println("\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}

	public void listar(){
		System.out.println("\n---listagem de carros:");
		
		Query q;
		
		q = manager.query();
		q.constrain(Carro.class);
		List<Carro> carros = q.execute();
		for(Carro c: carros){
			System.out.println(c);
		}
		
		
		System.out.println("\n---listagem de motores:");
		q = manager.query();
		q.constrain(Motor.class);
		List<Motor> motores = q.execute();
		for(Motor m: motores){
			System.out.println(m);
		}
		
		
		System.out.println("\n---listagem de motoristas:");
		q = manager.query();
		q.constrain(Motorista.class);
		List<Motorista> motoristas = q.execute();
		for(Motorista m: motoristas){
			System.out.println(m);
		}
	}
	




	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}


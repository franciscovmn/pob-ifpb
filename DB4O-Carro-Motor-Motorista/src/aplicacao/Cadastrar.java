package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import com.db4o.ObjectContainer;

import modelo.Carro;
import modelo.Motor;
import modelo.Motorista;


public class Cadastrar {
	private ObjectContainer manager;

	public Cadastrar(){
		manager = Util.conectarBanco();
		cadastrar();
		Util.desconectar();
		
		System.out.println("fim da aplicacao");
	}


	public void cadastrar(){
		System.out.println("cadastrando...");
		Motor motor;
		Motorista motorista;
		Carro carro;
		
		motor = new Motor("firefly", 1.0);
		motorista = new Motorista("00001", "Joao da Silva");
		carro = new Carro("AAA1111", motor, motorista);
		manager.store(carro);
		manager.commit();
		
		motor = new Motor("duratec", 2.0);
		motorista = new Motorista("00002", "Maria do Carmo");
		carro = new Carro("BBB2222", motor, motorista);
		manager.store(carro);
		manager.commit();
		
		motor = new Motor("ferrari", 5.0);
		motorista = new Motorista("00003", "Julio de Castro");
		carro = new Carro("CCC3333", motor, motorista);
		manager.store(carro);
		manager.commit();
		
		System.out.println("cadastro concluido.");
	}	


	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}



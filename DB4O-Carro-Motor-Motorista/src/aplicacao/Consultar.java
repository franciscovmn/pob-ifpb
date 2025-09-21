package aplicacao;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import com.db4o.ObjectContainer;


public class Consultar {
	private ObjectContainer manager;

	public Consultar(){
		manager = Util.conectarBanco();
		consultar();
		Util.desconectar();
		
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}

	public void consultar(){
		System.out.println("\n---listar carros com placa iniciando por A");

		
		System.out.println("\n---listar carros com motor de potencia >= 2.0, ordenado pela potencia");

	}
	
	//=================================================
	public static void main(String[] args) {
		new Consultar();
	}
}


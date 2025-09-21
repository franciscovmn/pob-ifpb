package appconsole;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Query;

import modelo.Pessoa;

public class Deletar {
	private ObjectContainer manager;

	public Deletar() {
		manager = Util.conectarBanco();

		//localizar joana
		Query q = manager.query();
		q.constrain(Pessoa.class);
		q.descend("nome").constrain("joana");
		List<Pessoa> resultados = q.execute(); // select p from Pessoa p where p.nome="maria"

		if (resultados.size() > 0) {
			Pessoa p = resultados.get(0);
			manager.delete(p);			//apagar joana
			manager.commit();
			System.out.println("apagou grafo da joana (em cascata)");
		} 
		else
			System.out.println("joana inexistente");

		
		Util.desconectar();
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}


	// =================================================
	public static void main(String[] args) {
		new Deletar();
	}
}

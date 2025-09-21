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

import modelo.Aluno;
import modelo.Pessoa;
import modelo.Telefone;


public class Listar {
	private ObjectContainer manager;

	public Listar(){
		manager = Util.conectarBanco();
		
		System.out.println("\n---listagem das pessoas:");
		Query q1 = manager.query();
		q1.constrain(Pessoa.class);  				
		List<Pessoa> pessoas = q1.execute();
		for(Pessoa p: pessoas)
			System.out.println(p);

		
		System.out.println("\n---listagem dos alunos:");
		Query q2 = manager.query();
		q2.constrain(Aluno.class);  				
		List<Aluno> alunos = q2.execute();
		for(Aluno a: alunos)
			System.out.println(a);

		
		System.out.println("\n---listagem dos telefones:");
		Query q3 = manager.query();
		q3.constrain(Telefone.class);  				
		List<Telefone> telefones = q3.execute();
		for(Telefone t: telefones)
			System.out.println(t);

		Util.desconectar();

		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}






	//=================================================
	public static void main(String[] args) {
		new Listar();
	}
}


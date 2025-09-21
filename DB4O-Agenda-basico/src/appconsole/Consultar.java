package appconsole;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Aluno;
import modelo.Pessoa;
import modelo.Telefone;

public class Consultar {
	private ObjectContainer manager;

	public Consultar() {
		manager = Util.conectarBanco();
		consultar();
		Util.desconectar();
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}

	public void consultar() {
		System.out.println("***********************************");
		System.out.println("*****Consultas da classe Pessoa****");
		System.out.println("***********************************");
		List<Pessoa> pessoas;
		List<Telefone> telefones;
		Query q;

		System.out.println("\n---listar pessoas em ordem alfabetica");
		q = manager.query();
		q.constrain(Pessoa.class);
		q.descend("nome").orderAscending();
		pessoas = q.execute();
		for (Pessoa p : pessoas)
			System.out.println(p);

		System.out.println("\n---listar pessoas que nasceram no mes 02");
		q = manager.query();
		q.constrain(Pessoa.class);
		q.descend("dtnascimento").constrain("/02/").contains();
		q.descend("nome").orderAscending();
		pessoas = q.execute();
		for (Pessoa p : pessoas)
			System.out.println(p);

		System.out.println("\n---listar telefones fixos");
		q = manager.query();
		q.constrain(Telefone.class);
		q.descend("numero").constrain("3").startsWith(true);
		telefones = q.execute();
		for (Telefone t : telefones)
			System.out.println(t);

		System.out.println("\n---Total de pessoas");
		q = manager.query();
		q.constrain(Pessoa.class);
		int cont = q.execute().size();
		System.out.println(cont);

		System.out.println("\n---listar pessoas com 2 telefones (filtro)");
		q = manager.query();
		q.constrain(Pessoa.class);
		q.constrain(new Filtro1(2));
		pessoas = q.execute();
		for (Pessoa p : pessoas)
			System.out.println(p);

		System.out.println("\n**********************************");
		System.out.println("*****Consultas da classe Aluno****");
		System.out.println("**********************************");
		List<Aluno> alunos;

		System.out.println("\n---listar telefones de alunos");
		q = manager.query();
		q.constrain(Telefone.class);
		q.descend("pessoa").constrain(Aluno.class);
		telefones = q.execute();
		for (Telefone t : telefones)
			System.out.println(t);

		System.out.println("\n---listar alunos com nota 10 (sem filtro)");
		q = manager.query();
		q.constrain(Aluno.class);
		q.descend("nota").constrain(10.0);
		alunos = q.execute();
		for (Aluno a : alunos)
			System.out.println(a);

		System.out.println("\n---listar alunos com nota 10 (com filtro)");
		q = manager.query();
		q.constrain(Aluno.class);
		// q.descend("nota").constrain(10.0);
		q.constrain(new Filtro2());
		alunos = q.execute();
		for (Aluno a : alunos)
			System.out.println(a);

		System.out.println("\n---listar alunos que nasceram no ano 1990");
		q = manager.query();
		q.constrain(Aluno.class);
		q.descend("dtnascimento").constrain("1990").endsWith(true);
		alunos = q.execute();
		for (Aluno p : alunos)
			System.out.println(p);

		System.out.println("\n---Total de alunos");
		q = manager.query();
		q.constrain(Aluno.class);
		cont = q.execute().size();
		System.out.println(cont);

	}

	public static void main(String[] args) {
		new Consultar();
	}
}

//classe interna 
class Filtro1 implements Evaluation {

	private int n;

	public Filtro1(int n) {
		this.n = n;
	}

	public void evaluate(Candidate candidate) {
		// obter cada objeto da classe Pessoa que esta no banco
		Pessoa p = (Pessoa) candidate.getObject();

		if (p.getTelefones().size() == n)
			candidate.include(true); // incluir objeto no resultado
		else
			candidate.include(false); // excluir objeto do resultado
	}
}

class Filtro2 implements Evaluation {
	public void evaluate(Candidate candidate) {
		// obter cada objeto da classe Aluno que esta no banco
		Aluno a = (Aluno) candidate.getObject();
		if (a.getNota() == 10.0)
			candidate.include(true); // incluir objeto no resultado
		else
			candidate.include(false); // excluir objeto do resultado
	}
}

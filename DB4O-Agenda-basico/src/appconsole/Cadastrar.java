package appconsole;
/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import com.db4o.ObjectContainer;

import modelo.Aluno;
import modelo.Pessoa;
import modelo.Telefone;


public class Cadastrar {
	private ObjectContainer manager;

	public Cadastrar(){
		manager = Util.conectarBanco();
		
		System.out.println("cadastrando...");
		Pessoa p;
	
		p = new Pessoa("joao");
		p.setDtnascimento("01/01/1980");
		p.adicionar(new Telefone("88880000"));
		p.adicionar(new Telefone("88881100"));
		p.setApelidos(new String[] {"jo", "joaozinho"}  );
		
		manager.store(p);	
		manager.commit();

		p = new Pessoa("maria");
		p.setDtnascimento("02/02/1980");
		p.adicionar(new Telefone("87882200"));
		p.adicionar(new Telefone("87003300"));
		p.adicionar(new Telefone("32470000"));
		p.setApelidos(new String[] {"mary", "mar"}  );

		manager.store(p);
		manager.commit();

		p = new Pessoa("jose");
		p.setDtnascimento("01/01/1990");
		p.adicionar(new Telefone("87884400"));
		p.setApelidos(new String[] {"ze"}  );

		manager.store(p);		
		manager.commit();

		p = new Aluno("paulo",9);
		p.setDtnascimento("02/02/1990");
		p.adicionar(new Telefone("87885500"));
		p.setApelidos(new String[] {"paulao"}  );

		manager.store(p);		
		manager.commit();

		p = new Aluno("ana",10);
		p.setDtnascimento("02/04/1995");

		manager.store(p);		
		manager.commit();
		
		
		System.out.println("cadastrou.");
		Util.desconectar();
		System.out.println("fim da appconsole");
	}

	

	//=================================================
	public static void main(String[] args) {
		new Cadastrar();
	}
}



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
import modelo.Telefone;


public class Alterar {
	private ObjectContainer manager;

	public Alterar(){
		manager = Util.conectarBanco();
		
		//localizar pessoa com nome joao
		Query q = manager.query();
		q.constrain(Pessoa.class);  				
		q.descend("nome").constrain("joao");		 
		List<Pessoa> resultados = q.execute(); // select p from Pessoa p where p.nome="joao"
		
		if(resultados.size()>0) {
			Pessoa p =  resultados.get(0);
			p.setNome("joana");			//alterar o nome
			
			Telefone t = p.getTelefones().getLast(); 
			p.remover(t);  			//remover telefone do joao
			t.setPessoa(null);	 	//remover joao do telefone 

			manager.store(p);
			manager.store(t);		//verificar (listar) se o telefone esta orfao no banco !!
			//manager.delete(t);	//apagar orfao no banco

			manager.commit();
			System.out.println("alterou grafo do joao (nome e telefone)");
		}
		else
			System.out.println("joao inexistente");
		
		Util.desconectar();
		System.out.println("\n\n aviso: feche sempre o plugin OME antes de executar aplicação");
	}

	public void atualizar(){

	}



	//=================================================
	public static void main(String[] args) {
		new Alterar();
	}
}


package dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Cidade;
import util.Connection;

public class CidadeDAO {

	Connection conexao = new Connection();

	@SuppressWarnings("unchecked")
	public List<Cidade> listarCidadeByNome(String paramPesquisa) {
		List<Cidade> listaResultado = new ArrayList<Cidade>();

		EntityManager em = conexao.getConexao();

		em.getTransaction().begin();

		Query query = em.createQuery("SELECT l FROM Cidade l where l.nome like :paramPesquisa");
		query.setParameter("paramPesquisa", "%" + paramPesquisa + "%");
		listaResultado.addAll(query.getResultList());

		em.getTransaction().commit();

		em.close();

		return listaResultado;
	}

	public Cidade buscarCidadeById(Integer idCidade) {

		Cidade cidade;

		EntityManager em = conexao.getConexao();

		em.getTransaction().begin();

		cidade = em.find(Cidade.class, idCidade);

		em.getTransaction().commit();

		em.close();

		return cidade;
	}
}

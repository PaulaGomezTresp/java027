package es.curso.repositories;

import java.util.List;

import es.curso.negocio.Ordenador;

public interface OrdenadorRepository {

	List<Ordenador> buscarTodos();
	void insertar (Ordenador ordenador);
}

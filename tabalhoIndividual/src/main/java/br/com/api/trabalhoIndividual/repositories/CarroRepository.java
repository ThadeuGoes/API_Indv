package br.com.api.trabalhoIndividual.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.trabalhoIndividual.entities.Carro;

@Repository
public interface CarroRepository extends JpaRepository<Carro, Integer> {

	@Query(value = "select count(*) from carro ", nativeQuery = true)
	public Integer contar();

}

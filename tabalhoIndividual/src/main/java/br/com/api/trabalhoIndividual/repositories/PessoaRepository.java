package br.com.api.trabalhoIndividual.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.api.trabalhoIndividual.entities.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

	@Query(value = "select count(*) from pessoa ", nativeQuery = true)
	public Integer contar();

	@Query(value = "select * from pessoa u where email = :email", nativeQuery = true)
	public Optional<Pessoa> findByEmail(@Param("email") String email);

}

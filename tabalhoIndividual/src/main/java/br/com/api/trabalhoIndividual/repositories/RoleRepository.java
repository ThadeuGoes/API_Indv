package br.com.api.trabalhoIndividual.repositories;

import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.api.trabalhoIndividual.entities.Role;
import br.com.api.trabalhoIndividual.enums.TipoRoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

	Optional<Role> findByName(TipoRoleEnum roleUser);

	@Query(value = "select r.* from pessoa p \r\n" + "inner join pessoa_role pr on p.id = pr.pessoa_id\r\n"
			+ "inner join roles r on pr.role_id = r.id\r\n" + "where p.email = :email", nativeQuery = true)
	Set<Role> roles(String email);

}
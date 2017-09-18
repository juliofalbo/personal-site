package juliosilveiradev.site.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import juliosilveiradev.site.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>
{

	@Query("from Usuario u where u.username=:username")
	public Usuario getUsuarioByUsername(@Param("username") String username);

}

package juliosilveiradev.site.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import juliosilveiradev.site.model.Post;

public interface PostRepository extends CrudRepository<Post, Long>
{

	@Query("from Post d where d.publicado = 1")
	public Iterable<Post> getPostPublicados();

	@Query("from Post d where d.publicado = 0")
	public Iterable<Post> getPostNaoPublicados();

}

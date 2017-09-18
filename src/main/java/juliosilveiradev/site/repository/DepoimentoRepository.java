package juliosilveiradev.site.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import juliosilveiradev.site.model.Depoimento;

public interface DepoimentoRepository extends CrudRepository<Depoimento, Long> {

	@Query("from Depoimento d where d.aprovado is null")
	public Iterable<Depoimento> getDepoimentoSemAvaliacao();

	@Query("from Depoimento d where d.aprovado = 1")
	public Iterable<Depoimento> getDepoimentoAprovados();

	@Query("from Depoimento d where d.aprovado = 0")
	public Iterable<Depoimento> getDepoimentoNaoAprovados();

}

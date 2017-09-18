package juliosilveiradev.site.facades;

import java.math.BigInteger;

import juliosilveiradev.site.facades.interfaces.FacadeInterface;
import juliosilveiradev.site.model.Comentario;
import juliosilveiradev.site.model.Post;
import juliosilveiradev.site.utils.CriptografiaUtils;

public class ComentarioFacade implements FacadeInterface
{

	private Comentario comentario = null;

	public ComentarioFacade(Comentario comentario)
	{
		this.comentario = comentario;
	}

	public void definirPost(Post post)
	{
		this.comentario.setPost(post);
	}

	@Override
	public void criptografarId()
	{
		this.comentario.setIdCriptografado(CriptografiaUtils.getInstance()
				.criptografarInteiro(BigInteger.valueOf(this.comentario.getId())));
	}

}

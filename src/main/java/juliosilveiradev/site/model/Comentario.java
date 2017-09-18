package juliosilveiradev.site.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Comentario
{

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	@JoinColumn(name = "id_post")
	private Post post;

	private String remetente;
	private String email;
	private String site;
	private String mensagem;

	//Objetos não persistidos

	@Transient
	private BigInteger idCriptografado;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Post getPost()
	{
		return post;
	}

	public void setPost(Post post)
	{
		this.post = post;
	}

	public String getRemetente()
	{
		return remetente;
	}

	public void setRemetente(String remetente)
	{
		this.remetente = remetente;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getSite()
	{
		return site;
	}

	public void setSite(String site)
	{
		this.site = site;
	}

	public String getMensagem()
	{
		return mensagem;
	}

	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}

	public BigInteger getIdCriptografado()
	{
		return idCriptografado;
	}

	public void setIdCriptografado(BigInteger idCriptografado)
	{
		this.idCriptografado = idCriptografado;
	}

}

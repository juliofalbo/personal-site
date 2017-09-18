package juliosilveiradev.site.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Portfolio
{

	@Id
	@GeneratedValue
	private Long id;

	private String nome;
	private String link;
	private String tipo;
	private String filtro;
	private String icone;
	private String foto;

	//Objetos não persistidos

	@Transient
	private BigInteger idCriptografado;
	
	@Transient
	private String nomePlic;
	
	@Transient
	private String linkPlic;
	
	@Transient
	private String tipoPlic;
	
	@Transient
	private String filtroPlic;
	
	@Transient
	private String iconePlic;
	
	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public BigInteger getIdCriptografado()
	{
		return idCriptografado;
	}

	public void setIdCriptografado(BigInteger idCriptografado)
	{
		this.idCriptografado = idCriptografado;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getTipo()
	{
		return tipo;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	public String getFiltro()
	{
		return filtro;
	}

	public void setFiltro(String filtro)
	{
		this.filtro = filtro;
	}

	public String getIcone()
	{
		return icone;
	}

	public void setIcone(String icone)
	{
		this.icone = icone;
	}

	public String getFoto()
	{
		return foto;
	}

	public void setFoto(String foto)
	{
		this.foto = foto;
	}

	public String getNomePlic()
	{
		return nomePlic;
	}

	public void setNomePlic(String nomePlic)
	{
		this.nomePlic = nomePlic;
	}

	public String getLinkPlic()
	{
		return linkPlic;
	}

	public void setLinkPlic(String linkPlic)
	{
		this.linkPlic = linkPlic;
	}

	public String getTipoPlic()
	{
		return tipoPlic;
	}

	public void setTipoPlic(String tipoPlic)
	{
		this.tipoPlic = tipoPlic;
	}

	public String getFiltroPlic()
	{
		return filtroPlic;
	}

	public void setFiltroPlic(String filtroPlic)
	{
		this.filtroPlic = filtroPlic;
	}

	public String getIconePlic()
	{
		return iconePlic;
	}

	public void setIconePlic(String iconePlic)
	{
		this.iconePlic = iconePlic;
	}

}

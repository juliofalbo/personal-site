package juliosilveiradev.site.model;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Post
{

	@Id
	@GeneratedValue
	private Long id;

	private String html;
	private String titulo;
	private Date dataCriacao;
	private Date dataPostagem;
	private Long likes;
	private String textoInicial;
	private String imagem;
	private Boolean publicado;

	@OneToOne
	@JoinColumn(name = "criador")
	private Usuario criador;

	@OneToMany(mappedBy = "post", targetEntity = Comentario.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Comentario> comentarios;

	//Objetos não persistidos

	@Transient
	private BigInteger idCriptografado;

	@Transient
	private ZonedDateTime dataCriacaoZDT;

	@Transient
	private ZonedDateTime dataPostagemZDT;

	@Transient
	private String dataCriacaoFormatada;

	@Transient
	private String dataPostagemFormatada;
	
	@Transient
	private Long qtdDeComentarios;
	
	@Transient
	private String htmlPlic;
	
	@Transient
	private String tituloPlic;
	
	@Transient
	private String textoInicialPlic;

	//G&S

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getHtml()
	{
		return html;
	}

	public void setHtml(String html)
	{
		this.html = html;
	}

	public Long getLikes()
	{
		return likes;
	}

	public void setLikes(Long likes)
	{
		this.likes = likes;
	}

	public String getTextoInicial()
	{
		return textoInicial;
	}

	public void setTextoInicial(String textoInicial)
	{
		this.textoInicial = textoInicial;
	}

	public String getImagem()
	{
		return imagem;
	}

	public void setImagem(String imagem)
	{
		this.imagem = imagem;
	}

	public Boolean getPublicado()
	{
		return publicado;
	}

	public void setPublicado(Boolean publicado)
	{
		this.publicado = publicado;
	}

	public String getDataCriacaoFormatada()
	{
		return dataCriacaoFormatada;
	}

	public void setDataCriacaoFormatada(String dataCriacaoFormatada)
	{
		this.dataCriacaoFormatada = dataCriacaoFormatada;
	}

	public String getDataPostagemFormatada()
	{
		return dataPostagemFormatada;
	}

	public void setDataPostagemFormatada(String dataPostagemFormatada)
	{
		this.dataPostagemFormatada = dataPostagemFormatada;
	}

	public Date getDataCriacao()
	{
		return dataCriacao;
	}

	public void setDataCriacao(Date dataCriacao)
	{
		this.dataCriacao = dataCriacao;
	}

	public Date getDataPostagem()
	{
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem)
	{
		this.dataPostagem = dataPostagem;
	}

	public Usuario getCriador()
	{
		return criador;
	}

	public void setCriador(Usuario criador)
	{
		this.criador = criador;
	}

	public ZonedDateTime getDataCriacaoZDT()
	{
		return dataCriacaoZDT;
	}

	public void setDataCriacaoZDT(ZonedDateTime dataCriacaoZDT)
	{
		this.dataCriacaoZDT = dataCriacaoZDT;
	}

	public ZonedDateTime getDataPostagemZDT()
	{
		return dataPostagemZDT;
	}

	public void setDataPostagemZDT(ZonedDateTime dataPostagemZDT)
	{
		this.dataPostagemZDT = dataPostagemZDT;
	}

	public BigInteger getIdCriptografado()
	{
		return idCriptografado;
	}

	public void setIdCriptografado(BigInteger idCriptografado)
	{
		this.idCriptografado = idCriptografado;
	}

	public String getTitulo()
	{
		return titulo;
	}

	public void setTitulo(String titulo)
	{
		this.titulo = titulo;
	}

	public List<Comentario> getComentarios()
	{
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios)
	{
		this.comentarios = comentarios;
	}

	public Long getQtdDeComentarios()
	{
		return qtdDeComentarios;
	}

	public void setQtdDeComentarios(Long qtdDeComentarios)
	{
		this.qtdDeComentarios = qtdDeComentarios;
	}

	public String getHtmlPlic()
	{
		return htmlPlic;
	}

	public void setHtmlPlic(String htmlPlic)
	{
		this.htmlPlic = htmlPlic;
	}

	public String getTituloPlic()
	{
		return tituloPlic;
	}

	public void setTituloPlic(String tituloPlic)
	{
		this.tituloPlic = tituloPlic;
	}

	public String getTextoInicialPlic()
	{
		return textoInicialPlic;
	}

	public void setTextoInicialPlic(String textoInicialPlic)
	{
		this.textoInicialPlic = textoInicialPlic;
	}

}

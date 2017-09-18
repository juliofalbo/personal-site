package juliosilveiradev.site.model;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Depoimento
{

	@Id
	@GeneratedValue
	private Long id;

	private String remetente;
	private String email;

	private Date data;

	private String celular;
	private String mensagem;
	private String foto;
	private String cargo;
	private String empresa;
	private String site;
	private String projeto;
	private Boolean aprovado;

	//Objetos não persistidos

	@Transient
	private BigInteger idCriptografado;

	@Transient
	private String dataFormatada;

	@Transient
	private ZonedDateTime dataZDT;

	//G&S

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
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

	public String getCelular()
	{
		return celular;
	}

	public void setCelular(String celular)
	{
		this.celular = celular;
	}

	public String getMensagem()
	{
		return mensagem;
	}

	public void setMensagem(String mensagem)
	{
		this.mensagem = mensagem;
	}

	public String getFoto()
	{
		return foto;
	}

	public void setFoto(String foto)
	{
		this.foto = foto;
	}

	public String getCargo()
	{
		return cargo;
	}

	public void setCargo(String cargo)
	{
		this.cargo = cargo;
	}

	public String getEmpresa()
	{
		return empresa;
	}

	public void setEmpresa(String empresa)
	{
		this.empresa = empresa;
	}

	public String getSite()
	{
		return site;
	}

	public void setSite(String site)
	{
		this.site = site;
	}

	public String getProjeto()
	{
		return projeto;
	}

	public void setProjeto(String projeto)
	{
		this.projeto = projeto;
	}

	public Boolean getAprovado()
	{
		return aprovado;
	}

	public void setAprovado(Boolean aprovado)
	{
		this.aprovado = aprovado;
	}

	public Date getData()
	{
		return data;
	}

	public void setData(Date data)
	{
		this.data = data;
	}

	public String getDataFormatada()
	{
		return dataFormatada;
	}

	public void setDataFormatada(String dataFormatada)
	{
		this.dataFormatada = dataFormatada;
	}

	public ZonedDateTime getDataZDT()
	{
		return dataZDT;
	}

	public void setDataZDT(ZonedDateTime dataZDT)
	{
		this.dataZDT = dataZDT;
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

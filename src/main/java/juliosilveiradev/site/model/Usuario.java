package juliosilveiradev.site.model;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Usuario
{

	@Id
	@GeneratedValue
	private Long id;

	private String username;
	private String password;
	private String nome;
	private Boolean enabled;

	//Objetos não persistidos

	@Transient
	private BigInteger idCriptografado;

	//G&S

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Boolean getEnabled()
	{
		return enabled;
	}

	public void setEnabled(Boolean enabled)
	{
		this.enabled = enabled;
	}

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

}

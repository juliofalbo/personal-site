package juliosilveiradev.site.utils;

import java.math.BigInteger;

import org.jasypt.util.numeric.BasicIntegerNumberEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;

import juliosilveiradev.site.constantes.Default;

public class CriptografiaUtils
{

	private static CriptografiaUtils instance;

	public static synchronized CriptografiaUtils getInstance()
	{
		if (instance == null)
		{
			instance = new CriptografiaUtils();
		}

		return instance;
	}

	public String criptografarTexto(String texto)
	{
		BasicTextEncryptor encryptor = getTextEncryptor();
		String encrypted = encryptor.encrypt(texto);
		return encrypted;
	}

	public String desriptografarTexto(String texto)
	{
		BasicTextEncryptor encryptor = getTextEncryptor();
		String decrypted = encryptor.decrypt(texto);
		return decrypted;
	}

	public BigInteger criptografarInteiro(BigInteger inteiro)
	{
		BasicIntegerNumberEncryptor encryptor = getIntegerEncryptor();
		BigInteger encrypted = encryptor.encrypt(inteiro);
		return encrypted;
	}

	public BigInteger desriptografarInteiro(BigInteger inteiro)
	{
		BasicIntegerNumberEncryptor encryptor = getIntegerEncryptor();
		BigInteger decrypted = encryptor.decrypt(inteiro);
		return decrypted;
	}

	private BasicIntegerNumberEncryptor getIntegerEncryptor()
	{
		BasicIntegerNumberEncryptor encryptor = new BasicIntegerNumberEncryptor();
		encryptor.setPassword(Default.CHAVE_CRIPTOGRAFIA_DEFAULT);
		return encryptor;
	}

	private BasicTextEncryptor getTextEncryptor()
	{
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(Default.CHAVE_CRIPTOGRAFIA_DEFAULT);
		return encryptor;
	}

}

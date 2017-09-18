package juliosilveiradev.site.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.channels.FileChannel;

import org.springframework.web.multipart.MultipartFile;

public class JUtils
{

	private static JUtils instance;

	public static synchronized JUtils getInstance()
	{
		if (instance == null)
		{
			instance = new JUtils();
		}

		return instance;
	}

	/**
	 * Método responsável por converter um MultipartFile para File
	 * 
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public File convert(MultipartFile file) throws IOException
	{
		File convFile = new File(file.getOriginalFilename());
		convFile.createNewFile();
		FileOutputStream fos = new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();

		return convFile;
	}

	/**
	 * Método responsável por salvar um arquivo
	 * 
	 * @param in
	 * @param out
	 */
	public void copyFile(File in, File out)
	{
		try
		{
			FileChannel sourceChannel = new FileInputStream(in).getChannel();
			FileChannel destinationChannel = new FileOutputStream(out).getChannel();
			sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);
			sourceChannel.close();
			destinationChannel.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}

	/**
	 * Método responsável por criar um diretório
	 * 
	 * @return String
	 */
	public void criarDiretorio(String novoDiretorio)
	{
		if (novoDiretorio.contains("/"))
		{
			novoDiretorio = novoDiretorio.replace("/", "\\");
		}

		if (!new File(novoDiretorio).exists())
		{
			(new File(novoDiretorio)).mkdirs();
		}
	}

	/**
	 * Método responsável salvar um arquivo e recuperar o caminho a ser salvo no
	 * banco de dados
	 * 
	 * @return String
	 */
	public String salvarArquivoRetornandoCaminhoDoBD(String diretorioParaSalvarOArquivo,
			MultipartFile file)
	{

		File fileConvertido = null;
		String caminhoFotoBanco = "";
		try
		{
			fileConvertido = convert(file);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		if (fileConvertido != null)
		{
			String localWorkspace = System.getProperty("user.dir");
			String nomePastaFotos = diretorioParaSalvarOArquivo;
			String localPastaFotos = localWorkspace + "\\src\\main\\resources\\static\\"
					+ nomePastaFotos;
			criarDiretorio(localPastaFotos);
			String uploadFileName = fileConvertido.getName();

			copyFile(fileConvertido, new File(localPastaFotos + "\\" + uploadFileName));

			caminhoFotoBanco = nomePastaFotos + "/" + uploadFileName;
		}
		return caminhoFotoBanco;

	}

	public Object atualizarObjeto(Object objetoEditado, Object objetoBanco)
	{
		if (objetoEditado.getClass().getTypeName().equals(objetoBanco.getClass().getTypeName()))
		{
			Field[] campos = objetoEditado.getClass().getDeclaredFields();
			for (int i = 0; i < campos.length; i++)
			{
				Field campo = campos[i];
				campo.setAccessible(true);
				try
				{
					Object campoEditado = campo.get(objetoEditado);
					Object campoBanco = campo.get(objetoBanco);

					if (campoEditado != null && !campoEditado.equals(campoBanco))
					{
						campo.set(objetoBanco, campoEditado);
					}
				}
				catch (IllegalArgumentException e)
				{
					e.printStackTrace();
				}
				catch (IllegalAccessException e)
				{
					e.printStackTrace();
				}
				catch (SecurityException e)
				{
					e.printStackTrace();
				}

			}
			return objetoBanco;
		}
		else
		{
			return null;
		}

	}

}

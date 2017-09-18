package juliosilveiradev.site.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import juliosilveiradev.site.model.Usuario;
import juliosilveiradev.site.repository.UsuarioRepository;

public class SiteUtils
{

	private static SiteUtils instance;

	public static synchronized SiteUtils getInstance()
	{
		if (instance == null)
		{
			instance = new SiteUtils();
		}

		return instance;
	}

	public String getUsernameDoUsuarioLogado()
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		return name;
	}

	public Usuario getUsuarioLogado(UsuarioRepository usuarioRepository)
	{
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Usuario usuarioLogado = usuarioRepository.getUsuarioByUsername(name);
		return usuarioLogado;
	}

	public Boolean validarArquivoUpload(MultipartFile arquivo)
	{
		if (arquivo != null && arquivo.getSize() > 0)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

}

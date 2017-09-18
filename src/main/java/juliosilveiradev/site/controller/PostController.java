package juliosilveiradev.site.controller;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import juliosilveiradev.site.constantes.Default;
import juliosilveiradev.site.constantes.Template;
import juliosilveiradev.site.facades.PostFacade;
import juliosilveiradev.site.model.Post;
import juliosilveiradev.site.repository.PostRepository;
import juliosilveiradev.site.repository.UsuarioRepository;
import juliosilveiradev.site.utils.CriptografiaUtils;
import juliosilveiradev.site.utils.SiteUtils;

@Controller
public class PostController
{

	@Autowired
	PostRepository postRepository;

	@Autowired
	UsuarioRepository usuarioRepository;

	@RequestMapping(value = "/admin/salvarPost", method = { RequestMethod.POST })
	public String salvarPost(@ModelAttribute("novoPost") Post novoPost, Model model,
			RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file)
	{

		if (novoPost != null)
		{

			PostFacade facade = new PostFacade(novoPost);
			facade.validarHtml();
			facade.setarDataAtualNaCriacao();
			facade.naoPublicar();
			facade.setarUsuarioLogadoComoCriador(usuarioRepository);
			facade.zerarLikes();

			if (SiteUtils.getInstance().validarArquivoUpload(file))
			{
				facade.salvarArquivoUpload(file);
			}

			String retorno = "";

			try
			{
				postRepository.save(novoPost);

				retorno = "Post " + novoPost.getHtml() + " criado! Mais um pra conta.";
			}
			catch (Exception e)
			{
				e.printStackTrace();
				retorno = Default.MENSAGEM_ERROR_DEFAULT;
			}

			redirectAttributes.addFlashAttribute("retorno", retorno);
		}

		return Template.REDIRECT_ADMIN_BLOG;
	}

	@RequestMapping(value = "/admin/editarPost", method = { RequestMethod.POST })
	public String editarPost(@ModelAttribute("postEditavel") Post postEditavel, Model model,
			RedirectAttributes redirectAttributes, @RequestParam("editFile") MultipartFile editFile)
	{

		if (postEditavel != null)
		{
			Post postBanco = postRepository.findOne(postEditavel.getId());

			PostFacade facade = new PostFacade(postBanco);
			facade.atualizarObjeto(postEditavel);
			facade.validarHtml();

			if (SiteUtils.getInstance().validarArquivoUpload(editFile))
			{
				facade.salvarArquivoUpload(editFile);
			}

			String retorno = "";

			try
			{
				postRepository.save(postBanco);
				retorno = "Post " + postEditavel.getHtml() + " editado com sucesso!.";
			}
			catch (Exception e)
			{
				e.printStackTrace();
				retorno = Default.MENSAGEM_ERROR_DEFAULT;
			}

			redirectAttributes.addFlashAttribute("retorno", retorno);

		}

		return Template.REDIRECT_ADMIN_BLOG;
	}

	@RequestMapping("/admin/publicarPost/{idCriptografado}")
	public String publicarPost(@PathVariable BigInteger idCriptografado, Model model,
			RedirectAttributes redirectAttributes)
	{

		String retorno = "";

		BigInteger id = null;
		try
		{
			id = CriptografiaUtils.getInstance().desriptografarInteiro(idCriptografado);
		}
		catch (Exception e)
		{
			redirectAttributes.addFlashAttribute("retorno", Default.MENSAGEM_DESCRIPTOGRAFIA_ID);
			return Template.REDIRECT_ADMIN_BLOG;
		}

		Post postParaPublicar = postRepository.findOne(id.longValue());

		if (postParaPublicar != null)
		{
			PostFacade facade = new PostFacade(postParaPublicar);
			facade.setarDataAtualNaPostagem();
			facade.publicar();

			try
			{
				postRepository.save(postParaPublicar);
				retorno = "O post " + postParaPublicar.getHtml() + " foi publicado.";
			}
			catch (Exception e)
			{
				e.printStackTrace();
				retorno = Default.MENSAGEM_ERROR_DEFAULT;
			}

			redirectAttributes.addFlashAttribute("retorno", retorno);

		}
		else
		{
			redirectAttributes.addFlashAttribute("retorno", Default.MENSAGEM_DESCRIPTOGRAFIA_ID);
		}

		return Template.REDIRECT_ADMIN_BLOG;
	}

	@RequestMapping("/admin/deletarPost/{idCriptografado}")
	public String deletarPost(@PathVariable BigInteger idCriptografado, Model model,
			RedirectAttributes redirectAttributes)
	{

		String retorno = "";

		BigInteger id = null;
		try
		{
			id = CriptografiaUtils.getInstance().desriptografarInteiro(idCriptografado);
		}
		catch (Exception e)
		{
			redirectAttributes.addFlashAttribute("retorno", Default.MENSAGEM_DESCRIPTOGRAFIA_ID);
			return Template.REDIRECT_ADMIN_BLOG;
		}

		Post postParaDeletar = postRepository.findOne(id.longValue());

		if (postParaDeletar != null)
		{

			try
			{
				postRepository.delete(id.longValue());
				retorno = "O post " + postParaDeletar.getHtml() + " foi deletado.";
			}
			catch (Exception e)
			{
				e.printStackTrace();
				retorno = Default.MENSAGEM_ERROR_DEFAULT;
			}

			redirectAttributes.addFlashAttribute("retorno", retorno);

		}
		else
		{
			redirectAttributes.addFlashAttribute("retorno", Default.MENSAGEM_DESCRIPTOGRAFIA_ID);
		}

		return Template.REDIRECT_ADMIN_BLOG;
	}

	@RequestMapping(value = "/curtir/{id}")
	@ResponseBody
	public String curtir(@PathVariable Long id)
	{
		Post postCurtido = postRepository.findOne(id);

		if (postCurtido != null)
		{

			PostFacade facade = new PostFacade(postCurtido);
			facade.curtir();

			try
			{
				postRepository.save(postCurtido);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				return Default.MENSAGEM_ERROR_DEFAULT;
			}

			return null;
		}
		else
		{
			return Default.MENSAGEM_DESCRIPTOGRAFIA_ID;
		}

	}

}

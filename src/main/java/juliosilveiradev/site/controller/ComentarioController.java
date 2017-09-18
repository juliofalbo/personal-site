package juliosilveiradev.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import juliosilveiradev.site.constantes.Default;
import juliosilveiradev.site.constantes.Template;
import juliosilveiradev.site.facades.ComentarioFacade;
import juliosilveiradev.site.model.Comentario;
import juliosilveiradev.site.model.Post;
import juliosilveiradev.site.repository.ComentarioRepository;
import juliosilveiradev.site.repository.PostRepository;

@Controller
public class ComentarioController
{

	@Autowired
	ComentarioRepository comentarioRepository;

	@Autowired
	PostRepository postRepository;

	@RequestMapping(value = "/salvarComentario", method = { RequestMethod.POST })
	public String salvarComentario(@ModelAttribute("novoComentario") Comentario novoComentario,
			Model model, RedirectAttributes redirectAttributes, @RequestParam("idPost") Long idPost)
	{
		Post post = null;

		if (novoComentario != null && idPost != null)
		{
			post = postRepository.findOne(idPost);

			ComentarioFacade comentarioFacade = new ComentarioFacade(novoComentario);
			comentarioFacade.definirPost(post);

			String retorno = "";

			try
			{
				comentarioRepository.save(novoComentario);

				retorno = "Opa, muito obrigado pela contribuição";
			}
			catch (Exception e)
			{
				e.printStackTrace();
				retorno = Default.MENSAGEM_ERROR_DEFAULT;
			}

			
			model.addAttribute("post", post);
			model.addAttribute("novoComentario", new Comentario());
			
			String html = post.getHtml();
			
			if (html.endsWith(".html"))
			{
				html = html.replace(".html", "");
			}
			
			model.addAttribute("retorno", retorno);
			
			return html;
		}
		else
		{
			return Template.REDIRECT_ADMIN_BLOG;
		}

	}

}

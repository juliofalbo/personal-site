package juliosilveiradev.site.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import juliosilveiradev.site.constantes.Template;
import juliosilveiradev.site.facades.DepoimentoFacade;
import juliosilveiradev.site.facades.PortfolioFacade;
import juliosilveiradev.site.facades.PostFacade;
import juliosilveiradev.site.model.Comentario;
import juliosilveiradev.site.model.Depoimento;
import juliosilveiradev.site.model.Portfolio;
import juliosilveiradev.site.model.Post;
import juliosilveiradev.site.repository.DepoimentoRepository;
import juliosilveiradev.site.repository.PortfolioRepository;
import juliosilveiradev.site.repository.PostRepository;

@Controller
public class SiteController
{

	@Autowired
	DepoimentoRepository depoimentoRepository;

	@Autowired
	PostRepository postRepository;
	
	@Autowired
	PortfolioRepository portfolioRepository;

	@RequestMapping("/")
	public String home(Model model)
	{
		model.addAttribute("depoimentos", depoimentoRepository.getDepoimentoAprovados());

		Integer size = 0;
		Iterable<Post> postPublicados = postRepository.getPostPublicados();
		if (postPublicados.iterator().hasNext())
		{
			PostFacade facade = new PostFacade(postPublicados);
			facade.procedimentosPadroesLista();
			size++;
		}
		model.addAttribute("posts", postPublicados);
		model.addAttribute("postsSize", size);
		model.addAttribute("novoDepoimento", new Depoimento());

		Integer sizePort = 0;
		Iterable<Portfolio> portfolios = portfolioRepository.findAll();
		if (portfolios.iterator().hasNext())
		{
			PortfolioFacade facade = new PortfolioFacade(portfolios);
			facade.procedimentosPadroesLista();
			sizePort++;
		}
		
		model.addAttribute("sizePort", sizePort);
		model.addAttribute("portfolios", portfolios);
		
		return Template.HOME;
	}

	@RequestMapping("/admin")
	public String admin(Model model)
	{

		Iterable<Depoimento> depoimentos = depoimentoRepository.getDepoimentoSemAvaliacao();
		if (depoimentos.iterator().hasNext())
		{
			DepoimentoFacade facade = new DepoimentoFacade(depoimentos);
			facade.procedimentosPadroesLista();
		}
		model.addAttribute("depoimentos", depoimentos);

		Integer size = 0;
		Iterable<Post> posts = postRepository.findAll();
		if (posts.iterator().hasNext())
		{
			PostFacade facade = new PostFacade(posts);
			facade.procedimentosPadroesLista();
			size++;
		}
		model.addAttribute("posts", posts);
		model.addAttribute("postsSize", size);
		model.addAttribute("novoPost", new Post());
		model.addAttribute("postEditavel", new Post());
		
		Integer sizePort = 0;
		Iterable<Portfolio> portfolios = portfolioRepository.findAll();
		if (portfolios.iterator().hasNext())
		{
			PortfolioFacade facade = new PortfolioFacade(portfolios);
			facade.procedimentosPadroesLista();
			sizePort++;
		}
		
		model.addAttribute("sizePort", sizePort);
		model.addAttribute("portfolios", portfolios);
		model.addAttribute("novoPortfolio", new Portfolio());
		model.addAttribute("portfolioEditavel", new Portfolio());
		
		return Template.ADMIN;
	}

	@RequestMapping("/blog/{id}")
	public String lerPost(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes)
	{
		Post post = postRepository.findOne(id);
		String html = post.getHtml();
		
		if (html.endsWith(".html"))
		{
			html = html.replace(".html", "");
		}
		
		model.addAttribute("post", post);
		model.addAttribute("novoComentario", new Comentario());

		return html;
	}

}

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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import juliosilveiradev.site.constantes.Default;
import juliosilveiradev.site.constantes.Template;
import juliosilveiradev.site.facades.PortfolioFacade;
import juliosilveiradev.site.model.Portfolio;
import juliosilveiradev.site.repository.PortfolioRepository;
import juliosilveiradev.site.utils.CriptografiaUtils;
import juliosilveiradev.site.utils.SiteUtils;

@Controller
public class PortfolioController
{

	@Autowired
	PortfolioRepository portfolioRepository;

	@RequestMapping(value = "/admin/salvarPortfolio", method = { RequestMethod.POST })
	public String salvarPortfolio(@ModelAttribute("novoPortfolio") Portfolio novoPortfolio, Model model,
			RedirectAttributes redirectAttributes,
			@RequestParam("fotoPortfolio") MultipartFile fotoPortfolio)
	{
		if (novoPortfolio != null)
		{

			PortfolioFacade facade = new PortfolioFacade(novoPortfolio);

			if (SiteUtils.getInstance().validarArquivoUpload(fotoPortfolio))
			{
				facade.salvarArquivoUpload(fotoPortfolio);
			}

			String retorno = "";

			try
			{
				portfolioRepository.save(novoPortfolio);

				retorno = "O projeto " + novoPortfolio.getNome()
						+ " foi pra conta! To ficando bom nisso hein?!";
			}
			catch (Exception e)
			{
				e.printStackTrace();
				retorno = Default.MENSAGEM_ERROR_DEFAULT;
			}

			redirectAttributes.addFlashAttribute("retorno", retorno);

			return Template.REDIRECT_ADMIN_PORTFOLIO;
		}
		else
		{
			return Template.REDIRECT_DEFAULT;
		}

	}

	@RequestMapping(value = "/admin/editarPortfolio", method = { RequestMethod.POST })
	public String editarPortfolio(@ModelAttribute("portfolioEditavel") Portfolio portfolioEditavel,
			Model model, RedirectAttributes redirectAttributes,
			@RequestParam("fotoPortfolioEdit") MultipartFile fotoPortfolio)
	{
		if (portfolioEditavel != null)
		{
			Portfolio portfolioBanco = portfolioRepository.findOne(portfolioEditavel.getId());

			PortfolioFacade facade = new PortfolioFacade(portfolioBanco);
			facade.atualizarObjeto(portfolioEditavel);

			if (SiteUtils.getInstance().validarArquivoUpload(fotoPortfolio))
			{
				facade.salvarArquivoUpload(fotoPortfolio);
			}

			String retorno = "";

			try
			{
				portfolioRepository.save(portfolioBanco);

				retorno = "O projeto " + portfolioBanco.getNome() + " foi alterado com sucesso!";
			}
			catch (Exception e)
			{
				e.printStackTrace();
				retorno = Default.MENSAGEM_ERROR_DEFAULT;
			}

			redirectAttributes.addFlashAttribute("retorno", retorno);

			return Template.REDIRECT_ADMIN_PORTFOLIO;
		}
		else
		{
			return Template.REDIRECT_DEFAULT;
		}

	}

	@RequestMapping("/admin/deletarPortfolio/{idCriptografado}")
	public String deletarPortfolio(@PathVariable BigInteger idCriptografado, Model model,
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
			return Template.REDIRECT_ADMIN_PORTFOLIO;
		}

		Portfolio postParaDeletar = portfolioRepository.findOne(id.longValue());

		if (postParaDeletar != null)
		{

			try
			{
				portfolioRepository.delete(id.longValue());
				retorno = "O item " + postParaDeletar.getNome() + " foi deletado.";
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

		return Template.REDIRECT_ADMIN_PORTFOLIO;
	}

}

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
import juliosilveiradev.site.facades.DepoimentoFacade;
import juliosilveiradev.site.model.Depoimento;
import juliosilveiradev.site.repository.DepoimentoRepository;
import juliosilveiradev.site.utils.CriptografiaUtils;
import juliosilveiradev.site.utils.SiteUtils;

@Controller
public class DepoimentoController
{

	@Autowired
	DepoimentoRepository depoimentoRepository;

	@RequestMapping(value = "/salvarDepoimento", method = { RequestMethod.POST })
	public String salvarDepoimento(@ModelAttribute("novoDepoimento") Depoimento novoDepoimento,
			Model model, RedirectAttributes redirectAttributes, @RequestParam("file") MultipartFile file)
	{

		if (novoDepoimento != null)
		{

			DepoimentoFacade facade = new DepoimentoFacade(novoDepoimento);
			facade.setarDataAtual();

			if (SiteUtils.getInstance().validarArquivoUpload(file))
			{
				facade.salvarArquivoUpload(file);
			}

			String retorno = "";

			try
			{
				depoimentoRepository.save(novoDepoimento);
				retorno = "Muito obrigado pelo seu tempo " + novoDepoimento.getRemetente()
						+ ", esse depoimento é muito importante para mim.";
			}
			catch (Exception e)
			{
				e.printStackTrace();
				retorno = Default.MENSAGEM_ERROR_DEFAULT;
			}

			redirectAttributes.addFlashAttribute("retorno", retorno);

		}

		return Template.REDIRECT_DEFAULT;
	}

	@RequestMapping("/admin/aprovarDepoimento/{idCriptografado}")
	public String aprovarDepoimento(@PathVariable BigInteger idCriptografado, Model model,
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
			return Template.REDIRECT_ADMIN;
		}

		Depoimento depoimentoParaAprovar = depoimentoRepository.findOne(id.longValue());

		if (depoimentoParaAprovar != null)
		{
			DepoimentoFacade facade = new DepoimentoFacade(depoimentoParaAprovar);
			facade.aprovarDepoimento();

			try
			{
				depoimentoRepository.save(depoimentoParaAprovar);
				retorno = "O depoimento escrito por " + depoimentoParaAprovar.getRemetente()
						+ " foi aprovado.";
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

		return Template.REDIRECT_ADMIN;
	}

	@RequestMapping("/admin/reprovarDepoimento/{idCriptografado}")
	public String reprovarDepoimento(@PathVariable BigInteger idCriptografado, Model model,
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
			return Template.REDIRECT_ADMIN;
		}

		Depoimento depoimentoParaReprovar = depoimentoRepository.findOne(id.longValue());

		if (depoimentoParaReprovar != null)
		{
			DepoimentoFacade facade = new DepoimentoFacade(depoimentoParaReprovar);
			facade.reprovarDepoimento();

			try
			{
				depoimentoRepository.save(depoimentoParaReprovar);
				retorno = "O depoimento escrito por " + depoimentoParaReprovar.getRemetente()
						+ " foi reprovado.";
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

		return Template.REDIRECT_ADMIN;
	}

}

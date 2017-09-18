package juliosilveiradev.site.facades;

import java.math.BigInteger;

import org.springframework.web.multipart.MultipartFile;

import juliosilveiradev.site.facades.interfaces.FacadeInterface;
import juliosilveiradev.site.model.Portfolio;
import juliosilveiradev.site.utils.CriptografiaUtils;
import juliosilveiradev.site.utils.JUtils;

public class PortfolioFacade implements FacadeInterface
{

	private Portfolio portfolio = null;
	private Iterable<Portfolio> portfolios = null;

	public PortfolioFacade(Portfolio portfolio)
	{
		this.portfolio = portfolio;
	}

	public PortfolioFacade(Iterable<Portfolio> portfolios)
	{
		this.portfolios = portfolios;
	}

	public void procedimentosPadroesLista()
	{
		for (Portfolio portfolio : this.portfolios)
		{
			PortfolioFacade facade = new PortfolioFacade(portfolio);
			facade.criptografarId();
			facade.adicionarPlic();
		}
	}

	public void salvarArquivoUpload(MultipartFile file)
	{
		String caminhoFotoBanco = JUtils.getInstance().salvarArquivoRetornandoCaminhoDoBD("portfolio",
				file);
		this.portfolio.setFoto(caminhoFotoBanco);
	}

	@Override
	public void criptografarId()
	{
		this.portfolio.setIdCriptografado(CriptografiaUtils.getInstance()
				.criptografarInteiro(BigInteger.valueOf(this.portfolio.getId())));
	}

	public void atualizarObjeto(Portfolio portfolioEditado)
	{
		this.portfolio = (Portfolio) JUtils.getInstance().atualizarObjeto(portfolioEditado,
				this.portfolio);
	}

	public void adicionarPlic()
	{
		this.portfolio.setNomePlic("'" + this.portfolio.getNome() + "'");
		this.portfolio.setLinkPlic("'" + this.portfolio.getLink() + "'");
		this.portfolio.setTipoPlic("'" + this.portfolio.getTipo() + "'");
		this.portfolio.setFiltroPlic("'" + this.portfolio.getFiltro() + "'");
		this.portfolio.setIconePlic("'" + this.portfolio.getIcone() + "'");
	}

}

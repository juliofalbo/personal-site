package juliosilveiradev.site.facades;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import juliosilveiradev.site.facades.interfaces.FacadeInterface;
import juliosilveiradev.site.model.Depoimento;
import juliosilveiradev.site.utils.CriptografiaUtils;
import juliosilveiradev.site.utils.JDataUtils;
import juliosilveiradev.site.utils.JUtils;

public class DepoimentoFacade implements FacadeInterface
{

	private Depoimento depoimento = null;
	private Iterable<Depoimento> depoimentos = null;

	public DepoimentoFacade(Depoimento depoimento)
	{
		this.depoimento = depoimento;
	}

	public DepoimentoFacade(Iterable<Depoimento> depoimentos)
	{
		this.depoimentos = depoimentos;
	}

	public void procedimentosPadroesLista()
	{
		for (Depoimento depoimento : this.depoimentos)
		{
			DepoimentoFacade facade = new DepoimentoFacade(depoimento);
			// this.depoimento = depoimento
			facade.formatarData();
			facade.converterData();
			facade.criptografarId();
		}
	}

	public void converterData()
	{
		this.depoimento.setDataZDT(
				JDataUtils.getInstance().converterParaZonedDateTime(this.depoimento.getData()));
	}

	public void formatarData()
	{
		ZonedDateTime data = this.depoimento.getDataZDT();
		if (data == null)
		{
			converterData();
		}
		this.depoimento.setDataFormatada(JDataUtils.getInstance().formatarZonedDateTime(data));
	}

	public void salvarArquivoUpload(MultipartFile file)
	{
		String caminhoFotoBanco = JUtils.getInstance().salvarArquivoRetornandoCaminhoDoBD("fotos", file);
		this.depoimento.setFoto(caminhoFotoBanco);
	}

	public void setarDataAtual()
	{
		this.depoimento.setData(new Date());
		this.depoimento.setDataZDT(ZonedDateTime.now());
	}

	public void aprovarDepoimento()
	{
		this.depoimento.setAprovado(true);
	}

	public void reprovarDepoimento()
	{
		this.depoimento.setAprovado(true);
	}

	@Override
	public void criptografarId()
	{
		this.depoimento.setIdCriptografado(
				CriptografiaUtils.getInstance().criptografarInteiro(BigInteger.valueOf(this.depoimento.getId())));
	}

}

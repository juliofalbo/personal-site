package juliosilveiradev.site.facades;

import java.math.BigInteger;
import java.time.ZonedDateTime;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import juliosilveiradev.site.facades.interfaces.FacadeInterface;
import juliosilveiradev.site.model.Post;
import juliosilveiradev.site.repository.UsuarioRepository;
import juliosilveiradev.site.utils.CriptografiaUtils;
import juliosilveiradev.site.utils.JDataUtils;
import juliosilveiradev.site.utils.JUtils;
import juliosilveiradev.site.utils.SiteUtils;

public class PostFacade implements FacadeInterface
{

	private Post post = null;
	private Iterable<Post> posts = null;

	public PostFacade(Post post)
	{
		this.post = post;
	}

	public PostFacade(Iterable<Post> posts)
	{
		this.posts = posts;
	}

	public void procedimentosPadroesLista()
	{
		for (Post post : this.posts)
		{
			PostFacade facade = new PostFacade(post);
			facade.converterDatas();
			facade.formatarDatas();
			facade.criptografarId();
			facade.calcularQtdDeComentarios();
			facade.adicionarPlic();
		}
	}

	public void formatarDatas()
	{
		formatarDataCriacao();
		formatarDataPostagem();
	}

	public void formatarDataCriacao()
	{
		ZonedDateTime criacao = this.post.getDataCriacaoZDT();
		Date criacaoDate = this.post.getDataCriacao();
		if (criacao == null && criacaoDate != null)
		{
			converterDataCriacao();
			this.post.setDataCriacaoFormatada(
					JDataUtils.getInstance().formatarZonedDateTime(this.post.getDataCriacaoZDT()));
		}
		else if (criacao != null)
		{
			this.post.setDataCriacaoFormatada(JDataUtils.getInstance().formatarZonedDateTime(criacao));
		}
	}

	public void formatarDataPostagem()
	{
		ZonedDateTime postagem = this.post.getDataPostagemZDT();
		Date postagemDate = this.post.getDataPostagem();
		if (postagem == null && postagemDate != null)
		{
			converterDataPostagem();
			this.post.setDataPostagemFormatada(JDataUtils.getInstance()
					.formatarZonedDateTimePorExtenso(this.post.getDataPostagemZDT()));
		}
		else if (postagem != null)
		{
			this.post.setDataPostagemFormatada(
					JDataUtils.getInstance().formatarZonedDateTimePorExtenso(postagem));
		}
	}

	public void converterDatas()
	{
		converterDataCriacao();
		converterDataPostagem();
	}

	public void converterDataCriacao()
	{
		this.post.setDataCriacaoZDT(
				JDataUtils.getInstance().converterParaZonedDateTime(this.post.getDataCriacao()));
	}

	public void converterDataPostagem()
	{
		this.post.setDataPostagemZDT(
				JDataUtils.getInstance().converterParaZonedDateTime(this.post.getDataPostagem()));
	}

	public void setarDataAtualNaCriacao()
	{
		this.post.setDataCriacao(new Date());
		this.post.setDataCriacaoZDT(ZonedDateTime.now());
	}

	public void setarDataAtualNaPostagem()
	{
		this.post.setDataPostagem(new Date());
		this.post.setDataPostagemZDT(ZonedDateTime.now());
	}

	public void salvarArquivoUpload(MultipartFile file)
	{
		String caminhoFotoBanco = JUtils.getInstance().salvarArquivoRetornandoCaminhoDoBD("posts", file);
		this.post.setImagem(caminhoFotoBanco);
	}

	public void publicar()
	{
		this.post.setPublicado(true);
	}

	public void naoPublicar()
	{
		this.post.setPublicado(false);
	}

	public void setarUsuarioLogadoComoCriador(UsuarioRepository usuarioRepository)
	{
		this.post.setCriador(SiteUtils.getInstance().getUsuarioLogado(usuarioRepository));
	}

	public void curtir()
	{
		Long likes = this.post.getLikes();
		likes++;
		this.post.setLikes(likes);
	}

	public void zerarLikes()
	{
		this.post.setLikes(0L);
	}

	public void calcularQtdDeComentarios()
	{
		this.post.setQtdDeComentarios(new Long(post.getComentarios().size()));
	}

	@Override
	public void criptografarId()
	{
		this.post.setIdCriptografado(CriptografiaUtils.getInstance()
				.criptografarInteiro(BigInteger.valueOf(this.post.getId())));
	}

	public void adicionarPlic()
	{
		this.post.setHtmlPlic("'" + this.post.getHtml() + "'");
		this.post.setTituloPlic("'" + this.post.getTitulo() + "'");
		this.post.setTextoInicialPlic("'" + this.post.getTextoInicial() + "'");
	}

	public void atualizarObjeto(Post postEditado)
	{
		this.post = (Post) JUtils.getInstance().atualizarObjeto(postEditado, this.post);
	}

	public void validarHtml()
	{
		if (!this.post.getHtml().endsWith(".html"))
		{
			this.post.setHtml(this.post.getHtml() + ".html");
		}
	}

}

package juliosilveiradev.site.utils;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class JDataUtils
{

	private static JDataUtils instance;

	public static synchronized JDataUtils getInstance()
	{
		if (instance == null)
		{
			instance = new JDataUtils();
		}

		return instance;
	}

	public String formatarZonedDateTime(ZonedDateTime data)
	{
		String dataFormatada = "";
		if (data != null)
		{
			DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
			dataFormatada = data.format(formatador);
		}
		return dataFormatada;
	}

	public String formatarZonedDateTimePorExtenso(ZonedDateTime data)
	{
		String dataFormatada = "";
		if (data != null)
		{
			DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy");
			dataFormatada = data.format(formatador);
		}
		return dataFormatada;
	}

	public Date converterParaDate(ZonedDateTime data)
	{
		return Date.from(data.toInstant());
	}

	public ZonedDateTime converterParaZonedDateTime(Date data)
	{
		ZonedDateTime zonedDateTime = null;
		if (data != null)
		{
			zonedDateTime = ZonedDateTime.ofInstant(data.toInstant(), ZoneId.systemDefault());
		}
		return zonedDateTime;
	}

}

package main;

public enum Numero {
	ZERO("zero"), UM("um"), DOIS("dois"), TRES("tres"), QUATRO("quatro"), CINCO(
			"cinco"), SEIS("seis"), SETE("sete"), OITO("oito"), NOVE("nove"), DEZ(
			"dez"), ONZE("onze"), DOZE("doze"), TREZE("treze"), CATORZE(
			"catorze"), QUINZE("quinze"), DEZESSEIS("dezesseis"), DEZESSETE(
			"dezessete"), DEZOITO("dezoito"), DEZENOVE("dezenove"), VINTE(
			"vinte"), TRINTA("trinta"), QUARENTA("quarenta"), CINQUENTA(
			"cinquenta"), SESSENTA("sessenta"), SETENTA("setenta"), OITENTA(
			"oitenta"), NOVENTA("noventa"), CENTO("cento"), CEM("cem"), DUZENTOS(
			"duzentos"), TREZENTOS("trezentos"), QUATROCENTOS("quatrocentos"), QUINHENTOS(
			"quinhentos"), SEISCENTOS("seissentos"), SETECENTOS("setessentos"), OITOCENTOS(
			"oitocentos"), NOVECENTOS("novecentos");
	;

	private String nomeDoNumero;

	private Numero(String nomeDoNumero) {
		this.setNumberName(nomeDoNumero);
	}

	public String getNumberName() {
		return nomeDoNumero;
	}

	public void setNumberName(String nomeDoNumero) {
		this.nomeDoNumero = nomeDoNumero;
	}

	@Override
	public String toString() {
		return nomeDoNumero;
	}
}

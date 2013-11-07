package main;

public enum Number {
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
			"oitocentos"), NOVECENTOS("novecentos"), MIL("mil"), MILHAO(
			"milhão"), BILHAO("bilhão"), MILHOES("milhões"), BILHOES("bilhões");
	;

	private String numberName;

	private Number(String numberName) {
		this.setNumberName(numberName);
	}

	public String getNumberName() {
		return numberName;
	}

	public void setNumberName(String numberName) {
		this.numberName = numberName;
	}

	@Override
	public String toString() {
		return numberName;
	}
}

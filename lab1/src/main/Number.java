package main;

/**
 * Enum que guarda valores dos números seja ele, centena, dezena ou unidade,
 * todos os outros podem ser formados por uma lei de formação da combinação
 * desses três anteriores. Para não ficar exaustivo a forma de se escrever e
 * facilitar a procura, os números que são dezenas foram concatenados com o
 * caracter 'd' e os que são centenas com o caracter 'c.
 */
public enum Number {
	ZERO("zero", "0"), ZERO_DEZENA("", "0d"), ZERO_CENTENA("", "0c"), UM("um",
			"1"), DOIS("dois", "2"), TRES("três", "3"), QUATRO("quatro", "4"), CINCO(
			"cinco", "5"), SEIS("seis", "6"), SETE("sete", "7"), OITO("oito",
			"8"), NOVE("nove", "9"), DEZ("dez", "10"), ONZE("onze", "11"), DOZE(
			"doze", "12"), TREZE("treze", "13"), CATORZE("catorze", "14"), QUINZE(
			"quinze", "15"), DEZESSEIS("dezesseis", "16"), DEZESSETE(
			"dezessete", "17"), DEZOITO("dezoito", "18"), DEZENOVE("dezenove",
			"19"), VINTE("vinte", "2d"), TRINTA("trinta", "3d"), QUARENTA(
			"quarenta", "4d"), CINQUENTA("cinquenta", "5d"), SESSENTA(
			"sessenta", "6d"), SETENTA("setenta", "7d"), OITENTA("oitenta",
			"8d"), NOVENTA("noventa", "9d"), CENTO("cento", "1c"), CEM("cem",
			"1cc"), DUZENTOS("duzentos", "2c"), TREZENTOS("trezentos", "3c"), QUATROCENTOS(
			"quatrocentos", "4c"), QUINHENTOS("quinhentos", "5c"), SEISCENTOS(
			"seissentos", "6c"), SETECENTOS("setessentos", "7c"), OITOCENTOS(
			"oitocentos", "8c"), NOVECENTOS("novecentos", "9c"), MIL("mil", "m"), MILHAO(
			"milhão", "1ml"), BILHAO("bilhão", "1bl"), MILHOES("milhões", "mls");
	;

	private String numberName;
	private String value;

	private Number(String numberName, String value) {
		this.setNumberName(numberName);
		this.value = value;
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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}

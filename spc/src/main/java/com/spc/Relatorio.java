package com.spc;

import com.spc.service.PacienteService;

public class Relatorio {
	public static void main(String[] args) {
		PacienteService pacienteService = new PacienteService();

		System.out.println(pacienteService.getTodasInformacoesSobreOsPacientes() + "\n\n"
				+ "Quantidade de pacientes: " + pacienteService.getQuantidadePacientes() + "\n"
				+ "Idade Média dos homens: " + pacienteService.getIdadeMediaDosHomens() + "\n"
				+ "Quantidade de mulheres com altura entre 1,60 e 1,70 e peso acima de 70kg: "
				+ pacienteService.getQuantidadeMulheresAlturaEPesoEspecificos() + "\n"
				+ "A quantidade de pessoas com idade entre 18 e 25: "
				+ pacienteService.getQuantidadePacientesComIdadeEspecifica() + "\n"
				+ "Nome do(s) paciente(s) mais velho(s): " + pacienteService.getNomePacientesMaisVelhos() + "\n"
				+ "Nome da(s) mulheres(s) mais baixa(s): " + pacienteService.getNomeMulheresMaisBaixas() + "\n"
				+ "Média total de pesos por sexo: " + pacienteService.getMediaTotalDePesoPorSexo());
	}
}

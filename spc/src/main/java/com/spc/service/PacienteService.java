package com.spc.service;

import javax.inject.Inject;

import com.spc.dao.PacienteDAO;

//TODO Transferir regras de negócio do DAO para a classe Service
public class PacienteService {

	@Inject
	PacienteDAO pacienteDAO;

	/**
	 * @return Retorna uma String que contem as informações de todos os pacientes
	 */
	public String getTodasInformacoesSobreOsPacientes() {
		return pacienteDAO.getTodasInformacoesSobreOsPacientes();
	}

	/**
	 * @return Quantidade total de pacientes - long
	 */
	public long getQuantidadePacientes() {
		return pacienteDAO.getQuantidadePacientes();
	}

	/**
	 * @return Retorna a média de idade dos Homens - double
	 */
	public double getIdadeMediaDosHomens() {
		return pacienteDAO.getIdadeMediaDosHomens();
	}

	/**
	 * @return Retorna a quantidade de mulheres com altura entre 1,60 e 1,70 e peso
	 *         acima de 70kg - long
	 */
	public long getQuantidadeMulheresAlturaEPesoEspecificos() {
		return pacienteDAO.getQuantidadeMulheresAlturaEPesoEspecificos();
	}

	/**
	 * @return Retorna a quantidade de pessoas com idade entre 18 e 25. - long
	 */
	public long getQuantidadePacientesComIdadeEspecifica() {
		return pacienteDAO.getQuantidadePacientesComIdadeEspecifica();
	}

	/**
	 * @return Retorna o nome do paciente mais velho, retorna mais de um nome se
	 *         existirem diversos pacientes com a mesma idade e se forem os mais
	 *         velhos. - String
	 */
	public String getNomePacientesMaisVelhos() {
		return pacienteDAO.getNomePacientesMaisVelhos();
	}

	/**
	 * @return Retorna o nome da mulher mais baixa, retorna mais de um nome se
	 *         existirem diversas mulheres com a mesma altura e se forem as mais
	 *         baixas. - String
	 */
	public String getNomeMulheresMaisBaixas() {
		return pacienteDAO.getNomeMulheresMaisBaixas();
	}

	/**
	 * @return Retorna a média de peso dos homens e das mulheres e diz qual média é maior. - String
	 */
	public String getMediaTotalDePesoPorSexo() {
		return pacienteDAO.getMediaTotalDePesoPorSexo();
	}
}

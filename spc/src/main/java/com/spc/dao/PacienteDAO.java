package com.spc.dao;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.spc.model.Paciente;

//TODO Transferir regras de negócio do DAO para a classe Service

public class PacienteDAO {

	@Inject
	private EntityManagerFactory entityManagerFactory;

	@Inject
	private EntityManager entityManager;

	private void InitializeEntityManager() {
		try {
			entityManagerFactory = Persistence.createEntityManagerFactory("spc");
			entityManager = entityManagerFactory.createEntityManager();
		} catch (Exception e) {
			System.out.println("failed to initialize EntityManager");
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @return Retorna uma String que contem as informações de todos os pacientes
	 */
	public String getTodasInformacoesSobreOsPacientes() {
		try {
			InitializeEntityManager();

			entityManager.getTransaction().begin();
			@SuppressWarnings("unchecked")
			List<Paciente> listaPaciente = (List<Paciente>) entityManager
					.createQuery("SELECT p FROM Paciente p").getResultList();
			entityManager.getTransaction().commit();
			
			String listaFinal = "";

			if(listaPaciente.size() > 0) {
				for(int i = 0; i < listaPaciente.size(); i++) {
					if(i > 0) {
						listaFinal += "\n\n";
					}
					if(i == listaPaciente.size() - 1) {
						listaFinal += "Nome do paciente: " + listaPaciente.get(i).getNome() + " fim" 
								+ "\nSexo: " + listaPaciente.get(i).getSexo()
								+ "\nPeso: " + listaPaciente.get(i).getPeso() + " kg"
								+ "\nIdade: " + listaPaciente.get(i).getIdade()
								+ "\nAltura: " + listaPaciente.get(i).getAltura() + " cm";
					}else {
						listaFinal += "Nome do paciente: " + listaPaciente.get(i).getNome()
								+ "\nSexo: " + listaPaciente.get(i).getSexo()
								+ "\nPeso: " + listaPaciente.get(i).getPeso() + " kg"
								+ "\nIdade: " + listaPaciente.get(i).getIdade()
								+ "\nAltura: " + listaPaciente.get(i).getAltura() + " cm";
					}
				}
			}else {
				listaFinal = "fim";
			}
			
			return listaFinal;
		} catch (Exception e) {
			if (entityManager.getTransaction() != null) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
			}
		} finally {
			entityManager.close();
		}

		return "";
	}

	/**
	 *
	 * @return Quantidade total de pacientes - long
	 */
	public long getQuantidadePacientes() {
		try {
			InitializeEntityManager();

			entityManager.getTransaction().begin();
			long quantidadePacientes = (long) entityManager.createQuery("SELECT COUNT(*) FROM Paciente p")
					.getSingleResult();
			entityManager.getTransaction().commit();

			return quantidadePacientes;
		} catch (Exception e) {
			if (entityManager.getTransaction() != null) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
			}
		} finally {
			entityManager.close();
		}

		return 0;
	}

	/**
	 * 
	 * @return Retorna a média de idade dos Homens - double
	 */
	public double getIdadeMediaDosHomens() {
		try {
			InitializeEntityManager();

			entityManager.getTransaction().begin();
			@SuppressWarnings("unchecked")
			List<Paciente> listaPaciente = (List<Paciente>) entityManager
					.createQuery("SELECT p FROM Paciente p WHERE p.sexo='M'").getResultList();
			entityManager.getTransaction().commit();

			int idadeTotal = 0;
			for (int i = 0; i < listaPaciente.size(); i++) {
				idadeTotal += listaPaciente.get(i).getIdade();
			}

			double mediaIdade = idadeTotal / (double) listaPaciente.size();

			return mediaIdade;
		} catch (Exception e) {
			if (entityManager.getTransaction() != null) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
			}
		} finally {
			entityManager.close();
		}

		return 0;
	}

	/**
	 * 
	 * @return Retorna a quantidade de mulheres com altura entre 1,60 e 1,70 e peso
	 *         acima de 70kg - long
	 */
	public long getQuantidadeMulheresAlturaEPesoEspecificos() {
		try {
			InitializeEntityManager();

			entityManager.getTransaction().begin();
			long quantidadeMulheres = (long) entityManager.createQuery(
					"SELECT COUNT(*) FROM Paciente p WHERE p.sexo='F' AND p.altura >= 160 AND p.altura <= 170 AND p.peso > 70")
					.getSingleResult();
			entityManager.getTransaction().commit();

			return quantidadeMulheres;
		} catch (Exception e) {
			if (entityManager.getTransaction() != null) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
			}
		} finally {
			entityManager.close();
		}

		return 0;
	}

	/**
	 * 
	 * @return Retorna a quantidade de pessoas com idade entre 18 e 25. - long
	 */
	public long getQuantidadePacientesComIdadeEspecifica() {
		try {
			InitializeEntityManager();

			entityManager.getTransaction().begin();
			long quantidadePessoas = (long) entityManager
					.createQuery("SELECT COUNT(*) FROM Paciente p WHERE p.idade >= 18 AND p.idade <= 25")
					.getSingleResult();
			entityManager.getTransaction().commit();

			return quantidadePessoas;
		} catch (Exception e) {
			if (entityManager.getTransaction() != null) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
			}
		} finally {
			entityManager.close();
		}

		return 0;
	}

	/**
	 * 
	 * @return Retorna o nome do paciente mais velho, retorna mais de um nome se
	 *         existirem diversos pacientes com a mesma idade e se forem os mais
	 *         velhos. - String
	 */
	public String getNomePacientesMaisVelhos() {
		try {
			InitializeEntityManager();

			entityManager.getTransaction().begin();

			@SuppressWarnings("unchecked")
			List<Paciente> pacientesMaisVelhos = (List<Paciente>) entityManager
					.createQuery("SELECT p FROM Paciente p WHERE p.idade = (SELECT max(p.idade) FROM Paciente p)")
					.getResultList();
			entityManager.getTransaction().commit();

			String nomeDosPacientesMaisVelhos = "";
			if (pacientesMaisVelhos.size() > 1) {
				for (int i = 0; i < pacientesMaisVelhos.size(); i++) {
					if (i > 0)
						nomeDosPacientesMaisVelhos += ", ";

					nomeDosPacientesMaisVelhos += pacientesMaisVelhos.get(i).getNome();
				}
			} else if (pacientesMaisVelhos.size() > 0) {
				nomeDosPacientesMaisVelhos = pacientesMaisVelhos.get(0).getNome();
			} else {
				nomeDosPacientesMaisVelhos = "Não existe nenhum paciente ainda nos registros!";
			}

			return nomeDosPacientesMaisVelhos;
		} catch (Exception e) {
			if (entityManager.getTransaction() != null) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
			}
		} finally {
			entityManager.close();
		}

		return "";
	}

	/**
	 * 
	 * @return Retorna o nome da mulher mais baixa, retorna mais de um nome se
	 *         existirem diversas mulheres com a mesma altura e se forem as mais
	 *         baixas. - String
	 */
	public String getNomeMulheresMaisBaixas() {
		try {
			InitializeEntityManager();

			entityManager.getTransaction().begin();

			@SuppressWarnings("unchecked")
			List<Paciente> mulheresMaisBaixas = (List<Paciente>) entityManager.createQuery(
					"SELECT p FROM Paciente p WHERE p.altura = (SELECT min(p.altura) FROM Paciente p) AND p.sexo = 'F'")
					.getResultList();
			entityManager.getTransaction().commit();

			String nomeDasMulheresMaisBaixas = "";
			if (mulheresMaisBaixas.size() > 1) {
				for (int i = 0; i < mulheresMaisBaixas.size(); i++) {
					if (i > 0)
						nomeDasMulheresMaisBaixas += ", ";

					nomeDasMulheresMaisBaixas += mulheresMaisBaixas.get(i).getNome();
				}
			} else if (mulheresMaisBaixas.size() > 0) {
				nomeDasMulheresMaisBaixas = mulheresMaisBaixas.get(0).getNome();
			} else {
				nomeDasMulheresMaisBaixas = "Não existe nenhum paciente ainda nos registros!";
			}

			return nomeDasMulheresMaisBaixas;
		} catch (Exception e) {
			if (entityManager.getTransaction() != null) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
			}
		} finally {
			entityManager.close();
		}

		return "";
	}

	/**
	 * 
	 * @return Retorna a média de peso dos homens e das mulheres e diz qual média é maior. - String
	 */
	public String getMediaTotalDePesoPorSexo() {
		try {
			InitializeEntityManager();

			entityManager.getTransaction().begin();

			@SuppressWarnings("unchecked")
			List<Paciente> todosPacientes = (List<Paciente>) entityManager.createQuery("SELECT p FROM Paciente p")
					.getResultList();
			entityManager.getTransaction().commit();

			List<Paciente> listaHomens = new ArrayList<>();
			List<Paciente> listaMulheres = new ArrayList<>();
			
			double mediaPesoHomens = 0;
			double mediaPesoMulheres = 0;
			
			int pesoTotalHomens = 0;
			int pesoTotalMulheres = 0;
			
			String resultado = "";

			for (int i = 0; i < todosPacientes.size(); i++) {
				if (todosPacientes.get(i).getSexo() == 'M')
					listaHomens.add(todosPacientes.get(i));
				else
					listaMulheres.add(todosPacientes.get(i));
			}
			
			if(listaHomens.size() > 0) {
				for(int i = 0; i < listaHomens.size(); i++) {
					pesoTotalHomens += listaHomens.get(i).getPeso();
				}
			}
			
			if(listaMulheres.size() > 0) {
				for(int i = 0; i < listaMulheres.size(); i++) {
					pesoTotalMulheres += listaMulheres.get(i).getPeso();
				}
			}
			
			mediaPesoHomens = (double)pesoTotalHomens / (double)listaHomens.size();
			mediaPesoMulheres = (double)pesoTotalMulheres / (double)listaMulheres.size();
			
			if(mediaPesoHomens > mediaPesoMulheres) {
				resultado = "A media do peso dos homens é maior do que a media de peso das mulheres! Sendo " + mediaPesoHomens + "kg a média total de peso dos homens e "
						+ mediaPesoMulheres + "kg a média total de peso das mulheres.";
			}else if(mediaPesoHomens < mediaPesoMulheres) {
				resultado = "A media do peso das mulheres é maior do que a media de peso dos homens! Sendo " + mediaPesoMulheres + "kg a média total de peso das mulheres e "
						+ mediaPesoHomens + "kg a média total de peso dos homens.";
			}else {
				resultado = "Incrivelmente a média do peso dos homens e das mulheres são iguais! Sendo " + mediaPesoHomens + "kg a média total de peso.";
			}

			return resultado;
		} catch (Exception e) {
			if (entityManager.getTransaction() != null) {
				entityManager.getTransaction().rollback();
				e.printStackTrace();
			}
		} finally {
			entityManager.close();
		}

		return "";
	}
}

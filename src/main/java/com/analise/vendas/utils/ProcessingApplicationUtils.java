package com.analise.vendas.utils;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

import com.analise.vendas.exception.ProcessingApplicationException;

public final class ProcessingApplicationUtils {

	/**
	 * Lista os arquivos no diretório de entrada: HOMEPATH/data/in
	 * 
	 * @return List<String>
	 */
	public static List<String> getArquivos() {
		return Arrays.asList(getDiretorioIn().listFiles(getfileFilter()))
					 .stream()
					 .map(File::getName)
					 .sorted()
					 .collect(Collectors.toList());
	}
	
	/**
	 * Lista os arquivos processados com seu nome original:
	 * arquivo_process.txt > arquivo.txt
	 * 
	 * @return List<String>
	 */
	public static List<String> getArquivosProcessados() {
		return Arrays.asList(getDiretorioOut().listFiles(getfileFilter()))
					 .stream()
					 .map(File::getName)
					 .map(s -> {
						 return StringUtils.remove(s, ConstantesProcessingApplicationUtils.ARQUIVO.PROCESS).toLowerCase();
					 })
					 .sorted()
					 .collect(Collectors.toList());
	}
	
	/**
	 * Retorna os arquivos com a extensao txt
	 * 
	 * @return FileFilter
	 */
	public static FileFilter getfileFilter() {
		return new FileFilter() {
			public boolean accept(final File file) {
				return file.getName().toLowerCase().endsWith(ConstantesProcessingApplicationUtils.EXTENSAO.PONTO_TXT.toLowerCase());
			}
		};
	}
	
	/**
	 * Retorna os arquivos processados (arquivo_process) com a extensao txt
	 * 
	 * @return FileFilter
	 */
	public static FileFilter getFileFilterProcess() {
		return new FileFilter() {
			public boolean accept(final File file) {
				return file.getName()
						   .toLowerCase()
						   .endsWith(new StringBuilder().append(ConstantesProcessingApplicationUtils.ARQUIVO.PROCESS)
								   						.append(ConstantesProcessingApplicationUtils.EXTENSAO.PONTO_TXT.toLowerCase()).toString());
			}
		};
	}
	
	/**
	 * Retorna File da pasta de entrada HOMEPATH/data/in
	 * 
	 * @return File
	 */
	public static File getDiretorioIn() {
		final File diretorio = new File(getInputPath());
		validaDiretorio(diretorio);
		return diretorio;
	}
	
	/**
	 * Retorna File da pasta de saida HOMEPATH/data/out
	 * 
	 * @return File
	 */
	public static File getDiretorioOut() {
		final File diretorio = new File(getOutputPath());
		validaDiretorio(diretorio);
		return diretorio;
	}
	
	/**
	 * Pasta de entrada HOMEPATH/data/in
	 * 
	 * @return String
	 */
	public static String getInputPath() {
		return System.getProperty("user.home")
					 .concat(File.separator)
					 .concat("data")
					 .concat(File.separator)
					 .concat("in");
	}
	
	/**
	 * Pasta de saida HOMEPATH/data/out
	 * 
	 * @return String
	 */
	public static String getOutputPath() {
		return System.getProperty("user.home")
					 .concat(File.separator)
					 .concat("data")
					 .concat(File.separator)
					 .concat("out");
	}
	
	/**
	 * Retorna o path do arquivo a ser gerado como processado.
	 * Neste caso o sistema ira atribuir o sufixo de _process
	 * ao nome do arquivo orginal
	 * 
	 * @param arquivo
	 * @return Path
	 */
	public static Path getResultPathProcess(File arquivo) {
		return Paths.get(getOutputPath())
					.resolve(StringUtils.replace(arquivo.getName().toLowerCase(), 
												 ConstantesProcessingApplicationUtils.EXTENSAO.PONTO_TXT.toLowerCase(), 
												 new StringBuilder().append(ConstantesProcessingApplicationUtils.ARQUIVO.PROCESS)
												 		            .append(ConstantesProcessingApplicationUtils.EXTENSAO.PONTO_TXT.toLowerCase())
												 		            .toString()));
	}
	
	/**
	 * Retorna o path do arquivo a ser gerado com erro.
	 * Neste caso o sistema ira atribuir o sufixo de _error
	 * ao nome do arquivo orginal
	 * 
	 * @param arquivo
	 * @return Path
	 */
	public static Path getResultPathError(File arquivo) {
		return Paths.get(getOutputPath())
					.resolve(StringUtils.replace(arquivo.getName().toLowerCase(), 
												 ConstantesProcessingApplicationUtils.EXTENSAO.PONTO_TXT.toLowerCase(), 
												 new StringBuilder().append(ConstantesProcessingApplicationUtils.ARQUIVO.ERROR)
												 		            .append(ConstantesProcessingApplicationUtils.EXTENSAO.PONTO_TXT.toLowerCase())
												 		            .toString()));
	}
	
	/**
	 * Validacoes dos diretorios que a aplicacao ira processar
	 * 
	 * @param diretorio
	 */
	public static void validaDiretorio(final File diretorio) {
		assertTrue(diretorio.isDirectory(),
				   new StringBuilder()
				   		.append("O caminho \" ")
				   		.append(diretorio.getAbsolutePath())
				   		.append(" \" não é um diretório.")
				   		.toString());

		assertTrue(diretorio.exists(),
				   new StringBuilder()
				   		.append("O diretório \" ")
				   		.append(diretorio.getAbsolutePath())
				   		.append(" \" não existe.")
				   		.toString());

		assertTrue(diretorio.canRead() && diretorio.canWrite(),
				   new StringBuilder()
				   		.append("Permissões negadas no diretório \" ")
				   		.append(diretorio.getAbsolutePath())
				   		.append(" \".")
				   		.toString());
	}
	
	/**
	 * Validacoes de objetos nao nulos, quando objeto for null
	 * uma excecao da aplicacao sera criada
	 * 
	 * @param object
	 * @param message
	 * @param args
	 * @return
	 */
	public static <T> T assertNotNull(T object, String message, Object... args) {
		if (Objects.isNull(object)) {
			throw new ProcessingApplicationException(args != null && args.length > 0 ? String.format(message, args) : message);
		}

		return object;
	}

	/**
	 * Validacoes de condicao verdade, quando objeto for false
	 * uma excecao da aplicacao sera criada
	 * 
	 * @param expression
	 * @param message
	 * @param args
	 */
	public static void assertTrue(boolean expression, String message, Object... args) {
		if (!expression) {
			throw new ProcessingApplicationException(args != null && args.length > 0 ? String.format(message, args) : message);
		}
	}
	
}

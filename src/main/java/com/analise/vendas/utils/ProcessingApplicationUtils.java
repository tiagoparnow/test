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

	public static List<String> getArquivos() {
		return Arrays.asList(getDiretorioIn().listFiles(getfileFilter()))
					 .stream()
					 .map(File::getName)
					 .sorted()
					 .collect(Collectors.toList());
	}
	
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
	
	public static FileFilter getfileFilter() {
		return new FileFilter() {
			public boolean accept(final File file) {
				return file.getName().toLowerCase().endsWith(ConstantesProcessingApplicationUtils.EXTENSAO.PONTO_TXT.toLowerCase());
			}
		};
	}
	
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
	
	public static File getDiretorioIn() {
		final File diretorio = new File(getInputPath());
		validaDiretorio(diretorio);
		return diretorio;
	}
	
	public static File getDiretorioOut() {
		final File diretorio = new File(getOutputPath());
		validaDiretorio(diretorio);
		return diretorio;
	}
	
	public static String getInputPath() {
		return System.getProperty("user.home")
					 .concat(File.separator)
					 .concat("data")
					 .concat(File.separator)
					 .concat("in");
	}
	
	public static String getOutputPath() {
		return System.getProperty("user.home")
					 .concat(File.separator)
					 .concat("data")
					 .concat(File.separator)
					 .concat("out");
	}
	
	public static Path getResultPathProcess(File arquivo) {
		return Paths.get(getOutputPath())
					.resolve(StringUtils.replace(arquivo.getName(), 
												 ConstantesProcessingApplicationUtils.EXTENSAO.PONTO_TXT.toLowerCase(), 
												 new StringBuilder().append(ConstantesProcessingApplicationUtils.ARQUIVO.PROCESS)
												 		            .append(ConstantesProcessingApplicationUtils.EXTENSAO.PONTO_TXT.toLowerCase())
												 		            .toString()));
	}
	
	public static Path getResultPathError(File arquivo) {
		return Paths.get(getOutputPath())
					.resolve(StringUtils.replace(arquivo.getName(), 
												 ConstantesProcessingApplicationUtils.EXTENSAO.PONTO_TXT.toLowerCase(), 
												 new StringBuilder().append(ConstantesProcessingApplicationUtils.ARQUIVO.ERROR)
												 		            .append(ConstantesProcessingApplicationUtils.EXTENSAO.PONTO_TXT.toLowerCase())
												 		            .toString()));
	}
	
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
	
	public static <T> T assertNotNull(T object, String message, Object... args) {
		if (Objects.isNull(object)) {
			throw new ProcessingApplicationException(args != null && args.length > 0 ? String.format(message, args) : message);
		}

		return object;
	}

	public static void assertTrue(boolean expression, String message, Object... args) {
		if (!expression) {
			throw new ProcessingApplicationException(args != null && args.length > 0 ? String.format(message, args) : message);
		}
	}
	
}

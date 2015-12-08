package gr.teicm.toulou.SnapChatyX.factories;

import gr.teicm.toulou.SnapChatyX.repositories.IRepository;

/**
 * 
 * 
 * @since Dec 8, 2015
 * 
 * @author Stamatios Tsalikis
 */
public class RepositoryFactory {
	
	private static final String REPOSITORIES_PACKAGE_NAME =
			IRepository.class.getPackage().getName();
	
	public static IRepository getInstance(String repositoryClassName)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(REPOSITORIES_PACKAGE_NAME);
		stringBuilder.append(".");
		stringBuilder.append(repositoryClassName);
		
		String repositoryClassFullName = stringBuilder.toString();
		
		Class<?> repositoryClass = Class.forName(repositoryClassFullName);
		
		return (IRepository) repositoryClass.newInstance();
	}
	
	public static IRepository getInstance(Class<?> repositoryClass)
			throws InstantiationException, IllegalAccessException {
		return (IRepository) repositoryClass.newInstance();
	}
	
}

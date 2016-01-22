package gr.teicm.toulou.SnapChatyX.model.transformer;

/**
 * 
 * @param <O> type before transformation
 * @param <T> type after transformation
 * 
 * @author Stamatios Tsalikis
 */
public interface ITransformer<T, O> {
	
	T transform(O object);
	
}

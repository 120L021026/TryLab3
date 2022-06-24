package pattern;

import java.util.HashMap;
import java.util.Map;

public class BusinessVotingSelection<C> implements SelectionStrategy<C>
{
    public Map<C, Double> everyResults(Map<C, Double> statistics, int quantity) throws Exception
    {
	Map<C, Double> ss=new HashMap<>();
	for(C forBusiness: statistics.keySet())
	{
	    // 支持率是否在2/3之上
	    if(statistics.get(forBusiness)>(double) 2/(double) 3)
	    {
		ss.put(forBusiness, statistics.get(forBusiness));
	    }
	}
	return ss;
    }
}

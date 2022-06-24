package pattern;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElectionSelection<C> implements SelectionStrategy<C>
{
    @Override
    public Map<C, Double> everyResults(Map<C, Double> statistics, int quantity) throws Exception
    {
	Map<C, Double> ss=new HashMap<>();
	// 排序
	Comparison<C> compare=new Comparison<C>(statistics);
	List<C> list=compare.compare();
	
	double limit=statistics.get(list.get(quantity-1));
	
	// 是否存在排序之后得到的数据结构，存在歧义的可以选出的人数
	int flag=0;
	if(quantity<statistics.size())
	{
	    if(statistics.get(list.get(quantity-1)).equals(statistics.get(list.get(quantity))))
	    {
		flag=1;
	    }
	}
	// 有歧义
	if(flag==1)
	{
	    for(C forElection: list)
	    {
		// 只选出明确可以被选入的候选人，即最终得到的结果数量<quantity
		if(statistics.get(forElection)==limit)
		{
		    break;
		}
		ss.put(forElection, statistics.get(forElection));
	    }
	}
	else if(flag==0)
	{
	    for(int i=0; i<quantity; i++)
	    {
		ss.put(list.get(i), statistics.get(list.get(i)));
	    }
	}
	return ss;
    }
}

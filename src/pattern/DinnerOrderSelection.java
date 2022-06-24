package pattern;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DinnerOrderSelection<C> implements SelectionStrategy<C>
{
    public Map<C, Double> everyResults(Map<C, Double> statistics, int quantity) throws Exception
    {
	Map<C, Double> ss=new HashMap<>();
	// 排序
	Comparison<C> compare=new Comparison<C>(statistics);
	// 按照喜欢程度的降序排序之后得到的菜品的列表
	List<C> list=compare.compare();
	
	for(int i=0; i<quantity; i++)
	{
	    ss.put(list.get(i), statistics.get(list.get(i)));
	}
	return ss;
    }
}

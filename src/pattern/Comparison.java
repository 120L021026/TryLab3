package pattern;

import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Comparison<C>
{
    /**
     * 实现对传入的Map类型的参数按照Value的值进行降序排序
     */
    private final Map<C, Double> compare;
    private List<C> list=new ArrayList<>();
    
    /**
     * 构造函数，将外部传来的参数赋值给自身的rep
     * 
     * @param compare
     *                    需要被排列的Map类型的参数
     */
    public Comparison(Map<C, Double> compare)
    {
	this.compare=compare;
	for(C forCreator: compare.keySet())
	{
	    list.add(forCreator);
	}
    }
    
    public ArrayList<C> compare()
    {
	ArrayList<C> ret;
	for(int i=0; i<compare.size()-1; i++)
	{
	    for(int j=compare.size()-1; j>0; j--)
	    {
		if(compare.get(list.get(j))>compare.get(list.get(j-1)))
		{
		    C temp=list.get(j);
		    list.set(j, list.get(j-1));
		    list.set(j-1, temp);
		}
	    }
	}
	ret=new ArrayList<>(list);
	return ret;
    }
}

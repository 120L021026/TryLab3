package legal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import auxiliary.Voter;

public class LegalStatistics<C>
{
    //每个投票人是否已经投票的映射
    Map<Voter, Boolean> haveVoter=new HashMap<>();
    
    public LegalStatistics(Map<Voter, Double> voters, Set<Voter> voterName)
    {
	Set<Voter> overVoter=voters.keySet();
	for(Voter forVoter: voterName)
	{
	    if(overVoter.contains(forVoter))
	    {
		haveVoter.put(forVoter, true);
	    }
	    else
	    {
		haveVoter.put(forVoter, false);
	    }
	}
    }
    
    /**
     * 判断是否可以开始计票
     * 
     * @return 可以开始计票则返回true
     */
    public boolean getBegin()
    {
	boolean ret=true;
	for(Voter forVoter: haveVoter.keySet())
	{
	    if(!haveVoter.get(forVoter))
	    {
		ret=false;
		System.out.println(forVoter.getID()+"没有投票");
		break;
	    }
	}
	return ret;
    }
}

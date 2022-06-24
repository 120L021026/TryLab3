package legal;

import java.util.HashMap;
import java.util.Map;
import vote.VoteItem;
import vote.VoteType;

public class CheckOptions<C>
{
    private Map<VoteItem<C>, Boolean> checkOptions=new HashMap<>();
    
    /**
     * 判断选票中是否出现非法选项
     * 
     * @param checkCandidates
     *                            经过判断候选人与名单相符程度之后的结果
     * @param voteType
     *                            选票类型
     */
    public CheckOptions(CheckCandidates<C> checkCandidates, VoteType voteType)
    {
	int flag=0;
	
	Map<VoteItem<C>, Boolean> mapCandidates=checkCandidates.getCheckCandidates();
	for(VoteItem<C> forVoteItem: mapCandidates.keySet())
	{
	    if(!voteType.checkLegality(forVoteItem.getVoteValue()))
	    {
		flag=1;
	    }
	}
	
	if(flag==1)
	{
	    for(VoteItem<C> forVoteItem: mapCandidates.keySet())
	    {
		checkOptions.put(forVoteItem, false);
	    }
	}
	else if(flag==0)
	{
	    for(VoteItem<C> forVoteItem: mapCandidates.keySet())
	    {
		checkOptions.put(forVoteItem, true);
	    }
	}
    }
    
    /**
     * 获得判断选票选项是否合法的映射
     * 
     * @return 选票与选票选项是否合法的映射
     */
    public Map<VoteItem<C>, Boolean> getCheckOptions()
    {
	Map<VoteItem<C>, Boolean> retCheckOptions=new HashMap<>(this.checkOptions);
	return retCheckOptions;
    }
}

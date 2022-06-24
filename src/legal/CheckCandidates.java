package legal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import vote.VoteItem;

public class CheckCandidates<C>
{
    private Map<VoteItem<C>, Boolean> checkCandidates=new HashMap<>();
    
    /**
     * 构造CheckCandidates类，判断是否存在选票中的选项不在候选名单
     * 
     * @param setVoteItem
     *                        一个投票人的所有选票
     * @param candidates
     *                        候选者
     */
    public CheckCandidates(Set<VoteItem<C>> setVoteItem, List<C> candidates)
    {
	int flag=0;
	
	for(VoteItem<C> forVoteItem: setVoteItem)
	{
	    if(!candidates.contains(forVoteItem.getCandidate()))
	    {
		flag=1;
		System.out.println(forVoteItem.getCandidate()+"不在候选名单中");
	    }
	}
	
	if(flag==1)
	{
	    for(VoteItem<C> forVoteItem: setVoteItem)
	    {
		checkCandidates.put(forVoteItem, false);
	    }
	}
	else if(flag==0)
	{
	    for(VoteItem<C> forVoteItem: setVoteItem)
	    {
		checkCandidates.put(forVoteItem, true);
	    }
	}
    }
    
    /**
     * 获得判断选票名单是否存在与候选名单的映射
     * 
     * @return 选票与选票内容是否合法的映射
     */
    public Map<VoteItem<C>, Boolean> getCheckCandidates()
    {
	Map<VoteItem<C>, Boolean> retCheckCandidates=new HashMap<>(this.checkCandidates);
	return retCheckCandidates;
    }
    
}

package legal;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import vote.VoteItem;
import vote.VoteType;

public class LegalAddVote<C>
{
    private Map<VoteItem<C>, Boolean> legalAddVote=new HashMap<>();
    private Set<C> setCandidates=new HashSet<>();
    private int flag=0;
    
    /**
     * 创建对增加选票方法进行合法性检查的对象
     * 
     * @param setVoteItem
     *                        选票人对所有候选对象而投票结果
     * @param candidates
     *                        候选对象
     * @param voteType
     *                        采取的投票方式
     */
    public LegalAddVote(Set<VoteItem<C>> setVoteItem, List<C> candidates, VoteType voteType)
    {
	for(VoteItem<C> forLegal: setVoteItem)
	{
	    setCandidates.add(forLegal.getCandidate());
	}
	
	for(C forList: candidates)
	{
	    // 在选票中有候选人没有被选中，则此选票完全作废：Value=false
	    if(!setCandidates.contains(forList))
	    {
		flag=1;
	    }
	}
	
	CheckCandidates<C> checkCandidates=new CheckCandidates<>(setVoteItem, candidates);
	Map<VoteItem<C>, Boolean> mapCheckCandidates=checkCandidates.getCheckCandidates();
	
	CheckOptions<C> checkOptions=new CheckOptions<>(checkCandidates, voteType);
	Map<VoteItem<C>, Boolean> mapCheckOptions=checkOptions.getCheckOptions();
	
	legalAddVote=new HashMap<VoteItem<C>, Boolean>(mapCheckOptions);
	
	if(flag==1)
	{
	    for(VoteItem<C> forLegal: mapCheckOptions.keySet())
	    {
		legalAddVote.put(forLegal, false);
	    }
	}
    }
    
    /**
     * 获得合法性检查的结果
     * 
     * @return 选票与是否合法的映射
     */
    public Map<VoteItem<C>, Boolean> getLegalAddVote()
    {
	Map<VoteItem<C>, Boolean> retLegalAddVote=new HashMap<>(legalAddVote);
	return retLegalAddVote;
    }
}

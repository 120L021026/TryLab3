package pattern;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import auxiliary.Voter;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

public class DinnerOrderStatistics<C> implements StatisticsStrategy<C>
{
    /**
     * 计算DinnerOrder每个菜的总分
     * 总分=喜欢*权重+不喜欢*权重+无所谓*权重
     */
    @Override
    public Map<C, Double> everyStatistics(VoteType voteType, Set<Vote<C>> votes) throws Exception
    {
	throw new Exception();
    }
    
    @Override
    public Map<C, Double> everyStatistics(VoteType voteType, Set<RealNameVote<C>> votes, Map<Voter, Double> voters)
	    throws Exception
    {
	Map<C, Double> ss=new HashMap<C, Double>();
	double result=0;
	C dish;
	for(RealNameVote<C> forRealNameVote: votes)
	{
	    Set<VoteItem<C>> rev=new HashSet<>(forRealNameVote.getVoteItems());
	    for(VoteItem<C> forVoteItems: rev)
	    {
		result=0;
		dish=forVoteItems.getCandidate();
		if(!ss.containsKey(dish))
		    ss.put(dish, result);
		result=ss.get(dish)
			+voteType.getScoreByOption(forVoteItems.getVoteValue())*voters.get(forRealNameVote.getVoter());
		ss.put(dish, result);
	    }
	}
	return ss;
    }
}

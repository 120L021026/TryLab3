package pattern;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import auxiliary.Voter;
import vote.RealNameVote;
import vote.VoteItem;
import vote.VoteType;

public class ChangeDinnerOrderStatistics<C> extends DinnerOrderStatistics<C> implements StatisticsStrategy<C>
{
    /**
     * 只计算投票为"Like"的票数，不再考虑权重为1的"Irrelevant"票
     */
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
		// 计算"Like"的票数
		if(forVoteItems.getVoteValue()=="Like")
		{
		    result=ss.get(dish)+voteType.getScoreByOption(forVoteItems.getVoteValue())
			    *voters.get(forRealNameVote.getVoter());
		    ss.put(dish, result);
		}
	    }
	}
	return ss;
    }
}

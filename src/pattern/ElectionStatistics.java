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

public class ElectionStatistics<C> implements StatisticsStrategy<C>
{
    /**
     * 计算Election每个候选人的结果
     * 支持的票数之和
     * 
     * @param voteType
     *                     投票选举类型
     * @param votes
     *                     匿名选票结果
     * 
     * @throws Exception
     */
    @Override
    public Map<C, Double> everyStatistics(VoteType voteType, Set<Vote<C>> votes) throws Exception
    {
	Map<C, Double> ss=new HashMap<C, Double>();
	double result=0.0;
	C person;
	for(Vote<C> forVote: votes)
	{
	    Set<VoteItem<C>> rev=new HashSet<>(forVote.getVoteItems());
	    for(VoteItem<C> forVoteItems: rev)
	    {
		result=0.0;
		person=forVoteItems.getCandidate();
		if(!ss.containsKey(person))
		    ss.put(person, result);
		if(forVoteItems.getVoteValue()=="Support")
		{
		    result=ss.get(person)+voteType.getScoreByOption(forVoteItems.getVoteValue());
		    ss.put(person, result);
		}
	    }
	}
	return ss;
    }
    
    @Override
    public Map<C, Double> everyStatistics(VoteType voteType, Set<RealNameVote<C>> votes, Map<Voter, Double> voters)
	    throws Exception
    {
	throw new Exception();
    }
}

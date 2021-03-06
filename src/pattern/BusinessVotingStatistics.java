package pattern;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import auxiliary.Proposal;
import auxiliary.Voter;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

public class BusinessVotingStatistics<C> implements StatisticsStrategy<C>
{
    /**
     * 计算BusinessVoting对一个提案的支持票数的集合
     * 支持票数=支持的各股东的股权之和
     */
    
    /**
     * 本投票活动实名，不可调用此函数，抛出异常
     * 
     * @param voteType
     *                     投票类型
     * @param votes
     *                     匿名投票的集合
     * 
     * @return
     *         无
     * 
     * @throws Exception
     */
    @Override
    public Map<C, Double> everyStatistics(VoteType voteType, Set<Vote<C>> votes) throws Exception
    {
	throw new Exception();
    }
    
    /**
     * 计算实名投票活动中各选票支持票数的集合
     * 
     * @param voteType
     *                     投票类型
     * @param votes
     *                     实名投票的集合
     * @param voters
     *                     投票者
     * 
     * @return
     *         候选对象与支持票数的集合
     * 
     * @throws Exception
     */
    @Override
    public Map<C, Double> everyStatistics(VoteType voteType, Set<RealNameVote<C>> votes, Map<Voter, Double> voters)
	    throws Exception
    {
	// key为候选对象：商业提案，value为支持率
	Map<C, Double> ss=new HashMap<C, Double>();
	double result=0;
	C proposal;
	for(RealNameVote<C> forRealNameVote: votes)
	{
	    Set<VoteItem<C>> rev=new HashSet<>(forRealNameVote.getVoteItems());
	    for(VoteItem<C> forVoteItems: rev)
	    {
		proposal=forVoteItems.getCandidate();
		ss.put(proposal, result);
		if(forVoteItems.getVoteValue()=="Support")
		{
		    result=ss.get(proposal)+voteType.getScoreByOption(forVoteItems.getVoteValue())
			    *voters.get(forRealNameVote.getVoter());
		    ss.put(proposal, result);
		}
	    }
	}
	return ss;
    }
}

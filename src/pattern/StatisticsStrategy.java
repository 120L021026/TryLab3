package pattern;

import java.util.Map;
import java.util.Set;
import auxiliary.Voter;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteType;

public interface StatisticsStrategy<C>
{
    /**
     * 计算匿名投票活动中各选票支持票数的集合
     * 
     * @param voteType
     *                     投票类型
     * @param votes
     *                     匿名投票的集合
     * 
     * @return
     *         候选对象与支持票数的映射
     * 
     * @throws Exception
     */
    public Map<C, Double> everyStatistics(VoteType voteType, Set<Vote<C>> votes) throws Exception;
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
    public Map<C, Double> everyStatistics(VoteType voteType, Set<RealNameVote<C>> votes, Map<Voter, Double> voters)
	    throws Exception;
}

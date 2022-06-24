package visitor;

import poll.GeneralPollImpl;
import poll.BusinessVoting;
import poll.DinnerOrder;
import poll.Election;

public class PercentVisitorImpl<C> implements PercentVisitor<C>
{
    /**
     * 计算提案表决中合法选票的比值
     * 
     * @param poll
     *                 提案表决活动
     * 
     * @return
     *         合法选票的比值
     */
    @Override
    public double visit(BusinessVoting poll)
    {
	// 所有选票
	double allVotes=poll.getRealNameVotes().size();
	// 合法选票
	double legalVotes=poll.getLegalVotesSize();
	double result=legalVotes/allVotes;
	return result;
    }
    
    /**
     * 计算代表选举中合法选票的比值
     * 
     * @param poll
     *                 代表选举活动
     * 
     * @return
     *         合法选票的比值
     */
    @Override
    public double visit(Election poll)
    {
	// 所有选票
	double allVotes=poll.getVotes().size();
	// 合法选票
	double legalVotes=poll.getLegalVotesSize();
	double result=legalVotes/allVotes;
	return result;
    }
    
    /**
     * 计算聚餐点菜中合法选票的比值
     * 
     * @param poll
     *                 聚餐点菜活动
     * 
     * @return
     *         合法选票的比值
     */
    @Override
    public double visit(DinnerOrder poll)
    {
	// 所有选票
	double allVotes=poll.getRealNameVotes().size();
	// 合法选票
	double legalVotes=poll.getLegalVotesSize();
	double result=legalVotes/allVotes;
	return result;
    }
}

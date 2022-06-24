package visitor;

import poll.BusinessVoting;
import poll.DinnerOrder;
import poll.Election;
import poll.GeneralPollImpl;

public interface PercentVisitor<C>
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
    public double visit(BusinessVoting poll);
    /**
     * 计算代表选举中合法选票的比值
     * 
     * @param poll
     *                 代表选举活动
     * 
     * @return
     *         合法选票的比值
     */
    public double visit(Election poll);
    /**
     * 计算聚餐点菜中合法选票的比值
     * 
     * @param poll
     *                 聚餐点菜活动
     * 
     * @return
     *         合法选票的比值
     */
    public double visit(DinnerOrder poll);
}

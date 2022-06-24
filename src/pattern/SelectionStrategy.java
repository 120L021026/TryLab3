package pattern;

import java.util.Map;
import java.util.Set;
import vote.Vote;
import vote.VoteType;

public interface SelectionStrategy<C>
{
    /**
     * 计算投票的最终结果
     * 
     * @param statistics
     *                       候选对象与其支持率的映射
     * @param quantity
     *                       候选数量
     * 
     * @return
     *         最终结果，即胜出对象与其支持票
     * 
     * @throws Exception
     */
    public Map<C, Double> everyResults(Map<C, Double> statistics, int quantity) throws Exception;
}

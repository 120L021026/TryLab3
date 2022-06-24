package poll;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import auxiliary.Dish;
import auxiliary.Voter;
import visitor.PercentVisitor;
import vote.RealNameVote;

public class DinnerOrder extends GeneralPollImpl<Dish> implements Poll<Dish>
{
    /**
     * 设定投票人及其权重
     * 
     * @param voters
     *                   投票人信息和对应权重
     */
    @Override
    public void addVoters(Map<Voter, Double> voters)
    {
	int minQuantity=voters.size();
	if(quantity>=minQuantity && quantity<=minQuantity+5)
	{
	    this.voters=new HashMap<Voter, Double>(voters);
	}
	else
	{
	    System.out.println("选菜数量错误");
	    System.exit(-1);
	}
    }
    
    /**
     * 接收一个投票人对全体候选对象的投票
     * 
     * @param vote
     *                 菜投票项
     */
    @Override
    public void addVote(RealNameVote<Dish> vote)
    {
	realNameVotes.add(vote);
    }
    
    @Override
    public String result()
    {
	String retString=results.toString();
	return retString;
    }
    
    /**
     * 计算合法选票所占比例
     * 
     * @return 合法选票所占比例
     */
    @Override
    public double accept(PercentVisitor<Dish> visitor)
    {
	return visitor.visit(this);
    }
}

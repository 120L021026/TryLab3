package poll;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import auxiliary.Proposal;
import auxiliary.Voter;
import visitor.PercentVisitor;
import vote.RealNameVote;
import vote.Vote;

public class BusinessVoting extends GeneralPollImpl<Proposal> implements Poll<Proposal>
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
	// 计算权重之和
	double sum=0;
	for(Voter forVoter: voters.keySet())
	{
	    sum=sum+voters.get(forVoter);
	}
	if(sum!=1.0)
	{
	    System.out.println("投票人权重错误，退出程序!");
	    System.exit(-1);
	}
	this.voters=new HashMap<Voter, Double>(voters);
    }
    
    /**
     * 接收一个投票人对全体候选对象的投票
     * 
     * @param vote
     *                 表决提案项
     */
    @Override
    public void addVote(RealNameVote<Proposal> vote)
    {
	realNameVotes.add(vote);
    }
    
    @Override
    public String result()
    {
	if(results.size()==0)
	{
	    return "无提案通过";
	}
	else
	{
	    String retString=results.toString();
	    return retString;
	}
    }
    
    /**
     * 计算合法选票所占比例
     * 
     * @return 合法选票所占比例
     */
    @Override
    public double accept(PercentVisitor<Proposal> visitor)
    {
	return visitor.visit(this);
    }
}
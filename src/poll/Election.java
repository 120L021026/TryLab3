package poll;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import auxiliary.Person;
import auxiliary.Proposal;
import legal.LegalAddVote;
import pattern.StatisticsStrategy;
import visitor.PercentVisitor;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

public class Election extends GeneralPollImpl<Person> implements Poll<Person>
{
    private final GeneralPollImpl<Person> poll=new GeneralPollImpl<>();
    // 选票类型
    private VoteType voteType=new VoteType(2);
    // 合法选票集合
    protected Set<Vote<Person>> statisticsVotes=new HashSet<>();
    
    /**
     * 接收一个匿名投票人对全体候选对象的投票
     * 
     * @param vote
     *                 一个匿名投票人对全体候选对象的投票记录集合
     */
    @Override
    public void addVote(Vote<Person> vote)
    {
	votes.add(vote);
    }
    
    /**
     * 按规则计票。
     * 首先判定选票的合法性，然后基于各个合法选票，按本次投票活动的规则进行计票，计算每个候选人的得分。
     * 
     * @param ss
     *               计票策略
     */
    @Override
    public void statistics(StatisticsStrategy<Person> ss)
    {
	for(Vote<Person> forVotes: votes)
	{
	    // 检查当前所拥有的选票的合法性
	    // 记录当前投票人支持的个数
	    int count=0;
	    
	    Set<VoteItem<Person>> setVoteItem=forVotes.getVoteItems();
	    LegalAddVote<Person> legalAddVote=new LegalAddVote<>(setVoteItem, candidates, this.voteType);
	    // 获得当前投票人所投选票的合法性的映射
	    Map<VoteItem<Person>, Boolean> mapLegalAddVote=legalAddVote.getLegalAddVote();
	    
	    // 支持Support的数量不能超过quantity
	    // 计算支持数量
	    for(VoteItem<Person> forSetVoteItem: setVoteItem)
	    {
		if(forSetVoteItem.getVoteValue().equals("Support"))
		{
		    count=count+1;
		}
	    }
	    // 如果违规，则将mapLegalAddVote中的映射全部更换为非法
	    if(count>quantity)
	    {
		for(VoteItem<Person> forMapLegalAddVote: mapLegalAddVote.keySet())
		{
		    mapLegalAddVote.put(forMapLegalAddVote, false);
		}
		// 直接开始下一次迭代，此投票人无效
		continue;
	    }
	    
	    // 当前投票人持有的合法选票
	    Set<VoteItem<Person>> setVote=new HashSet<>();
	    // 投票日期
	    Calendar voteDate=forVotes.getDate();
	    
	    for(VoteItem<Person> formap: mapLegalAddVote.keySet())
	    {
		if(mapLegalAddVote.get(formap))
		{
		    setVote.add(formap);
		}
	    }
	    
	    Vote<Person> partVotes=new Vote<>(setVote, voteDate);
	    
	    statisticsVotes.add(partVotes);
	    
	    if(mapLegalAddVote.containsValue(true))
		legalVotesSize++;
	}
	
	try
	{
	    this.statistics=new HashMap<Person, Double>(ss.everyStatistics(this.voteType, statisticsVotes));
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
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
    public double accept(PercentVisitor<Person> visitor)
    {
	return visitor.visit(this);
    }
}

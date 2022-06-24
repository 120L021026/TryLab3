package vote;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import auxiliary.Voter;

// immutable
public class Vote<C>
{
    // 缺省为“匿名”投票，即不需要管理投票人的信息
    
    // 一个投票人对所有候选对象的投票项集合
    private Set<VoteItem<C>> voteItems=new HashSet<>();
    // 投票时间
    private Calendar date=Calendar.getInstance();
    
    // Rep Invariants
    // 对于voteItems中的每个选票，选票项都应该在VoteType中
    // Abstract Function
    // 代表一个投票人对所有候选对象的投票项的聚合体
    // Safety from Rep Exposure
    // 防御式拷贝
    private void checkRep()
    {
	VoteType voteType1=new VoteType(1);
	VoteType voteType2=new VoteType(2);
	VoteType voteType3=new VoteType(3);
	for(VoteItem<C> forVoteItem: voteItems)
	{
	    assert voteType1.checkLegality(forVoteItem.getVoteValue())
		    | voteType2.checkLegality(forVoteItem.getVoteValue())
		    | voteType3.checkLegality(forVoteItem.getVoteValue());
	}
    }
    
    /**
     * 创建一个选票对象
     * 
     * @param voteItems
     *                      投票人对所有候选对象选票的集合
     * @param date
     *                      投票日期
     */
    public Vote(Set<VoteItem<C>> voteItems, Calendar date)
    {
	this.voteItems=new HashSet<>(voteItems);
	this.date=date;
    }
    
    /**
     * 查询该选票中包含的所有投票项
     * 
     * @return 所有投票项
     */
    public Set<VoteItem<C>> getVoteItems()
    {
	Set<VoteItem<C>> rev=new HashSet<>(voteItems);
	return rev;
    }
    
    /**
     * 返回投票日期
     * 
     * @return 投票日期
     */
    public Calendar getDate()
    {
	return this.date;
    }
    
    /**
     * 一个特定候选人是否包含本选票中
     * 
     * @param candidate
     *                      待查询的候选人
     * 
     * @return 若包含该候选人的投票项，则返回true，否则false
     */
    public boolean candidateIncluded(C candidate)
    {
	if(!voteItems.contains(candidate))
	    return true;
	return false;
    }
    
    @Override
    public int hashCode()
    {
	int result=31*voteItems.hashCode()+date.hashCode();
	return result;
    }
    
    /*
     * 采取引用相等来判断，避免出现不同投票人的投票相同而被吞并的情况
     */
    @Override
    public boolean equals(Object obj)
    {
	if(!(obj instanceof Vote))
	    return false;
	if(this==obj)
	    return true;
	return false;
    }
    
    public String toString()
    {
	String ret=voteItems.toString();
	return ret;
    }
}

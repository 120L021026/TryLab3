package vote;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import auxiliary.Voter;

// immutable
public class RealNameVote<C> extends Vote<C>
{
    // 投票人
    private Voter voter;
    
    // 装饰器模式，传入父类Vote类型，在其上增加返回投票人的方法
    
    /**
     * 构造方法，在Vote的基础上构造实名投票
     * 
     * @param vote
     *                  匿名投票对象
     * @param voter
     *                  投票人
     */
    public RealNameVote(Vote<C> vote, Voter voter)
    {
	super(vote.getVoteItems(), vote.getDate());
	this.voter=voter;
    }
    
    /**
     * 获得投票人
     * 
     * @return
     *         投票人
     */
    public Voter getVoter()
    {
	return this.voter;
    }
    
    /**
     * 通过投票人获得其对应的选票
     * 
     * @return
     *         投票人的选票
     */
    public Set<VoteItem<C>> getVoteItemsByVoter()
    {
	// 调用父类方法
	Set<VoteItem<C>> revByVoter=getVoteItems();
	return revByVoter;
    }
    
    @Override
    public int hashCode()
    {
	return 31*voter.hashCode()+super.hashCode();
    }
    
    /**
     * 基于Vote中重写的equals()方法，此次重写无关与引用或数据等价
     * 这里采用数据等价，但是其中在判断具体投票选项时，调用父类的方法依然是引用等价
     */
    @Override
    public boolean equals(Object obj)
    {
	if(this==obj)
	    return true;
	if(obj==null)
	    return false;
	if(!(obj instanceof RealNameVote))
	    return false;
	RealNameVote<C> other=(RealNameVote<C>) obj;
	if(!voter.equals(other.voter))
	    return false;
	if(!super.getVoteItems().equals(other.getVoteItems()))
	    return false;
	if(!super.getDate().equals(other.getDate()))
	    return false;
	return true;
    }
    
    /**
     * 重写toString()方法
     */
    @Override
    public String toString()
    {
	String ret=voter.getID()+super.toString();
	return ret;
    }
}

package vote;

import auxiliary.Person;

// immutable
public class VoteItem<C>
{
    // 本投票项所针对的候选对象
    private C candidate;
    // 对候选对象的具体投票选项，例如“支持”、“反对”等
    // 无需保存具体数值，需要时可以从投票活动的VoteType对象中查询得到
    private String value;
    
    // Rep Invariants
    // 每一个对应的选项都应该在VoteType中
    // Abstract Function
    // 表征一个投票人对一个候选对象的具体评价
    // Safety from Rep Exposure
    // 防御式拷贝
    private void checkRep()
    {
	VoteType voteType1=new VoteType(1);
	VoteType voteType2=new VoteType(2);
	VoteType voteType3=new VoteType(3);
	assert voteType1.checkLegality(value) | voteType2.checkLegality(value) | voteType3.checkLegality(value);
    }
    
    /**
     * 创建一个投票项对象 例如：针对候选对象“张三”，投票选项是“支持”
     * 
     * @param candidate
     *                      所针对的候选对象
     * @param value
     *                      所给出的投票选项
     */
    public VoteItem(C candidate, String value)
    {
	this.candidate=candidate;
	this.value=value;
    }
    
    /**
     * 得到该投票选项的具体投票项
     * 
     * @return 投票选项的具体投票项
     */
    public String getVoteValue()
    {
	String rev=value;
	return rev;
    }
    
    /**
     * 得到该投票选项所对应的候选人
     * 
     * @return 投票选项所对应的候选人
     */
    public C getCandidate()
    {
	C rec=candidate;
	return rec;
    }
    
    @Override
    public int hashCode()
    {
	int result=31*candidate.hashCode()+value.hashCode();
	return result;
    }
    
    /*
     * 采取引用相等来判断，避免出现不同投票人的投票相同而被吞并的情况
     */
    @Override
    public boolean equals(Object obj)
    {
	if(!(obj instanceof VoteItem))
	    return false;
	if(this==obj)
	    return true;
	return false;
    }
    
    @Override
    public String toString()
    {
	String retString="候选对象"+candidate+"选票为"+value;
	return retString;
    }
}

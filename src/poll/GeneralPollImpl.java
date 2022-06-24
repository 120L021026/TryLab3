package poll;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import auxiliary.Person;
import auxiliary.Voter;
import legal.LegalAddVote;
import legal.LegalStatistics;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import visitor.PercentVisitor;
import vote.VoteType;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;

public class GeneralPollImpl<C> implements Poll<C>
{
    // 投票活动的名称
    private String name;
    // 投票活动发起的时间
    private Calendar date;
    
    // 候选对象集合
    protected List<C> candidates;
    
    // 投票人集合，key为投票人，value为其在本次投票中所占权重
    protected Map<Voter, Double> voters;
    
    // 拟选出的候选对象最大数量
    protected int quantity;
    
    // 本次投票拟采用的投票类型（合法选项及各自对应的分数）
    private VoteType voteType;
    
    // 所有实名选票集合，包括非法选票
    protected Set<RealNameVote<C>> realNameVotes=new HashSet<>();
    // 所有匿名选票集合，包括非法选票
    protected Set<Vote<Person>> votes=new HashSet<>();
    
    // 选票的合法性
    protected Map<Voter, LegalAddVote<C>> legal;
    
    // 计票结果，key为候选对象，value为其得分
    protected Map<C, Double> statistics=new HashMap<C, Double>();
    // 遴选结果，key为候选对象，value为其排序位次
    protected Map<C, Double> results=new HashMap<C, Double>();
    
    // 合法选票数量
    protected int legalVotesSize=0;
    
    // Rep Invariants
    // 每个投票选项的候选人都需要记录在candidates中；
    // 拟选出的最大数量quantity要小于等于candidates的数量；
    // 每个投票人不能多次投票，且选票中不能存在同一个候选对象的多次投票
    // Abstract Function
    // 发起一个投票活动，记录投票的基本信息以及相应的投票记录
    // Safety from Rep Exposure
    // 防御式拷贝，调用包legal中的类来提前对非法操作进行判断
    private boolean checkRep()
    {
	assert quantity<=candidates.size();
	// 其他的RI由legal包解决
	return false;
    }
    
    /**
     * 设定投票活动的基本属性
     * 
     * @param name
     *                     投票活动名称
     * @param date
     *                     投票活动时间
     * @param type
     *                     投票选举规则
     * @param quantity
     *                     遴选最大数量
     */
    @Override
    public void setInfo(String name, Calendar date, VoteType type, int quantity)
    {
	this.name=name;
	this.date=date;
	this.voteType=type;
	this.quantity=quantity;
    }
    
    /**
     * 设定候选人
     * 
     * @param candidates
     *                       候选人
     */
    @Override
    public void addCandidates(List<C> candidates)
    {
	this.candidates=new ArrayList<C>(candidates);
    }
    
    /**
     * 设定投票人及其权重
     * 
     * @param voters
     *                   投票人信息和对应权重
     */
    @Override
    public void addVoters(Map<Voter, Double> voters)
    {
	this.voters=new HashMap<Voter, Double>(voters);
    }
    
    /**
     * 接收一个匿名投票人对全体候选对象的投票
     * 
     * @param vote
     *                 一个匿名投票人对全体候选对象的投票记录集合
     */
    @Override
    public void addVote(Vote<C> vote) throws Exception
    {
	// 暂时抛出异常，由子类选举投票重写
	throw new Exception();
    }
    
    /**
     * 接收一个实名投票人对全体候选对象的投票
     * 
     * @param vote
     *                 一个实名投票人对全体候选对象的投票记录集合
     */
    public void addVote(RealNameVote<C> vote) throws Exception
    {
	// 暂时抛出异常，由子类表决提案、聚会点餐重写
	throw new Exception();
    }
    
    /**
     * 按规则计票。
     * 首先判定选票的合法性，然后基于各个合法选票，按本次投票活动的规则进行计票，计算每个候选人的得分。
     * 
     * @param ss
     *               计票策略
     */
    @Override
    public void statistics(StatisticsStrategy<C> ss)
    {
	// 选择基于StatisticsStrategy接口的实现方式
	// 已经投票的投票人的集合
	Set<Voter> voterName=new HashSet<>();
	
	// 合法选票
	Set<RealNameVote<C>> statisticsVotes=new HashSet<>();
	
	for(RealNameVote<C> forVotes: realNameVotes)
	{
	    // 记录已参与投票的投票人
	    voterName.add(forVotes.getVoter());
	    
	    // 检查当前所拥有的选票的合法性
	    Set<VoteItem<C>> setVoteItem=forVotes.getVoteItemsByVoter();
	    LegalAddVote<C> legalAddVote=new LegalAddVote<>(setVoteItem, candidates, voteType);
	    // 获得当前投票人所投选票的合法性的映射
	    Map<VoteItem<C>, Boolean> mapLegalAddVote=legalAddVote.getLegalAddVote();
	    
	    // 当前投票人持有的合法选票
	    Set<VoteItem<C>> setVote=new HashSet<>();
	    // 投票日期
	    Calendar voteDate=forVotes.getDate();
	    
	    for(VoteItem<C> formap: mapLegalAddVote.keySet())
	    {
		if(mapLegalAddVote.get(formap))
		{
		    setVote.add(formap);
		}
	    }
	    
	    Vote<C> partVotes=new Vote<>(setVote, voteDate);
	    RealNameVote<C> partRealNameVotes=new RealNameVote<>(partVotes, forVotes.getVoter());
	    
	    statisticsVotes.add(partRealNameVotes);
	    
	    if(mapLegalAddVote.containsValue(true))
		legalVotesSize++;
	    else
		System.out.println("投票人"+forVotes.getVoter().getID()+"选票无效");
	}
	
	// 判断投票人参与度的完整性
	LegalStatistics<C> legalStatistics=new LegalStatistics<>(this.voters, voterName);
	
	boolean start=legalStatistics.getBegin();
	try
	{
	    // 只有当所有的投票人都投票了，计票才可以开始
	    if(start)
	    {
		this.statistics=new HashMap<C, Double>(ss.everyStatistics(voteType, statisticsVotes, voters));
	    }
	    else
	    {
		System.out.println("存在没有投票的投票人");
		System.exit(-1);
	    }
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
    }
    
    /**
     * 按规则遴选。
     * 基于计票结果，按本次投票活动的规则进行遴选，从中选出quantity个候选人作为最终结果。
     * 
     * @param ss
     *               遴选策略
     */
    @Override
    public void selection(SelectionStrategy<C> ss)
    {
	try
	{
	    this.results=new HashMap<C, Double>(ss.everyResults(statistics, quantity));
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
    }
    
    @Override
    public String result()
    {
	String ret="GeneralPollImpl中无法访问";
	return ret;
    }
    
    /**
     * 计算合法选票所占比例
     * 
     * @return 合法选票所占比例
     */
    @Override
    public double accept(PercentVisitor<C> visitor) throws Exception
    {
	throw new Exception();
    }
    
    /**
     * 以下为类中的getter方法
     */
    public String getName()
    {
	return name;
    }
    
    public Calendar getDate()
    {
	return date;
    }
    
    public List<C> getCandidates()
    {
	return new ArrayList<C>(candidates);
    }
    
    public Map<Voter, Double> getVoters()
    {
	return new HashMap<Voter, Double>(voters);
    }
    
    public int getQuantity()
    {
	return quantity;
    }
    
    public VoteType getVoteType()
    {
	return voteType;
    }
    
    public Set<RealNameVote<C>> getRealNameVotes()
    {
	return new HashSet<RealNameVote<C>>(realNameVotes);
    }
    
    public Set<Vote<Person>> getVotes()
    {
	return new HashSet<Vote<Person>>(votes);
    }
    
    public Map<Voter, LegalAddVote<C>> getLegal()
    {
	return new HashMap<Voter, LegalAddVote<C>>(legal);
    }
    
    public Map<C, Double> getStatistics()
    {
	return new HashMap<C, Double>(statistics);
    }
    
    public Map<C, Double> getResults()
    {
	return new HashMap<C, Double>(results);
    }
    
    public int getLegalVotesSize()
    {
	return legalVotesSize;
    }
}

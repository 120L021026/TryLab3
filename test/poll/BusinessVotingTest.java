package poll;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.jupiter.api.Test;
import auxiliary.Person;
import auxiliary.Proposal;
import auxiliary.Voter;
import pattern.BusinessVotingSelection;
import pattern.BusinessVotingStatistics;
import pattern.ElectionStatistics;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

class BusinessVotingTest
{
    // test strategy
    // 通过建立BusinessVoting类，来对其中的选项进行加入
    // 理论上一切选票都可以加入，因为addVote方法只是求取所有的选票，不要求合法
    // 对于statistics()方法的测试，采取既有合法选票又有非法选票和选票全部合法两种策略
    
    @Test
    public void testAddVote()
    {
	// 创建BusinessVoting类
	GeneralPollImpl<Proposal> businessVotingPoll=new BusinessVoting();
	
	// 投票活动名称
	String name="BusinessVoting";
	// 投票日期
	Calendar date=Calendar.getInstance();
	date.set(2019, 6, 1);
	// 遴选最大数量
	int quantity=1;
	
	// 选票类型
	VoteType voteType=new VoteType(1);
	
	// 设立投票基本信息
	businessVotingPoll.setInfo(name, date, voteType, quantity);
	
	// 增加候选对象
	Proposal testProposal=new Proposal("Proposal", date);
	List<Proposal> testList=new ArrayList<>();
	testList.add(testProposal);
	businessVotingPoll.addCandidates(testList);
	
	// 增加投票人
	Voter voter1=new Voter("A");
	Voter voter2=new Voter("B");
	Voter voter3=new Voter("C");
	Map<Voter, Double> testMap=new HashMap<>();
	testMap.put(voter1, 0.21);
	testMap.put(voter2, 0.24);
	testMap.put(voter3, 0.55);
	businessVotingPoll.addVoters(testMap);
	
	// 设定选票
	VoteItem<Proposal> voteItem1=new VoteItem<>(testProposal, "Support");
	Set<VoteItem<Proposal>> testSet1=new HashSet<>();
	testSet1.add(voteItem1);
	Vote<Proposal> vote1=new Vote<Proposal>(testSet1, date);
	RealNameVote<Proposal> realNameVote1=new RealNameVote<>(vote1, voter1);
	
	VoteItem<Proposal> voteItem2=new VoteItem<>(testProposal, "Object");
	Set<VoteItem<Proposal>> testSet2=new HashSet<>();
	testSet2.add(voteItem2);
	Vote<Proposal> vote2=new Vote<Proposal>(testSet2, date);
	RealNameVote<Proposal> realNameVote2=new RealNameVote<>(vote2, voter2);
	
	VoteItem<Proposal> voteItem3=new VoteItem<>(testProposal, "Support");
	Set<VoteItem<Proposal>> testSet3=new HashSet<>();
	testSet3.add(voteItem3);
	Vote<Proposal> vote3=new Vote<Proposal>(testSet3, date);
	RealNameVote<Proposal> realNameVote3=new RealNameVote<>(vote3, voter3);
	
	try
	{
	    businessVotingPoll.addVote(realNameVote1);
	    businessVotingPoll.addVote(realNameVote2);
	    businessVotingPoll.addVote(realNameVote3);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	// 同时手动判断内容是否相同
	System.out.println("实际输出：");
	for(RealNameVote<Proposal> tr: businessVotingPoll.getRealNameVotes())
	{
	    System.out.println(tr);
	    System.out.println();
	}
	
	Set<RealNameVote<Proposal>> retSet=new HashSet<>();
	
	VoteItem<Proposal> testVoteItem1=new VoteItem<>(testProposal, "Support");
	Set<VoteItem<Proposal>> testTestSet1=new HashSet<>();
	testTestSet1.add(testVoteItem1);
	Vote<Proposal> testVote1=new Vote<Proposal>(testTestSet1, date);
	RealNameVote<Proposal> testRealNameVote1=new RealNameVote<>(testVote1, voter1);
	
	VoteItem<Proposal> testVoteItem2=new VoteItem<>(testProposal, "Object");
	Set<VoteItem<Proposal>> testTestSet2=new HashSet<>();
	testTestSet2.add(testVoteItem2);
	Vote<Proposal> testVote2=new Vote<Proposal>(testTestSet2, date);
	RealNameVote<Proposal> testRealNameVote2=new RealNameVote<>(testVote2, voter2);
	
	VoteItem<Proposal> testVoteItem3=new VoteItem<>(testProposal, "Support");
	Set<VoteItem<Proposal>> testTestSet3=new HashSet<>();
	testTestSet3.add(testVoteItem3);
	Vote<Proposal> testVote3=new Vote<Proposal>(testTestSet3, date);
	RealNameVote<Proposal> testRealNameVote3=new RealNameVote<>(testVote3, voter3);
	
	retSet.add(testRealNameVote1);
	retSet.add(testRealNameVote2);
	retSet.add(testRealNameVote3);
	
	// 同时手动判断内容是否相同
	System.out.println("期望输出：");
	for(RealNameVote<Proposal> tr: retSet)
	{
	    System.out.println(tr);
	    System.out.println();
	}
	
	assertFalse(retSet.equals(businessVotingPoll.getRealNameVotes()));
    }
    
    /**
     * 对statistics()方法的测试
     * 选票全部合法的情况
     */
    @Test
    public void testStatistics1()
    {
	// 创建BusinessVoting类
	GeneralPollImpl<Proposal> businessVotingPoll=new BusinessVoting();
	
	// 投票活动名称
	String name="BusinessVoting";
	// 投票日期
	Calendar date=Calendar.getInstance();
	date.set(2019, 6, 1);
	// 遴选最大数量
	int quantity=1;
	
	// 选票类型
	VoteType voteType=new VoteType(1);
	
	// 设立投票基本信息
	businessVotingPoll.setInfo(name, date, voteType, quantity);
	
	// 增加候选对象
	Proposal testProposal=new Proposal("Proposal", date);
	List<Proposal> testList=new ArrayList<>();
	testList.add(testProposal);
	businessVotingPoll.addCandidates(testList);
	
	// 增加投票人
	Voter voter1=new Voter("A");
	Voter voter2=new Voter("B");
	Voter voter3=new Voter("C");
	Map<Voter, Double> testMap=new HashMap<>();
	testMap.put(voter1, 0.21);
	testMap.put(voter2, 0.24);
	testMap.put(voter3, 0.55);
	businessVotingPoll.addVoters(testMap);
	
	// 设定选票
	VoteItem<Proposal> voteItem1=new VoteItem<>(testProposal, "Support");
	Set<VoteItem<Proposal>> testSet1=new HashSet<>();
	testSet1.add(voteItem1);
	Vote<Proposal> vote1=new Vote<Proposal>(testSet1, date);
	RealNameVote<Proposal> realNameVote1=new RealNameVote<>(vote1, voter1);
	
	VoteItem<Proposal> voteItem2=new VoteItem<>(testProposal, "Object");
	Set<VoteItem<Proposal>> testSet2=new HashSet<>();
	testSet2.add(voteItem2);
	Vote<Proposal> vote2=new Vote<Proposal>(testSet2, date);
	RealNameVote<Proposal> realNameVote2=new RealNameVote<>(vote2, voter2);
	
	VoteItem<Proposal> voteItem3=new VoteItem<>(testProposal, "Support");
	Set<VoteItem<Proposal>> testSet3=new HashSet<>();
	testSet3.add(voteItem3);
	Vote<Proposal> vote3=new Vote<Proposal>(testSet3, date);
	RealNameVote<Proposal> realNameVote3=new RealNameVote<>(vote3, voter3);
	
	try
	{
	    businessVotingPoll.addVote(realNameVote1);
	    businessVotingPoll.addVote(realNameVote2);
	    businessVotingPoll.addVote(realNameVote3);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	Map<Proposal, Double> retMap=new HashMap<>();
	retMap.put(testProposal, 0.76);
	
	StatisticsStrategy<Proposal> ss=new BusinessVotingStatistics<>();
	businessVotingPoll.statistics(ss);
	
	assertEquals(retMap, businessVotingPoll.getStatistics());
    }
    
    /**
     * 对statistics()方法的测试
     * 选票不全合法的情况
     */
    @Test
    public void testStatistics2()
    {
	// 创建BusinessVoting类
	GeneralPollImpl<Proposal> businessVotingPoll=new BusinessVoting();
	
	// 投票活动名称
	String name="BusinessVoting";
	// 投票日期
	Calendar date=Calendar.getInstance();
	date.set(2019, 6, 1);
	// 遴选最大数量
	int quantity=3;
	
	// 选票类型
	VoteType voteType=new VoteType(1);
	
	// 设立投票基本信息
	businessVotingPoll.setInfo(name, date, voteType, quantity);
	
	// 增加候选对象
	Proposal testProposal=new Proposal("Proposal", date);
	List<Proposal> testList=new ArrayList<>();
	testList.add(testProposal);
	businessVotingPoll.addCandidates(testList);
	
	// 增加投票人
	Voter voter1=new Voter("A");
	Voter voter2=new Voter("B");
	Voter voter3=new Voter("C");
	Map<Voter, Double> testMap=new HashMap<>();
	testMap.put(voter1, 0.21);
	testMap.put(voter2, 0.24);
	testMap.put(voter3, 0.55);
	businessVotingPoll.addVoters(testMap);
	
	// 设定选票
	VoteItem<Proposal> voteItem1=new VoteItem<>(testProposal, "Support");
	Set<VoteItem<Proposal>> testSet1=new HashSet<>();
	testSet1.add(voteItem1);
	Vote<Proposal> vote1=new Vote<Proposal>(testSet1, date);
	RealNameVote<Proposal> realNameVote1=new RealNameVote<>(vote1, voter1);
	
	VoteItem<Proposal> voteItem2=new VoteItem<>(testProposal, "Object");
	Set<VoteItem<Proposal>> testSet2=new HashSet<>();
	testSet2.add(voteItem2);
	Vote<Proposal> vote2=new Vote<Proposal>(testSet2, date);
	RealNameVote<Proposal> realNameVote2=new RealNameVote<>(vote2, voter2);
	
	VoteItem<Proposal> voteItem3=new VoteItem<>(testProposal, "Like");
	Set<VoteItem<Proposal>> testSet3=new HashSet<>();
	testSet3.add(voteItem3);
	Vote<Proposal> vote3=new Vote<Proposal>(testSet3, date);
	RealNameVote<Proposal> realNameVote3=new RealNameVote<>(vote3, voter3);
	
	try
	{
	    businessVotingPoll.addVote(realNameVote1);
	    businessVotingPoll.addVote(realNameVote2);
	    businessVotingPoll.addVote(realNameVote3);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	Map<Proposal, Double> retMap=new HashMap<>();
	retMap.put(testProposal, 0.21);
	
	StatisticsStrategy<Proposal> ss=new BusinessVotingStatistics<>();
	businessVotingPoll.statistics(ss);
	
	assertEquals(retMap, businessVotingPoll.getStatistics());
    }
    
    @Test
    public void testSelection()
    {
	// 创建BusinessVoting类
	GeneralPollImpl<Proposal> businessVotingPoll=new BusinessVoting();
	
	// 投票活动名称
	String name="BusinessVoting";
	// 投票日期
	Calendar date=Calendar.getInstance();
	date.set(2019, 6, 1);
	// 遴选最大数量
	int quantity=3;
	
	// 选票类型
	VoteType voteType=new VoteType(1);
	
	// 设立投票基本信息
	businessVotingPoll.setInfo(name, date, voteType, quantity);
	
	// 增加候选对象
	Proposal testProposal=new Proposal("Proposal", date);
	List<Proposal> testList=new ArrayList<>();
	testList.add(testProposal);
	businessVotingPoll.addCandidates(testList);
	
	// 增加投票人
	Voter voter1=new Voter("A");
	Voter voter2=new Voter("B");
	Voter voter3=new Voter("C");
	Map<Voter, Double> testMap=new HashMap<>();
	testMap.put(voter1, 0.21);
	testMap.put(voter2, 0.24);
	testMap.put(voter3, 0.55);
	businessVotingPoll.addVoters(testMap);
	
	// 设定选票
	VoteItem<Proposal> voteItem1=new VoteItem<>(testProposal, "Support");
	Set<VoteItem<Proposal>> testSet1=new HashSet<>();
	testSet1.add(voteItem1);
	Vote<Proposal> vote1=new Vote<Proposal>(testSet1, date);
	RealNameVote<Proposal> realNameVote1=new RealNameVote<>(vote1, voter1);
	
	VoteItem<Proposal> voteItem2=new VoteItem<>(testProposal, "Object");
	Set<VoteItem<Proposal>> testSet2=new HashSet<>();
	testSet2.add(voteItem2);
	Vote<Proposal> vote2=new Vote<Proposal>(testSet2, date);
	RealNameVote<Proposal> realNameVote2=new RealNameVote<>(vote2, voter2);
	
	VoteItem<Proposal> voteItem3=new VoteItem<>(testProposal, "Support");
	Set<VoteItem<Proposal>> testSet3=new HashSet<>();
	testSet3.add(voteItem3);
	Vote<Proposal> vote3=new Vote<Proposal>(testSet3, date);
	RealNameVote<Proposal> realNameVote3=new RealNameVote<>(vote3, voter3);
	
	try
	{
	    businessVotingPoll.addVote(realNameVote1);
	    businessVotingPoll.addVote(realNameVote2);
	    businessVotingPoll.addVote(realNameVote3);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	StatisticsStrategy<Proposal> ss=new BusinessVotingStatistics<>();
	businessVotingPoll.statistics(ss);
	
	SelectionStrategy<Proposal> selectionStrategy=new BusinessVotingSelection<>();
	businessVotingPoll.selection(selectionStrategy);
	
	Map<Proposal, Double> retMap=new HashMap<>();
	retMap.put(testProposal, 0.76);
	
	assertEquals(retMap, businessVotingPoll.getResults());
    }
}

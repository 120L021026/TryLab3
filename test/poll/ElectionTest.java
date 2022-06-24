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
import pattern.ElectionSelection;
import pattern.ElectionStatistics;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

class ElectionTest
{
    // test strategy
    // 通过建立Election类，来对其中的选项进行加入
    // 理论上一切选票都可以加入，因为addVote方法只是求取所有的选票，不要求合法
    // 对于statistics()方法的测试，采取既有合法选票又有非法选票和选票全部合法两种策略
    // 对于selection()方法的测试，采取结果<quantity和=quantity两种方式
    
    // 投票活动名称
    String name="Election";
    // 投票日期
    Calendar date=Calendar.getInstance();
    // 遴选最大数量
    int quantity=3;
    // 选票类型
    VoteType voteType=new VoteType(2);
    
    // 增加候选对象
    Person tpe1=new Person("Ben", 20);
    Person tpe2=new Person("Bob", 21);
    Person tpe3=new Person("Alice", 23);
    Person tpe4=new Person("Lucy", 19);
    
    // 增加投票人
    Voter voter1=new Voter("A");
    Voter voter2=new Voter("B");
    Voter voter3=new Voter("C");
    
    @Test
    public void testAddVote()
    {
	// 创建BusinessVoting类
	GeneralPollImpl<Person> electionPoll=new Election();
	
	date.set(2019, 6, 1);
	
	// 设立投票基本信息
	electionPoll.setInfo(name, date, voteType, quantity);
	
	// 设立候选人
	List<Person> testList=new ArrayList<>();
	testList.add(tpe1);
	testList.add(tpe2);
	testList.add(tpe3);
	testList.add(tpe4);
	
	electionPoll.addCandidates(testList);
	
	// 设立投票者
	Map<Voter, Double> testMap=new HashMap<>();
	testMap.put(voter1, 1.0);
	testMap.put(voter2, 1.0);
	testMap.put(voter3, 1.0);
	
	electionPoll.addVoters(testMap);
	
	// 设定选票
	VoteItem<Person> voteItem11=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem12=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> voteItem13=new VoteItem<>(tpe3, "Object");
	VoteItem<Person> voteItem14=new VoteItem<>(tpe4, "Waiver");
	Set<VoteItem<Person>> testSet1=new HashSet<>();
	testSet1.add(voteItem11);
	testSet1.add(voteItem12);
	testSet1.add(voteItem13);
	testSet1.add(voteItem14);
	Vote<Person> vote1=new Vote<Person>(testSet1, date);
	
	VoteItem<Person> voteItem21=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem22=new VoteItem<>(tpe2, "Object");
	VoteItem<Person> voteItem23=new VoteItem<>(tpe3, "Object");
	VoteItem<Person> voteItem24=new VoteItem<>(tpe4, "Support");
	Set<VoteItem<Person>> testSet2=new HashSet<>();
	testSet1.add(voteItem21);
	testSet1.add(voteItem22);
	testSet1.add(voteItem23);
	testSet1.add(voteItem24);
	Vote<Person> vote2=new Vote<Person>(testSet2, date);
	
	VoteItem<Person> voteItem31=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem32=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> voteItem33=new VoteItem<>(tpe3, "Support");
	VoteItem<Person> voteItem34=new VoteItem<>(tpe4, "Support");
	Set<VoteItem<Person>> testSet3=new HashSet<>();
	testSet1.add(voteItem31);
	testSet1.add(voteItem32);
	testSet1.add(voteItem33);
	testSet1.add(voteItem34);
	Vote<Person> vote3=new Vote<Person>(testSet3, date);
	
	try
	{
	    electionPoll.addVote(vote1);
	    electionPoll.addVote(vote2);
	    electionPoll.addVote(vote3);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	// 同时手动判断内容是否相同
	System.out.println("实际输出：");
	for(Vote<Person> tv: electionPoll.getVotes())
	{
	    System.out.println(tv);
	    System.out.println();
	}
	
	Set<Vote<Person>> retSet=new HashSet<>();
	
	VoteItem<Person> testVoteItem11=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> testVoteItem12=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> testVoteItem13=new VoteItem<>(tpe3, "Object");
	VoteItem<Person> testVoteItem14=new VoteItem<>(tpe4, "Waiver");
	Set<VoteItem<Person>> testTestSet1=new HashSet<>();
	testTestSet1.add(testVoteItem11);
	testTestSet1.add(testVoteItem12);
	testTestSet1.add(testVoteItem13);
	testTestSet1.add(testVoteItem14);
	Vote<Person> testVote1=new Vote<Person>(testTestSet1, date);
	
	VoteItem<Person> testVoteItem21=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> testVoteItem22=new VoteItem<>(tpe2, "Object");
	VoteItem<Person> testVoteItem23=new VoteItem<>(tpe3, "Object");
	VoteItem<Person> testVoteItem24=new VoteItem<>(tpe4, "Support");
	Set<VoteItem<Person>> testTestSet2=new HashSet<>();
	testTestSet1.add(testVoteItem21);
	testTestSet1.add(testVoteItem22);
	testTestSet1.add(testVoteItem23);
	testTestSet1.add(testVoteItem24);
	Vote<Person> testVote2=new Vote<Person>(testTestSet2, date);
	
	VoteItem<Person> testVoteItem31=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> testVoteItem32=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> testVoteItem33=new VoteItem<>(tpe3, "Support");
	VoteItem<Person> testVoteItem34=new VoteItem<>(tpe4, "Support");
	Set<VoteItem<Person>> testTestSet3=new HashSet<>();
	testTestSet1.add(testVoteItem31);
	testTestSet1.add(testVoteItem32);
	testTestSet1.add(testVoteItem33);
	testTestSet1.add(testVoteItem34);
	Vote<Person> testVote3=new Vote<Person>(testTestSet3, date);
	
	retSet.add(testVote1);
	retSet.add(testVote2);
	retSet.add(testVote3);
	
	// 同时手动判断内容是否相同
	System.out.println("期望输出：");
	for(Vote<Person> tv: retSet)
	{
	    System.out.println(tv);
	    System.out.println();
	}
	
	assertFalse(retSet.equals(electionPoll.getVotes()));
    }
    
    @Test
    public void testStatistics1()
    {
	// 创建BusinessVoting类
	GeneralPollImpl<Person> electionPoll=new Election();
	
	date.set(2019, 6, 1);
	
	// 设立投票基本信息
	electionPoll.setInfo(name, date, voteType, quantity);
	
	// 设立候选人
	List<Person> testList=new ArrayList<>();
	testList.add(tpe1);
	testList.add(tpe2);
	testList.add(tpe3);
	testList.add(tpe4);
	
	electionPoll.addCandidates(testList);
	
	// 设立投票者
	Map<Voter, Double> testMap=new HashMap<>();
	testMap.put(voter1, 1.0);
	testMap.put(voter2, 1.0);
	testMap.put(voter3, 1.0);
	
	electionPoll.addVoters(testMap);
	
	// 设定选票
	VoteItem<Person> voteItem11=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem12=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> voteItem13=new VoteItem<>(tpe3, "Object");
	VoteItem<Person> voteItem14=new VoteItem<>(tpe4, "Waiver");
	Set<VoteItem<Person>> testSet1=new HashSet<>();
	testSet1.add(voteItem11);
	testSet1.add(voteItem12);
	testSet1.add(voteItem13);
	testSet1.add(voteItem14);
	Vote<Person> vote1=new Vote<Person>(testSet1, date);
	
	VoteItem<Person> voteItem21=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem22=new VoteItem<>(tpe2, "Object");
	VoteItem<Person> voteItem23=new VoteItem<>(tpe3, "Object");
	VoteItem<Person> voteItem24=new VoteItem<>(tpe4, "Support");
	Set<VoteItem<Person>> testSet2=new HashSet<>();
	testSet2.add(voteItem21);
	testSet2.add(voteItem22);
	testSet2.add(voteItem23);
	testSet2.add(voteItem24);
	Vote<Person> vote2=new Vote<Person>(testSet2, date);
	
	VoteItem<Person> voteItem31=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem32=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> voteItem33=new VoteItem<>(tpe3, "Support");
	VoteItem<Person> voteItem34=new VoteItem<>(tpe4, "Support");
	Set<VoteItem<Person>> testSet3=new HashSet<>();
	testSet3.add(voteItem31);
	testSet3.add(voteItem32);
	testSet3.add(voteItem33);
	testSet3.add(voteItem34);
	Vote<Person> vote3=new Vote<Person>(testSet3, date);
	
	try
	{
	    electionPoll.addVote(vote1);
	    electionPoll.addVote(vote2);
	    electionPoll.addVote(vote3);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	Map<Person, Double> retMap=new HashMap<>();
	retMap.put(tpe1, 2.0);
	retMap.put(tpe2, 1.0);
	retMap.put(tpe3, 0.0);
	retMap.put(tpe4, 1.0);
	
	StatisticsStrategy<Person> ss=new ElectionStatistics<>();
	electionPoll.statistics(ss);
	
	assertEquals(retMap, electionPoll.getStatistics());
    }
    
    @Test
    public void testStatistics2()
    {
	// 新增一个候选名单之外的候选人
	Person tpe5=new Person("Daniel", 21);
	
	// 创建BusinessVoting类
	GeneralPollImpl<Person> electionPoll=new Election();
	
	date.set(2019, 6, 1);
	
	// 设立投票基本信息
	electionPoll.setInfo(name, date, voteType, quantity);
	
	// 设立候选人
	List<Person> testList=new ArrayList<>();
	testList.add(tpe1);
	testList.add(tpe2);
	testList.add(tpe3);
	testList.add(tpe4);
	
	electionPoll.addCandidates(testList);
	
	// 设立投票者
	Map<Voter, Double> testMap=new HashMap<>();
	testMap.put(voter1, 1.0);
	testMap.put(voter2, 1.0);
	testMap.put(voter3, 1.0);
	
	electionPoll.addVoters(testMap);
	
	// 设定选票
	VoteItem<Person> voteItem11=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem12=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> voteItem13=new VoteItem<>(tpe3, "Object");
	VoteItem<Person> voteItem14=new VoteItem<>(tpe4, "Waiver");
	Set<VoteItem<Person>> testSet1=new HashSet<>();
	testSet1.add(voteItem11);
	testSet1.add(voteItem12);
	testSet1.add(voteItem13);
	testSet1.add(voteItem14);
	Vote<Person> vote1=new Vote<Person>(testSet1, date);
	
	// 有非法选票
	VoteItem<Person> voteItem21=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem22=new VoteItem<>(tpe2, "Object");
	VoteItem<Person> voteItem23=new VoteItem<>(tpe5, "Object");
	VoteItem<Person> voteItem24=new VoteItem<>(tpe4, "Support");
	Set<VoteItem<Person>> testSet2=new HashSet<>();
	testSet2.add(voteItem21);
	testSet2.add(voteItem22);
	testSet2.add(voteItem23);
	testSet2.add(voteItem24);
	Vote<Person> vote2=new Vote<Person>(testSet2, date);
	
	VoteItem<Person> voteItem31=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem32=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> voteItem33=new VoteItem<>(tpe3, "Support");
	VoteItem<Person> voteItem34=new VoteItem<>(tpe4, "Support");
	Set<VoteItem<Person>> testSet3=new HashSet<>();
	testSet3.add(voteItem31);
	testSet3.add(voteItem32);
	testSet3.add(voteItem33);
	testSet3.add(voteItem34);
	Vote<Person> vote3=new Vote<Person>(testSet3, date);
	
	try
	{
	    electionPoll.addVote(vote1);
	    electionPoll.addVote(vote2);
	    electionPoll.addVote(vote3);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	Map<Person, Double> retMap=new HashMap<>();
	retMap.put(tpe1, 1.0);
	retMap.put(tpe2, 1.0);
	retMap.put(tpe3, 0.0);
	retMap.put(tpe4, 0.0);
	
	StatisticsStrategy<Person> ss=new ElectionStatistics<>();
	electionPoll.statistics(ss);
	
	assertEquals(retMap, electionPoll.getStatistics());
    }
    
    /**
     * 存在投票者投票相同
     */
    @Test
    public void testStatistics3()
    {
	// 创建BusinessVoting类
	GeneralPollImpl<Person> electionPoll=new Election();
	
	date.set(2019, 6, 1);
	
	// 设立投票基本信息
	electionPoll.setInfo(name, date, voteType, quantity);
	
	// 设立候选人
	List<Person> testList=new ArrayList<>();
	testList.add(tpe1);
	testList.add(tpe2);
	testList.add(tpe3);
	testList.add(tpe4);
	
	electionPoll.addCandidates(testList);
	
	// 设立投票者
	Map<Voter, Double> testMap=new HashMap<>();
	testMap.put(voter1, 1.0);
	testMap.put(voter2, 1.0);
	testMap.put(voter3, 1.0);
	
	electionPoll.addVoters(testMap);
	
	// 设定选票
	VoteItem<Person> voteItem11=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem12=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> voteItem13=new VoteItem<>(tpe3, "Object");
	VoteItem<Person> voteItem14=new VoteItem<>(tpe4, "Waiver");
	Set<VoteItem<Person>> testSet1=new HashSet<>();
	testSet1.add(voteItem11);
	testSet1.add(voteItem12);
	testSet1.add(voteItem13);
	testSet1.add(voteItem14);
	Vote<Person> vote1=new Vote<Person>(testSet1, date);
	
	VoteItem<Person> voteItem21=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem22=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> voteItem23=new VoteItem<>(tpe3, "Object");
	VoteItem<Person> voteItem24=new VoteItem<>(tpe4, "Waiver");
	Set<VoteItem<Person>> testSet2=new HashSet<>();
	testSet2.add(voteItem21);
	testSet2.add(voteItem22);
	testSet2.add(voteItem23);
	testSet2.add(voteItem24);
	Vote<Person> vote2=new Vote<Person>(testSet2, date);
	
	VoteItem<Person> voteItem31=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem32=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> voteItem33=new VoteItem<>(tpe3, "Support");
	VoteItem<Person> voteItem34=new VoteItem<>(tpe4, "Support");
	Set<VoteItem<Person>> testSet3=new HashSet<>();
	testSet3.add(voteItem31);
	testSet3.add(voteItem32);
	testSet3.add(voteItem33);
	testSet3.add(voteItem34);
	Vote<Person> vote3=new Vote<Person>(testSet3, date);
	
	try
	{
	    electionPoll.addVote(vote1);
	    electionPoll.addVote(vote2);
	    electionPoll.addVote(vote3);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	Map<Person, Double> retMap=new HashMap<>();
	retMap.put(tpe1, 2.0);
	retMap.put(tpe2, 2.0);
	retMap.put(tpe3, 0.0);
	retMap.put(tpe4, 0.0);
	
	StatisticsStrategy<Person> ss=new ElectionStatistics<>();
	electionPoll.statistics(ss);
	
	assertEquals(retMap, electionPoll.getStatistics());
    }
    
    @Test
    public void testSelection1()
    {
	// 创建BusinessVoting类
	GeneralPollImpl<Person> electionPoll=new Election();
	
	date.set(2019, 6, 1);
	
	// 设立投票基本信息
	electionPoll.setInfo(name, date, voteType, quantity);
	
	// 设立候选人
	List<Person> testList=new ArrayList<>();
	testList.add(tpe1);
	testList.add(tpe2);
	testList.add(tpe3);
	testList.add(tpe4);
	
	electionPoll.addCandidates(testList);
	
	// 设立投票者
	Map<Voter, Double> testMap=new HashMap<>();
	testMap.put(voter1, 1.0);
	testMap.put(voter2, 1.0);
	testMap.put(voter3, 1.0);
	
	electionPoll.addVoters(testMap);
	
	// 设定选票
	VoteItem<Person> voteItem11=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem12=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> voteItem13=new VoteItem<>(tpe3, "Object");
	VoteItem<Person> voteItem14=new VoteItem<>(tpe4, "Waiver");
	Set<VoteItem<Person>> testSet1=new HashSet<>();
	testSet1.add(voteItem11);
	testSet1.add(voteItem12);
	testSet1.add(voteItem13);
	testSet1.add(voteItem14);
	Vote<Person> vote1=new Vote<Person>(testSet1, date);
	
	VoteItem<Person> voteItem21=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem22=new VoteItem<>(tpe2, "Object");
	VoteItem<Person> voteItem23=new VoteItem<>(tpe3, "Object");
	VoteItem<Person> voteItem24=new VoteItem<>(tpe4, "Support");
	Set<VoteItem<Person>> testSet2=new HashSet<>();
	testSet2.add(voteItem21);
	testSet2.add(voteItem22);
	testSet2.add(voteItem23);
	testSet2.add(voteItem24);
	Vote<Person> vote2=new Vote<Person>(testSet2, date);
	
	VoteItem<Person> voteItem31=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem32=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> voteItem33=new VoteItem<>(tpe3, "Support");
	VoteItem<Person> voteItem34=new VoteItem<>(tpe4, "Support");
	Set<VoteItem<Person>> testSet3=new HashSet<>();
	testSet3.add(voteItem31);
	testSet3.add(voteItem32);
	testSet3.add(voteItem33);
	testSet3.add(voteItem34);
	Vote<Person> vote3=new Vote<Person>(testSet3, date);
	
	try
	{
	    electionPoll.addVote(vote1);
	    electionPoll.addVote(vote2);
	    electionPoll.addVote(vote3);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	StatisticsStrategy<Person> ss=new ElectionStatistics<>();
	electionPoll.statistics(ss);
	
	SelectionStrategy<Person> selectionStrategy=new ElectionSelection<>();
	electionPoll.selection(selectionStrategy);
	
	Map<Person, Double> retMap=new HashMap<>();
	retMap.put(tpe1, 2.0);
	retMap.put(tpe2, 1.0);
	retMap.put(tpe4, 1.0);
	
	assertEquals(retMap, electionPoll.getResults());
    }
    
    /**
     * 存在有歧义的结果，最终结果只取确定的候选者，结果<quantity
     */
    @Test
    public void testSelection2()
    {
	// 创建BusinessVoting类
	GeneralPollImpl<Person> electionPoll=new Election();
	
	date.set(2019, 6, 1);
	
	// 设立投票基本信息
	electionPoll.setInfo(name, date, voteType, quantity);
	
	// 设立候选人
	List<Person> testList=new ArrayList<>();
	testList.add(tpe1);
	testList.add(tpe2);
	testList.add(tpe3);
	testList.add(tpe4);
	
	electionPoll.addCandidates(testList);
	
	// 设立投票者
	Map<Voter, Double> testMap=new HashMap<>();
	testMap.put(voter1, 1.0);
	testMap.put(voter2, 1.0);
	testMap.put(voter3, 1.0);
	
	electionPoll.addVoters(testMap);
	
	// 设定选票
	VoteItem<Person> voteItem11=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem12=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> voteItem13=new VoteItem<>(tpe3, "Object");
	VoteItem<Person> voteItem14=new VoteItem<>(tpe4, "Waiver");
	Set<VoteItem<Person>> testSet1=new HashSet<>();
	testSet1.add(voteItem11);
	testSet1.add(voteItem12);
	testSet1.add(voteItem13);
	testSet1.add(voteItem14);
	Vote<Person> vote1=new Vote<Person>(testSet1, date);
	
	VoteItem<Person> voteItem21=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem22=new VoteItem<>(tpe2, "Object");
	VoteItem<Person> voteItem23=new VoteItem<>(tpe3, "Support");
	VoteItem<Person> voteItem24=new VoteItem<>(tpe4, "Support");
	Set<VoteItem<Person>> testSet2=new HashSet<>();
	testSet2.add(voteItem21);
	testSet2.add(voteItem22);
	testSet2.add(voteItem23);
	testSet2.add(voteItem24);
	Vote<Person> vote2=new Vote<Person>(testSet2, date);
	
	VoteItem<Person> voteItem31=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem32=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> voteItem33=new VoteItem<>(tpe3, "Support");
	VoteItem<Person> voteItem34=new VoteItem<>(tpe4, "Support");
	Set<VoteItem<Person>> testSet3=new HashSet<>();
	testSet3.add(voteItem31);
	testSet3.add(voteItem32);
	testSet3.add(voteItem33);
	testSet3.add(voteItem34);
	Vote<Person> vote3=new Vote<Person>(testSet3, date);
	
	try
	{
	    electionPoll.addVote(vote1);
	    electionPoll.addVote(vote2);
	    electionPoll.addVote(vote3);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	StatisticsStrategy<Person> ss=new ElectionStatistics<>();
	electionPoll.statistics(ss);
	
	SelectionStrategy<Person> selectionStrategy=new ElectionSelection<>();
	electionPoll.selection(selectionStrategy);
	
	Map<Person, Double> retMap=new HashMap<>();
	retMap.put(tpe1, 2.0);
	
	assertEquals(retMap, electionPoll.getResults());
    }
}

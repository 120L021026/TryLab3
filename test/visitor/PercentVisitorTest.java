package visitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import auxiliary.Dish;
import auxiliary.Person;
import auxiliary.Proposal;
import auxiliary.Voter;
import pattern.BusinessVotingStatistics;
import pattern.DinnerOrderStatistics;
import pattern.ElectionStatistics;
import pattern.StatisticsStrategy;
import poll.BusinessVoting;
import poll.DinnerOrder;
import poll.Election;
import poll.GeneralPollImpl;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

public class PercentVisitorTest
{
    @Test
    public void testVisit1()
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
	
	PercentVisitor<Proposal> percentVisitor=new PercentVisitorImpl<>();
	
	try
	{
	    assertEquals(1.0, businessVotingPoll.accept(percentVisitor));
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
    }
    
    @Test
    public void testVisit2()
    {
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
	
	StatisticsStrategy<Person> ss=new ElectionStatistics<>();
	electionPoll.statistics(ss);

	PercentVisitor<Person> percentVisitor=new PercentVisitorImpl<>();
	
	try
	{
	    assertEquals((double) 1/(double) 3, electionPoll.accept(percentVisitor));
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
    }
    
    @Test
    public void testVisit3()
    {
	// 投票活动名称
	String name="DinnerOrder";
	// 投票日期
	Calendar date=Calendar.getInstance();
	// 遴选最大数量
	int quantity=3;
	// 选票类型
	VoteType voteType=new VoteType(3);
	
	// 增加候选对象
	Dish dish1=new Dish("DishA", 20);
	Dish dish2=new Dish("DishB", 30);
	Dish dish3=new Dish("DishC", 15);
	Dish dish4=new Dish("DishD", 100);
	
	// 增加投票人
	Voter voter1=new Voter("A");
	Voter voter2=new Voter("B");
	Voter voter3=new Voter("C");
	
	// 创建DinnerOrder类
	GeneralPollImpl<Dish> dinnerOrderPoll=new DinnerOrder();
	
	date.set(2019, 6, 1);
	
	dinnerOrderPoll.setInfo(name, date, voteType, quantity);
	
	// 设立候选对象
	List<Dish> testList=new ArrayList<>();
	testList.add(dish1);
	testList.add(dish2);
	testList.add(dish3);
	testList.add(dish4);
	
	dinnerOrderPoll.addCandidates(testList);
	
	// 设立投票人
	Map<Voter, Double> testMap=new HashMap<>();
	testMap.put(voter1, 3.0);
	testMap.put(voter2, 2.0);
	testMap.put(voter3, 1.0);
	
	dinnerOrderPoll.addVoters(testMap);
	
	// 设定选票
	VoteItem<Dish> voteItem11=new VoteItem<>(dish1, "Like");
	VoteItem<Dish> voteItem12=new VoteItem<>(dish2, "Dislike");
	VoteItem<Dish> voteItem13=new VoteItem<>(dish3, "Like");
	VoteItem<Dish> voteItem14=new VoteItem<>(dish4, "Like");
	Set<VoteItem<Dish>> testSet1=new HashSet<>();
	testSet1.add(voteItem11);
	testSet1.add(voteItem12);
	testSet1.add(voteItem13);
	testSet1.add(voteItem14);
	Vote<Dish> vote1=new Vote<Dish>(testSet1, date);
	RealNameVote<Dish> realNameVote1=new RealNameVote<>(vote1, voter1);
	
	VoteItem<Dish> voteItem21=new VoteItem<>(dish1, "Dislike");
	VoteItem<Dish> voteItem22=new VoteItem<>(dish2, "Object");
	VoteItem<Dish> voteItem23=new VoteItem<>(dish3, "Like");
	VoteItem<Dish> voteItem24=new VoteItem<>(dish4, "Like");
	Set<VoteItem<Dish>> testSet2=new HashSet<>();
	testSet2.add(voteItem21);
	testSet2.add(voteItem22);
	testSet2.add(voteItem23);
	testSet2.add(voteItem24);
	Vote<Dish> vote2=new Vote<Dish>(testSet2, date);
	RealNameVote<Dish> realNameVote2=new RealNameVote<>(vote2, voter2);
	
	VoteItem<Dish> voteItem31=new VoteItem<>(dish1, "Like");
	VoteItem<Dish> voteItem32=new VoteItem<>(dish2, "Irrelevant");
	VoteItem<Dish> voteItem33=new VoteItem<>(dish3, "Dislike");
	VoteItem<Dish> voteItem34=new VoteItem<>(dish4, "Irrelevant");
	Set<VoteItem<Dish>> testSet3=new HashSet<>();
	testSet3.add(voteItem31);
	testSet3.add(voteItem32);
	testSet3.add(voteItem33);
	testSet3.add(voteItem34);
	Vote<Dish> vote3=new Vote<Dish>(testSet3, date);
	RealNameVote<Dish> realNameVote3=new RealNameVote<>(vote3, voter3);
	
	try
	{
	    dinnerOrderPoll.addVote(realNameVote1);
	    dinnerOrderPoll.addVote(realNameVote2);
	    dinnerOrderPoll.addVote(realNameVote3);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	StatisticsStrategy<Dish> ss=new DinnerOrderStatistics<>();
	dinnerOrderPoll.statistics(ss);
	
	PercentVisitor<Dish> percentVisitor=new PercentVisitorImpl<>();
	
	try
	{
	    assertEquals((double) 2/(double) 3, dinnerOrderPoll.accept(percentVisitor));
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
    }
}

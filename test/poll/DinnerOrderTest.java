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
import auxiliary.Dish;
import auxiliary.Person;
import auxiliary.Proposal;
import auxiliary.Voter;
import pattern.DinnerOrderSelection;
import pattern.DinnerOrderStatistics;
import pattern.ElectionStatistics;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

class DinnerOrderTest
{
    // test strategy
    // 通过建立DinnerOrder类，来对其中的选项进行加入
    // 理论上一切选票都可以加入，因为addVote方法只是求取所有的选票，不要求合法
    // 对于statistics()方法的测试，采取既有合法选票又有非法选票和选票全部合法两种策略
    
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
    
    @Test
    public void testAddVote()
    {
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
	//// Like, Dislike, Irrelevant
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
	VoteItem<Dish> voteItem22=new VoteItem<>(dish2, "Dislike");
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
	
	// 同时手动判断内容是否相同
	System.out.println("实际输出：");
	for(RealNameVote<Dish> tr: dinnerOrderPoll.getRealNameVotes())
	{
	    System.out.println(tr);
	    System.out.println();
	}
	
	Set<RealNameVote<Dish>> retSet=new HashSet<>();
	
	VoteItem<Dish> testVoteItem11=new VoteItem<>(dish1, "Like");
	VoteItem<Dish> testVoteItem12=new VoteItem<>(dish2, "Dislike");
	VoteItem<Dish> testVoteItem13=new VoteItem<>(dish3, "Like");
	VoteItem<Dish> testVoteItem14=new VoteItem<>(dish4, "Like");
	Set<VoteItem<Dish>> testTestSet1=new HashSet<>();
	testTestSet1.add(testVoteItem11);
	testTestSet1.add(testVoteItem12);
	testTestSet1.add(testVoteItem13);
	testTestSet1.add(testVoteItem14);
	Vote<Dish> testVote1=new Vote<Dish>(testTestSet1, date);
	RealNameVote<Dish> testRealNameVote1=new RealNameVote<>(testVote1, voter1);
	
	VoteItem<Dish> testVoteItem21=new VoteItem<>(dish1, "Dislike");
	VoteItem<Dish> testVoteItem22=new VoteItem<>(dish2, "Dislike");
	VoteItem<Dish> testVoteItem23=new VoteItem<>(dish3, "Like");
	VoteItem<Dish> testVoteItem24=new VoteItem<>(dish4, "Like");
	Set<VoteItem<Dish>> testTestSet2=new HashSet<>();
	testTestSet2.add(testVoteItem21);
	testTestSet2.add(testVoteItem22);
	testTestSet2.add(testVoteItem23);
	testTestSet2.add(testVoteItem24);
	Vote<Dish> testVote2=new Vote<Dish>(testTestSet2, date);
	RealNameVote<Dish> testRealNameVote2=new RealNameVote<>(testVote2, voter2);
	
	VoteItem<Dish> testVoteItem31=new VoteItem<>(dish1, "Like");
	VoteItem<Dish> testVoteItem32=new VoteItem<>(dish2, "Irrelevant");
	VoteItem<Dish> testVoteItem33=new VoteItem<>(dish3, "Dislike");
	VoteItem<Dish> testVoteItem34=new VoteItem<>(dish4, "Irrelevant");
	Set<VoteItem<Dish>> testTestSet3=new HashSet<>();
	testTestSet3.add(testVoteItem31);
	testTestSet3.add(testVoteItem32);
	testTestSet3.add(testVoteItem33);
	testTestSet3.add(testVoteItem34);
	Vote<Dish> testVote3=new Vote<Dish>(testTestSet3, date);
	RealNameVote<Dish> testRealNameVote3=new RealNameVote<>(testVote3, voter3);
	
	retSet.add(testRealNameVote1);
	retSet.add(testRealNameVote2);
	retSet.add(testRealNameVote3);
	
	// 同时手动判断内容是否相同
	System.out.println("期望输出：");
	for(RealNameVote<Dish> tr: retSet)
	{
	    System.out.println(tr);
	    System.out.println();
	}
	
	assertFalse(retSet.equals(dinnerOrderPoll.getRealNameVotes()));
    }
    
    @Test
    public void testStatistics1()
    {
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
	VoteItem<Dish> voteItem22=new VoteItem<>(dish2, "Dislike");
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
	
	Map<Dish, Double> retMap=new HashMap<>();
	retMap.put(dish1, 8.0);
	retMap.put(dish2, 1.0);
	retMap.put(dish3, 10.0);
	retMap.put(dish4, 11.0);
	
	StatisticsStrategy<Dish> ss=new DinnerOrderStatistics<>();
	dinnerOrderPoll.statistics(ss);
	
	assertEquals(retMap, dinnerOrderPoll.getStatistics());
    }
    
    @Test
    public void testStatistics2()
    {
	// 新建非候选对象的菜品
	Dish dish5=new Dish("DishE", 8000);
	
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
	//// Like, Dislike, Irrelevant
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
	
	VoteItem<Dish> voteItem31=new VoteItem<>(dish5, "Like");
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
	
	Map<Dish, Double> retMap=new HashMap<>();
	retMap.put(dish1, 6.0);
	retMap.put(dish2, 0.0);
	retMap.put(dish3, 6.0);
	retMap.put(dish4, 6.0);
	
	StatisticsStrategy<Dish> ss=new DinnerOrderStatistics<>();
	dinnerOrderPoll.statistics(ss);
	
	assertEquals(retMap, dinnerOrderPoll.getStatistics());
    }
    
    /**
     * 由于某种情况下选菜的随机性，因此此处只采取一定可以无歧义的选出quantity道菜的方式
     * 至于会产生歧义的选菜方式，进行手动测试
     */
    @Test
    public void testSelection()
    {
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
	VoteItem<Dish> voteItem22=new VoteItem<>(dish2, "Dislike");
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
	
	SelectionStrategy<Dish> selectionStrategy=new DinnerOrderSelection<>();
	dinnerOrderPoll.selection(selectionStrategy);
	
	Map<Dish, Double> retMap=new HashMap<>();
	retMap.put(dish1, 8.0);
	retMap.put(dish3, 10.0);
	retMap.put(dish4, 11.0);
	
	assertEquals(retMap, dinnerOrderPoll.getResults());
    }
}

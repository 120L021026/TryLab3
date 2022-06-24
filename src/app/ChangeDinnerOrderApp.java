package app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import auxiliary.Dish;
import auxiliary.Voter;
import pattern.ChangeDinnerOrderStatistics;
import pattern.DinnerOrderSelection;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import poll.DinnerOrder;
import poll.GeneralPollImpl;
import visitor.PercentVisitor;
import visitor.PercentVisitorImpl;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

public class ChangeDinnerOrderApp
{
    public static void main(String[] args) throws Exception
    {
	// 创建DinnerOrder类
	GeneralPollImpl<Dish> dinnerOrderPoll=new DinnerOrder();
	
	// 投票活动名称
	String name="DinnerOrder";
	// 投票日期
	Calendar date=Calendar.getInstance();
	date.set(2019, 6, 1);
	// 遴选最大数量
	int quantity=4;
	
	// 选票类型
	VoteType voteType=new VoteType(3);
	// 设立基本信息
	dinnerOrderPoll.setInfo(name, date, voteType, quantity);
	
	// 增加候选对象
	Dish dish1=new Dish("DishA", 20);
	Dish dish2=new Dish("DishB", 30);
	Dish dish3=new Dish("DishC", 15);
	Dish dish4=new Dish("DishD", 100);
	Dish dish5=new Dish("DishE", 30);
	Dish dish6=new Dish("DishF", 60);
	// 设立候选对象
	List<Dish> candidateList=new ArrayList<>();
	candidateList.add(dish1);
	candidateList.add(dish2);
	candidateList.add(dish3);
	candidateList.add(dish4);
	candidateList.add(dish5);
	candidateList.add(dish6);
	dinnerOrderPoll.addCandidates(candidateList);
	
	// 增加投票人
	Voter voter1=new Voter("A");
	Voter voter2=new Voter("B");
	Voter voter3=new Voter("C");
	Voter voter4=new Voter("D");
	// 设立投票人，不区分权重
	Map<Voter, Double> voterMap=new HashMap<>();
	voterMap.put(voter1, 1.0);
	voterMap.put(voter2, 1.0);
	voterMap.put(voter3, 1.0);
	voterMap.put(voter4, 1.0);
	dinnerOrderPoll.addVoters(voterMap);
	
	// 设定选票
	VoteItem<Dish> voteItem11=new VoteItem<>(dish1, "Like");
	VoteItem<Dish> voteItem12=new VoteItem<>(dish2, "Dislike");
	VoteItem<Dish> voteItem13=new VoteItem<>(dish3, "Irrelevant");
	VoteItem<Dish> voteItem14=new VoteItem<>(dish4, "Like");
	VoteItem<Dish> voteItem15=new VoteItem<>(dish5, "Like");
	VoteItem<Dish> voteItem16=new VoteItem<>(dish6, "Irrelevant");
	Set<VoteItem<Dish>> itemSet1=new HashSet<>();
	itemSet1.add(voteItem11);
	itemSet1.add(voteItem12);
	itemSet1.add(voteItem13);
	itemSet1.add(voteItem14);
	itemSet1.add(voteItem15);
	itemSet1.add(voteItem16);
	Vote<Dish> vote1=new Vote<Dish>(itemSet1, date);
	RealNameVote<Dish> realNameVote1=new RealNameVote<>(vote1, voter1);
	
	VoteItem<Dish> voteItem21=new VoteItem<>(dish1, "Like");
	VoteItem<Dish> voteItem22=new VoteItem<>(dish2, "Dislike");
	VoteItem<Dish> voteItem23=new VoteItem<>(dish3, "Like");
	VoteItem<Dish> voteItem24=new VoteItem<>(dish4, "Dislike");
	VoteItem<Dish> voteItem25=new VoteItem<>(dish5, "Irrelevant");
	VoteItem<Dish> voteItem26=new VoteItem<>(dish6, "Like");
	Set<VoteItem<Dish>> itemSet2=new HashSet<>();
	itemSet2.add(voteItem21);
	itemSet2.add(voteItem22);
	itemSet2.add(voteItem23);
	itemSet2.add(voteItem24);
	itemSet2.add(voteItem25);
	itemSet2.add(voteItem26);
	Vote<Dish> vote2=new Vote<Dish>(itemSet2, date);
	RealNameVote<Dish> realNameVote2=new RealNameVote<>(vote2, voter2);
	
	VoteItem<Dish> voteItem31=new VoteItem<>(dish1, "Dislike");
	VoteItem<Dish> voteItem32=new VoteItem<>(dish2, "Like");
	VoteItem<Dish> voteItem33=new VoteItem<>(dish3, "Support");
	VoteItem<Dish> voteItem34=new VoteItem<>(dish4, "Like");
	VoteItem<Dish> voteItem35=new VoteItem<>(dish5, "Like");
	Set<VoteItem<Dish>> itemSet3=new HashSet<>();
	itemSet3.add(voteItem31);
	itemSet3.add(voteItem32);
	itemSet3.add(voteItem33);
	itemSet3.add(voteItem34);
	itemSet3.add(voteItem35);
	Vote<Dish> vote3=new Vote<Dish>(itemSet3, date);
	RealNameVote<Dish> realNameVote3=new RealNameVote<>(vote3, voter3);
	
	VoteItem<Dish> voteItem41=new VoteItem<>(dish1, "Irrelevant");
	VoteItem<Dish> voteItem42=new VoteItem<>(dish2, "Dislike");
	VoteItem<Dish> voteItem43=new VoteItem<>(dish3, "Like");
	VoteItem<Dish> voteItem44=new VoteItem<>(dish4, "Like");
	VoteItem<Dish> voteItem45=new VoteItem<>(dish5, "Like");
	VoteItem<Dish> voteItem46=new VoteItem<>(dish6, "Like");
	Set<VoteItem<Dish>> itemSet4=new HashSet<>();
	itemSet4.add(voteItem41);
	itemSet4.add(voteItem42);
	itemSet4.add(voteItem43);
	itemSet4.add(voteItem44);
	itemSet4.add(voteItem45);
	itemSet4.add(voteItem46);
	Vote<Dish> vote4=new Vote<Dish>(itemSet4, date);
	RealNameVote<Dish> realNameVote4=new RealNameVote<>(vote4, voter4);
	
	try
	{
	    dinnerOrderPoll.addVote(realNameVote1);
	    dinnerOrderPoll.addVote(realNameVote2);
	    dinnerOrderPoll.addVote(realNameVote3);
	    dinnerOrderPoll.addVote(realNameVote4);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	StatisticsStrategy<Dish> ss=new ChangeDinnerOrderStatistics<>();
	dinnerOrderPoll.statistics(ss);
	
	SelectionStrategy<Dish> selectionStrategy=new DinnerOrderSelection<>();
	dinnerOrderPoll.selection(selectionStrategy);
	
	PercentVisitor<Dish> percentVisitor=new PercentVisitorImpl<>();
	
	try
	{
	    System.out.println("合法选票的比例为："+dinnerOrderPoll.accept(percentVisitor));
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	System.out.println(dinnerOrderPoll.result());
    }
}

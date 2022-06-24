package app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import auxiliary.Person;
import auxiliary.Voter;
import pattern.ElectionSelection;
import pattern.ElectionStatistics;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import poll.Election;
import poll.GeneralPollImpl;
import poll.Poll;
import visitor.PercentVisitor;
import visitor.PercentVisitorImpl;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

public class ElectionApp
{
    public static void main(String[] args)
    {
	// 创建BusinessVoting类
	GeneralPollImpl<Person> electionPoll=new Election();
	
	// 投票活动名称
	String name="Election";
	// 投票日期
	Calendar date=Calendar.getInstance();
	date.set(2019, 6, 1);
	// 遴选最大数量
	int quantity=4;
	
	// 选票类型
	VoteType voteType=new VoteType(2);
	
	// 设立投票基本信息
	electionPoll.setInfo(name, date, voteType, quantity);
	
	// 增加候选对象
	Person tpe1=new Person("Ben", 20);
	Person tpe2=new Person("Bob", 21);
	Person tpe3=new Person("Alice", 23);
	Person tpe4=new Person("Lucy", 19);
	Person tpe5=new Person("Daniel", 20);
	Person tpe6=new Person("Nancy", 18);
	// 设立候选人
	List<Person> candidateList=new ArrayList<>();
	candidateList.add(tpe1);
	candidateList.add(tpe2);
	candidateList.add(tpe3);
	candidateList.add(tpe4);
	candidateList.add(tpe5);
	candidateList.add(tpe6);
	electionPoll.addCandidates(candidateList);
	
	// 增加投票人
	Voter voter1=new Voter("A");
	Voter voter2=new Voter("B");
	Voter voter3=new Voter("C");
	Voter voter4=new Voter("D");
	Voter voter5=new Voter("E");
	// 设立投票者
	Map<Voter, Double> voterMap=new HashMap<>();
	voterMap.put(voter1, 1.0);
	voterMap.put(voter2, 1.0);
	voterMap.put(voter3, 1.0);
	voterMap.put(voter4, 1.0);
	voterMap.put(voter5, 1.0);
	electionPoll.addVoters(voterMap);
	
	// 设定选票
	VoteItem<Person> voteItem11=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem12=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> voteItem13=new VoteItem<>(tpe3, "Object");
	VoteItem<Person> voteItem14=new VoteItem<>(tpe4, "Waiver");
	VoteItem<Person> voteItem15=new VoteItem<>(tpe5, "Waiver");
	VoteItem<Person> voteItem16=new VoteItem<>(tpe6, "Support");
	Set<VoteItem<Person>> itemSet1=new HashSet<>();
	itemSet1.add(voteItem11);
	itemSet1.add(voteItem12);
	itemSet1.add(voteItem13);
	itemSet1.add(voteItem14);
	itemSet1.add(voteItem15);
	itemSet1.add(voteItem16);
	Vote<Person> vote1=new Vote<Person>(itemSet1, date);
	
	VoteItem<Person> voteItem21=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem22=new VoteItem<>(tpe2, "Object");
	VoteItem<Person> voteItem23=new VoteItem<>(tpe3, "Object");
	VoteItem<Person> voteItem24=new VoteItem<>(tpe4, "Support");
	VoteItem<Person> voteItem25=new VoteItem<>(tpe5, "Support");
	VoteItem<Person> voteItem26=new VoteItem<>(tpe6, "Support");
	Set<VoteItem<Person>> itemSet2=new HashSet<>();
	itemSet2.add(voteItem21);
	itemSet2.add(voteItem22);
	itemSet2.add(voteItem23);
	itemSet2.add(voteItem24);
	itemSet2.add(voteItem25);
	itemSet2.add(voteItem26);
	Vote<Person> vote2=new Vote<Person>(itemSet2, date);
	
	VoteItem<Person> voteItem31=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem32=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> voteItem33=new VoteItem<>(tpe3, "Support");
	VoteItem<Person> voteItem34=new VoteItem<>(tpe4, "Object");
	VoteItem<Person> voteItem35=new VoteItem<>(tpe5, "Object");
	VoteItem<Person> voteItem36=new VoteItem<>(tpe6, "Waiver");
	Set<VoteItem<Person>> itemSet3=new HashSet<>();
	itemSet3.add(voteItem31);
	itemSet3.add(voteItem32);
	itemSet3.add(voteItem33);
	itemSet3.add(voteItem34);
	itemSet3.add(voteItem35);
	itemSet3.add(voteItem36);
	Vote<Person> vote3=new Vote<Person>(itemSet3, date);
	
	VoteItem<Person> voteItem41=new VoteItem<>(tpe1, "Support");
	VoteItem<Person> voteItem42=new VoteItem<>(tpe2, "Support");
	VoteItem<Person> voteItem43=new VoteItem<>(tpe3, "Support");
	VoteItem<Person> voteItem44=new VoteItem<>(tpe4, "Support");
	VoteItem<Person> voteItem45=new VoteItem<>(tpe5, "Waiver");
	VoteItem<Person> voteItem46=new VoteItem<>(tpe6, "Support");
	Set<VoteItem<Person>> itemSet4=new HashSet<>();
	itemSet4.add(voteItem41);
	itemSet4.add(voteItem42);
	itemSet4.add(voteItem43);
	itemSet4.add(voteItem44);
	itemSet4.add(voteItem45);
	itemSet4.add(voteItem46);
	Vote<Person> vote4=new Vote<Person>(itemSet4, date);
	
	VoteItem<Person> voteItem51=new VoteItem<>(tpe5, "Support");
	VoteItem<Person> voteItem52=new VoteItem<>(tpe5, "Object");
	VoteItem<Person> voteItem54=new VoteItem<>(tpe5, "Support");
	Set<VoteItem<Person>> itemSet5=new HashSet<>();
	itemSet5.add(voteItem51);
	itemSet5.add(voteItem52);
	itemSet5.add(voteItem54);
	Vote<Person> vote5=new Vote<Person>(itemSet5, date);
	
	try
	{
	    electionPoll.addVote(vote1);
	    electionPoll.addVote(vote2);
	    electionPoll.addVote(vote3);
	    electionPoll.addVote(vote4);
	    electionPoll.addVote(vote5);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	StatisticsStrategy<Person> ss=new ElectionStatistics<>();
	electionPoll.statistics(ss);
	
	SelectionStrategy<Person> selectionStrategy=new ElectionSelection<>();
	electionPoll.selection(selectionStrategy);
	
	PercentVisitor<Person> percentVisitor=new PercentVisitorImpl<>();
	
	try
	{
	    System.out.println("合法选票的比例为："+electionPoll.accept(percentVisitor));
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	System.out.println(electionPoll.result());
    }
    
}

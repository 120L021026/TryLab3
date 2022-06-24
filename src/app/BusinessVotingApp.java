package app;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import auxiliary.Proposal;
import auxiliary.Voter;
import pattern.BusinessVotingSelection;
import pattern.BusinessVotingStatistics;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import poll.BusinessVoting;
import poll.GeneralPollImpl;
import visitor.PercentVisitor;
import visitor.PercentVisitorImpl;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

public class BusinessVotingApp
{
    public static void main(String[] args) throws Exception
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
	Proposal proposal=new Proposal("Proposal", date);
	List<Proposal> candidateList=new ArrayList<>();
	candidateList.add(proposal);
	businessVotingPoll.addCandidates(candidateList);
	
	// 增加投票人
	Voter voter1=new Voter("A");
	Voter voter2=new Voter("B");
	Voter voter3=new Voter("C");
	Voter voter4=new Voter("D");
	Voter voter5=new Voter("E");
	Map<Voter, Double> voterMap=new HashMap<>();
	voterMap.put(voter1, 0.11);
	voterMap.put(voter2, 0.14);
	voterMap.put(voter3, 0.25);
	voterMap.put(voter4, 0.36);
	voterMap.put(voter5, 0.14);
	businessVotingPoll.addVoters(voterMap);
	
	// 设定选票
	VoteItem<Proposal> voteItem1=new VoteItem<>(proposal, "Dislike");
	Set<VoteItem<Proposal>> itemSet1=new HashSet<>();
	itemSet1.add(voteItem1);
	Vote<Proposal> vote1=new Vote<Proposal>(itemSet1, date);
	RealNameVote<Proposal> realNameVote1=new RealNameVote<>(vote1, voter1);
	
	VoteItem<Proposal> voteItem2=new VoteItem<>(proposal, "Object");
	Set<VoteItem<Proposal>> itemSet2=new HashSet<>();
	itemSet2.add(voteItem2);
	Vote<Proposal> vote2=new Vote<Proposal>(itemSet2, date);
	RealNameVote<Proposal> realNameVote2=new RealNameVote<>(vote2, voter2);
	
	VoteItem<Proposal> voteItem3=new VoteItem<>(proposal, "Support");
	Set<VoteItem<Proposal>> itemSet3=new HashSet<>();
	itemSet3.add(voteItem3);
	Vote<Proposal> vote3=new Vote<Proposal>(itemSet3, date);
	RealNameVote<Proposal> realNameVote3=new RealNameVote<>(vote3, voter3);
	
	VoteItem<Proposal> voteItem4=new VoteItem<>(proposal, "Support");
	Set<VoteItem<Proposal>> itemSet4=new HashSet<>();
	itemSet4.add(voteItem4);
	Vote<Proposal> vote4=new Vote<Proposal>(itemSet4, date);
	RealNameVote<Proposal> realNameVote4=new RealNameVote<>(vote4, voter4);
	
	VoteItem<Proposal> voteItem5=new VoteItem<>(proposal, "Support");
	Set<VoteItem<Proposal>> itemSet5=new HashSet<>();
	itemSet5.add(voteItem5);
	Vote<Proposal> vote5=new Vote<Proposal>(itemSet5, date);
	RealNameVote<Proposal> realNameVote5=new RealNameVote<>(vote5, voter5);
	
	try
	{
	    businessVotingPoll.addVote(realNameVote1);
	    businessVotingPoll.addVote(realNameVote2);
	    businessVotingPoll.addVote(realNameVote3);
	    businessVotingPoll.addVote(realNameVote4);
	    businessVotingPoll.addVote(realNameVote5);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	StatisticsStrategy<Proposal> statisticsStrategy=new BusinessVotingStatistics<>();
	businessVotingPoll.statistics(statisticsStrategy);
	
	SelectionStrategy<Proposal> selectionStrategy=new BusinessVotingSelection<>();
	businessVotingPoll.selection(selectionStrategy);
	
	PercentVisitor<Proposal> percentVisitor=new PercentVisitorImpl<>();
	
	try
	{
	    System.out.println("合法选票的比例为："+businessVotingPoll.accept(percentVisitor));
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	System.out.println(businessVotingPoll.result());
    }
}

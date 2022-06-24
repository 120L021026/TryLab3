package change;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.junit.Test;
import auxiliary.Proposal;
import auxiliary.Voter;
import pattern.BusinessVotingSelection;
import pattern.BusinessVotingStatistics;
import pattern.ChangeBusinessVotingStatistics;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import poll.BusinessVoting;
import poll.GeneralPollImpl;
import vote.RealNameVote;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

public class ChangeBusinessVotingStatisticsTest
{
    /**
     * 测试同时表决多个提案
     */
    @Test
    public void testChangeBusinessVotingStatisticsTest()
    {
	// 创建BusinessVoting类
	GeneralPollImpl<Proposal> testBusinessVotingPoll=new BusinessVoting();
	
	// 投票活动名称
	String name="BusinessVoting";
	// 投票日期
	Calendar date=Calendar.getInstance();
	date.set(2019, 6, 1);
	// 遴选最大数量
	int quantity=4;
	
	// 选票类型
	VoteType voteType=new VoteType(1);
	
	// 设立投票基本信息
	testBusinessVotingPoll.setInfo(name, date, voteType, quantity);
	
	// 增加候选对象
	Proposal proposal1=new Proposal("Proposal1", date);
	Proposal proposal2=new Proposal("Proposal2", date);
	Proposal proposal3=new Proposal("Proposal3", date);
	Proposal proposal4=new Proposal("Proposal4", date);
	List<Proposal> candidateList=new ArrayList<>();
	candidateList.add(proposal1);
	candidateList.add(proposal2);
	candidateList.add(proposal3);
	candidateList.add(proposal4);
	testBusinessVotingPoll.addCandidates(candidateList);
	
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
	testBusinessVotingPoll.addVoters(voterMap);
	
	// 设定选票
	VoteItem<Proposal> voteItem11=new VoteItem<>(proposal1, "Dislike");
	VoteItem<Proposal> voteItem13=new VoteItem<>(proposal3, "Support");
	VoteItem<Proposal> voteItem14=new VoteItem<>(proposal4, "Support");
	Set<VoteItem<Proposal>> itemSet1=new HashSet<>();
	itemSet1.add(voteItem11);
	itemSet1.add(voteItem13);
	itemSet1.add(voteItem14);
	Vote<Proposal> vote1=new Vote<Proposal>(itemSet1, date);
	RealNameVote<Proposal> realNameVote1=new RealNameVote<>(vote1, voter1);
	
	VoteItem<Proposal> voteItem21=new VoteItem<>(proposal1, "Object");
	VoteItem<Proposal> voteItem22=new VoteItem<>(proposal2, "Support");
	VoteItem<Proposal> voteItem23=new VoteItem<>(proposal3, "Waiver");
	VoteItem<Proposal> voteItem24=new VoteItem<>(proposal4, "Support");
	Set<VoteItem<Proposal>> itemSet2=new HashSet<>();
	itemSet2.add(voteItem21);
	itemSet2.add(voteItem22);
	itemSet2.add(voteItem23);
	itemSet2.add(voteItem24);
	Vote<Proposal> vote2=new Vote<Proposal>(itemSet2, date);
	RealNameVote<Proposal> realNameVote2=new RealNameVote<>(vote2, voter2);
	
	VoteItem<Proposal> voteItem31=new VoteItem<>(proposal1, "Support");
	VoteItem<Proposal> voteItem32=new VoteItem<>(proposal2, "Support");
	VoteItem<Proposal> voteItem33=new VoteItem<>(proposal3, "Support");
	VoteItem<Proposal> voteItem34=new VoteItem<>(proposal4, "Support");
	Set<VoteItem<Proposal>> itemSet3=new HashSet<>();
	itemSet3.add(voteItem31);
	itemSet3.add(voteItem32);
	itemSet3.add(voteItem33);
	itemSet3.add(voteItem34);
	Vote<Proposal> vote3=new Vote<Proposal>(itemSet3, date);
	RealNameVote<Proposal> realNameVote3=new RealNameVote<>(vote3, voter3);
	
	VoteItem<Proposal> voteItem41=new VoteItem<>(proposal1, "Support");
	VoteItem<Proposal> voteItem42=new VoteItem<>(proposal2, "Object");
	VoteItem<Proposal> voteItem43=new VoteItem<>(proposal3, "Support");
	VoteItem<Proposal> voteItem44=new VoteItem<>(proposal4, "Support");
	Set<VoteItem<Proposal>> itemSet4=new HashSet<>();
	itemSet4.add(voteItem41);
	itemSet4.add(voteItem42);
	itemSet4.add(voteItem43);
	itemSet4.add(voteItem44);
	Vote<Proposal> vote4=new Vote<Proposal>(itemSet4, date);
	RealNameVote<Proposal> realNameVote4=new RealNameVote<>(vote4, voter4);
	
	VoteItem<Proposal> voteItem51=new VoteItem<>(proposal1, "Support");
	VoteItem<Proposal> voteItem52=new VoteItem<>(proposal2, "Support");
	VoteItem<Proposal> voteItem53=new VoteItem<>(proposal3, "Support");
	VoteItem<Proposal> voteItem54=new VoteItem<>(proposal4, "Support");
	Set<VoteItem<Proposal>> itemSet5=new HashSet<>();
	itemSet5.add(voteItem51);
	itemSet5.add(voteItem52);
	itemSet5.add(voteItem53);
	itemSet5.add(voteItem54);
	Vote<Proposal> vote5=new Vote<Proposal>(itemSet5, date);
	RealNameVote<Proposal> realNameVote5=new RealNameVote<>(vote5, voter5);
	
	try
	{
	    testBusinessVotingPoll.addVote(realNameVote1);
	    testBusinessVotingPoll.addVote(realNameVote2);
	    testBusinessVotingPoll.addVote(realNameVote3);
	    testBusinessVotingPoll.addVote(realNameVote4);
	    testBusinessVotingPoll.addVote(realNameVote5);
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
	
	StatisticsStrategy<Proposal> statisticsStrategy=new ChangeBusinessVotingStatistics<>();
	testBusinessVotingPoll.statistics(statisticsStrategy);
	
	Map<Proposal, Double> retMap1=new HashMap<>();
	retMap1.put(proposal1, 0.75);
	retMap1.put(proposal2, 0.53);
	retMap1.put(proposal3, 0.75);
	retMap1.put(proposal4, 0.89);
	
	// 测试statistics()方法
	assertEquals(retMap1, testBusinessVotingPoll.getStatistics());
	
	SelectionStrategy<Proposal> selectionStrategy=new BusinessVotingSelection<>();
	testBusinessVotingPoll.selection(selectionStrategy);
	
	Map<Proposal, Double> retMap2=new HashMap<>();
	retMap2.put(proposal1, 0.75);
	retMap2.put(proposal3, 0.75);
	retMap2.put(proposal4, 0.89);
	
	// 测试selection()方法
	assertEquals(retMap2, testBusinessVotingPoll.getResults());
    }
}

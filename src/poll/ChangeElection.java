package poll;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import auxiliary.Person;
import pattern.Comparison;
import pattern.SelectionStrategy;
import vote.Vote;
import vote.VoteItem;

public class ChangeElection extends Election implements Poll<Person>
{
    /**
     * 重写selection
     * 当出现歧义时，通过判断反对票的数量来将选出的人数尽量向quantity靠拢
     */
    @Override
    public void selection(SelectionStrategy<Person> ss)
    {
	try
	{
	    // 可以确切被选中的候选人
	    this.results=new HashMap<Person, Double>(ss.everyResults(statistics, quantity));
	    
	    // 通过反对票来选择剩余的不满足quantity个候选人
	    if(this.results.size()<quantity)
	    {
		// 获得按照支持票数量降序排列的列表
		Comparison<Person> compare1=new Comparison<>(statistics);
		List<Person> list1=compare1.compare();
		
		// 存储歧义开始出现且支持票数相等的候选人
		List<Person> list2=new ArrayList<>();
		
		// 出现歧义的候选人的支持票数量
		double limit=statistics.get(list1.get(this.results.size()));
		// 将支持票数量相等的人存储到列表中
		for(int i=this.results.size(); i<list1.size(); i++)
		{
		    if(statistics.get(list1.get(i))==limit)
		    {
			list2.add(list1.get(i));
		    }
		}
		
		// 获得全部候选人反对票的总分数，映射到的Value值应该全部小于0
		// 候选人与反对票的总分的映射
		Map<Person, Double> byObject=new HashMap<Person, Double>();
		double gradeObject=0.0;
		Person person;
		// 对合法选票开始计数反对票
		for(Vote<Person> forVote: statisticsVotes)
		{
		    Set<VoteItem<Person>> rev=new HashSet<>(forVote.getVoteItems());
		    for(VoteItem<Person> forVoteItems: rev)
		    {
			gradeObject=0.0;
			person=forVoteItems.getCandidate();
			if(!byObject.containsKey(person))
			    byObject.put(person, gradeObject);
			if(forVoteItems.getVoteValue()=="Object")
			{
			    gradeObject=byObject.get(person)-1;
			    byObject.put(person, gradeObject);
			}
		    }
		}
		
		Map<Person, Double> compareObject=new HashMap<>();
		for(Person forPerson: list2)
		{
		    compareObject.put(forPerson, byObject.get(forPerson));
		}
		
		// 获得按照反对票得分<0降序排列的列表
		Comparison<Person> compare3=new Comparison<>(compareObject);
		List<Person> list3=compare3.compare();
		
		// 缺少的数量
		int num=quantity-this.results.size();
		
		double brande=compareObject.get(list3.get(num));
		
		// 是否存在按照反对票排序之后得到的数据结构，依然存在歧义的可以选出的人数
		int flag=0;
		if(num<list3.size())
		{
		    if(compareObject.get(list3.get(num-1)).equals(compareObject.get(list3.get(num))))
		    {
			flag=1;
		    }
		}
		
		// 有歧义
		if(flag==1)
		{
		    for(Person forChangeElection: list3)
		    {
			// 只选出明确可以被选入的候选人，即最终得到的结果数量<quantity
			if(compareObject.get(forChangeElection)==brande)
			{
			    break;
			}
			this.results.put(forChangeElection, compareObject.get(forChangeElection));
		    }
		}
		// 无歧义
		else if(flag==0)
		{
		    for(int i=0; i<num; i++)
		    {
			this.results.put(list3.get(i), limit);
		    }
		}
	    }
	}
	catch (Exception e)
	{
	    e.printStackTrace();
	}
    }
}

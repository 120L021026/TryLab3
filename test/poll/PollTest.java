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
import auxiliary.Voter;
import pattern.SelectionStrategy;
import pattern.StatisticsStrategy;
import vote.Vote;
import vote.VoteItem;
import vote.VoteType;

class PollTest
{
    // test strategy
    // 以聚餐点菜为例，构造DinnerOrder类
    // 分别对每个方法进行测试，其中addVote()方法、statistics()方法、selection()方法交由具体实现类实现测试
    // 对上述三个方法的测试，具体见BusinessVotingTest、ElectionTest、DinnerOrderTest文件
    
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
    public void testSetInfo()
    {
	// 创建聚餐点菜投票活动
	GeneralPollImpl<Dish> testPoll=new DinnerOrder();
	
	date.set(2019, 6, 1);
	
	// 设立基本信息
	testPoll.setInfo(name, date, voteType, quantity);
	
	assertEquals(name, testPoll.getName());
	assertEquals(date, testPoll.getDate());
	assertEquals(voteType, testPoll.getVoteType());
	assertEquals(quantity, testPoll.getQuantity());
    }
    
    @Test
    public void testAddCandidates()
    {
	// 创建聚餐点菜投票活动
	GeneralPollImpl<Dish> testPoll=new DinnerOrder();
	
	date.set(2019, 6, 1);
	
	// 设立基本信息
	testPoll.setInfo(name, date, voteType, quantity);
	
	// 设立候选对象
	List<Dish> testList=new ArrayList<>();
	testList.add(dish1);
	testList.add(dish2);
	testList.add(dish3);
	testList.add(dish4);
	
	testPoll.addCandidates(testList);
	
	assertEquals(testList, testPoll.getCandidates());
    }
    
    @Test
    public void testAddVoters()
    {
	// 创建DinnerOrder类
	GeneralPollImpl<Dish> testPoll=new DinnerOrder();
	
	date.set(2019, 6, 1);
	
	testPoll.setInfo(name, date, voteType, quantity);
	
	// 设立候选对象
	List<Dish> testList=new ArrayList<>();
	testList.add(dish1);
	testList.add(dish2);
	testList.add(dish3);
	testList.add(dish4);
	
	testPoll.addCandidates(testList);
	
	// 设立投票人
	Map<Voter, Double> testMap=new HashMap<>();
	testMap.put(voter1, 3.0);
	testMap.put(voter2, 2.0);
	testMap.put(voter3, 1.0);
	
	Map<Voter, Double> retMap=new HashMap<>(testMap);
	
	testPoll.addVoters(testMap);
	assertEquals(retMap, testPoll.getVoters());
    }
    
    @Test
    public void testAddVote()
    {
	// 交由三个具体实现类实现
	assert true;
    }
    
    @Test
    public void testStatistics()
    {
	// 交由三个具体实现类实现
	assert true;
    }
    
    @Test
    public void testSelection()
    {
	// 交由三个具体实现类实现
	assert true;
    }
    
    @Test
    public void testResult()
    {
	// 创建DinnerOrder类
	GeneralPollImpl<Dish> testPoll=new DinnerOrder();
	
	date.set(2019, 6, 1);
	
	testPoll.setInfo(name, date, voteType, quantity);
	
	// 设立候选对象
	List<Dish> testList=new ArrayList<>();
	testList.add(dish1);
	testList.add(dish2);
	testList.add(dish3);
	testList.add(dish4);
	
	testPoll.addCandidates(testList);
	
	// 设立投票人
	Map<Voter, Double> testMap=new HashMap<>();
	testMap.put(voter1, 3.0);
	testMap.put(voter2, 2.0);
	testMap.put(voter3, 1.0);
	
	testPoll.addVoters(testMap);
    }
}

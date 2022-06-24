package vote;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;
import org.junit.jupiter.api.Test;
import auxiliary.*;

class VoteItemTest
{
    @Test
    public void testVoteItem()
    {
	//代表选举
	Person tpe=new Person("Ben", 20);
	VoteItem<Person> tvpe=new VoteItem<Person>(tpe, "Support");
	assertEquals("Support", tvpe.getVoteValue());
	assertEquals(tpe, tvpe.getCandidate());
	
	//商业表决
	Calendar tc=Calendar.getInstance();
	tc.set(2019, 6, 1);
	Proposal tpr=new Proposal("First", tc);
	VoteItem<Proposal> tvpr=new VoteItem<Proposal>(tpr, "Support");
	assertEquals("Support", tvpr.getVoteValue());
	assertEquals(tpr, tvpr.getCandidate());
	
	//聚餐点菜
	Dish tdi=new Dish("rice", 1);
	VoteItem<Dish> tvdi=new VoteItem<Dish>(tdi, "Like");
	assertEquals("Like", tvdi.getVoteValue());
	assertEquals(tdi, tvdi.getCandidate());
    }
    
    @Test
    public void testGetVoteValue()
    {
	//代表选举
	Person tpe=new Person("Ben", 20);
	VoteItem<Person> tvpe=new VoteItem<Person>(tpe, "Support");
	assertEquals("Support", tvpe.getVoteValue());
	
	//商业表决
	Calendar tc=Calendar.getInstance();
	tc.set(2019, 6, 1);
	Proposal tpr=new Proposal("First", tc);
	VoteItem<Proposal> tvpr=new VoteItem<Proposal>(tpr, "Support");
	assertEquals("Support", tvpr.getVoteValue());
	
	//聚餐点菜
	Dish tdi=new Dish("rice", 1);
	VoteItem<Dish> tvdi=new VoteItem<Dish>(tdi, "Like");
	assertEquals("Like", tvdi.getVoteValue());
    }
    
    @Test
    public void testGetCandidate()
    {
	//代表选举
	Person tpe=new Person("Ben", 20);
	VoteItem<Person> tvpe=new VoteItem<Person>(tpe, "Support");
	assertEquals(tpe, tvpe.getCandidate());
	
	//商业表决
	Calendar tc=Calendar.getInstance();
	tc.set(2019, 6, 1);
	Proposal tpr=new Proposal("First", tc);
	VoteItem<Proposal> tvpr=new VoteItem<Proposal>(tpr, "Support");
	assertEquals(tpr, tvpr.getCandidate());
	
	//聚餐点菜
	Dish tdi=new Dish("rice", 1);
	VoteItem<Dish> tvdi=new VoteItem<Dish>(tdi, "Like");
	assertEquals(tdi, tvdi.getCandidate());
    }
}

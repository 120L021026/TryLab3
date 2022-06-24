package vote;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import auxiliary.*;

class VoteTest
{
    // test strategy
    // 以投票选举为例，实现Election即VoteType(2)的测试
    // Vote类中没有mutator方法，因此采用creator方法来测试observer方法
    
    @Test
    public void testVote()
    {
	Calendar tc=Calendar.getInstance();
	tc.set(2019, 6, 1);
	Person tpe1=new Person("Ben", 20);
	Person tpe2=new Person("Bob", 21);
	Person tpe3=new Person("Alice", 23);
	Person tpe4=new Person("Lucy", 19);
	VoteItem<Person> tvpe1=new VoteItem<Person>(tpe1, "Support");
	VoteItem<Person> tvpe2=new VoteItem<Person>(tpe2, "Object");
	VoteItem<Person> tvpe3=new VoteItem<Person>(tpe3, "Support");
	VoteItem<Person> tvpe4=new VoteItem<Person>(tpe4, "Waiver");
	Set<VoteItem<Person>> tspe=new HashSet<>();
	tspe.add(tvpe1);
	tspe.add(tvpe2);
	tspe.add(tvpe3);
	tspe.add(tvpe4);
	Vote<Person> tv=new Vote<Person>(tspe, tc);
	Set<VoteItem<Person>> rspe=tv.getVoteItems();
	assertTrue(rspe.contains(tvpe4));
	assertEquals(tspe, rspe);
    }
    
    @Test
    public void testGetVoteItems()
    {
	Calendar tc=Calendar.getInstance();
	tc.set(2019, 6, 1);
	Person tpe1=new Person("Ben", 20);
	Person tpe2=new Person("Bob", 21);
	Person tpe3=new Person("Alice", 23);
	Person tpe4=new Person("Lucy", 19);
	VoteItem<Person> tvpe1=new VoteItem<Person>(tpe1, "Support");
	VoteItem<Person> tvpe2=new VoteItem<Person>(tpe2, "Object");
	VoteItem<Person> tvpe3=new VoteItem<Person>(tpe3, "Support");
	VoteItem<Person> tvpe4=new VoteItem<Person>(tpe4, "Waiver");
	Set<VoteItem<Person>> tspe=new HashSet<>();
	tspe.add(tvpe1);
	tspe.add(tvpe2);
	tspe.add(tvpe3);
	tspe.add(tvpe4);
	Vote<Person> tv=new Vote<Person>(tspe, tc);
	Set<VoteItem<Person>> rspe=tv.getVoteItems();
	assertEquals(tspe, rspe);
    }
    
    @Test
    public void testCandidateIncluded()
    {
	Calendar tc=Calendar.getInstance();
	tc.set(2019, 6, 1);
	Person tpe1=new Person("Ben", 20);
	Person tpe2=new Person("Bob", 21);
	Person tpe3=new Person("Alice", 23);
	Person tpe4=new Person("Lucy", 19);
	VoteItem<Person> tvpe1=new VoteItem<Person>(tpe1, "Support");
	VoteItem<Person> tvpe2=new VoteItem<Person>(tpe2, "Object");
	VoteItem<Person> tvpe3=new VoteItem<Person>(tpe3, "Support");
	VoteItem<Person> tvpe4=new VoteItem<Person>(tpe4, "Waiver");
	Set<VoteItem<Person>> tspe=new HashSet<>();
	tspe.add(tvpe1);
	tspe.add(tvpe2);
	tspe.add(tvpe3);
	tspe.add(tvpe4);
	Vote<Person> tv=new Vote<Person>(tspe, tc);
	assertTrue(tv.candidateIncluded(tpe4));
    }
}

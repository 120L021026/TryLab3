package vote;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class VoteTypeTest
{
    // test strategy
    // 等价类划分为：全部正确的、含有空字符的、含有错误的
    
    @Test
    public void testVoteType1()
    {
	// 商业表决
	VoteType tv=new VoteType(1);
	Map<String, Integer> to=new HashMap<>();
	to.put("Support", 1); // 支持=1
	to.put("Object", -1); // 反对=-1
	to.put("Waiver", 0); // 弃权=0
	assertEquals(1, tv.getScoreByOption("Support"));
	assertEquals(-2, tv.getScoreByOption("Like"));
	
	// 代表选举
	tv=new VoteType(2);
	to=new HashMap<>();
	to.put("Support", 1); // 支持=1
	to.put("Object", -1); // 反对=-1
	to.put("Waiver", 0); // 弃权=0
	assertEquals(1, tv.getScoreByOption("Support"));
	assertEquals(-2, tv.getScoreByOption("Disike"));
	
	// 聚餐点菜
	tv=new VoteType(3);
	to=new HashMap<>();
	to.put("Like", 2); // 喜欢
	to.put("Dislike", 0); // 不喜欢
	to.put("Irrelevant", 1); // 不喜欢
	assertEquals(2, tv.getScoreByOption("Like"));
	assertEquals(-2, tv.getScoreByOption("Support"));
	
	// 错误输入
	tv=new VoteType(4);
    }
    
    @Test
    public void testVoteType2()
    {
	// 商业表决和代表选举
	String in1="\"Support\"(1)|\"Object\"(-1)|\"Waiver\"(0)";
	VoteType tv=new VoteType(in1);
	assertEquals(1, tv.getScoreByOption("Support"));
	assertEquals(-2, tv.getScoreByOption("Like"));
	
	// 聚餐点菜
	String in2="\"Like\"(2)|\"Dislike\"(0)|\"Irrelevant\"(1)";
	tv=new VoteType(in2);
	assertEquals(2, tv.getScoreByOption("Like"));
	assertEquals(-2, tv.getScoreByOption("Support"));
	
	// 错误输入
	String in3="\"Support\"(2)|\"Dislike\"(0)|\"Irrelevant\"(1)";
	tv=new VoteType(in3);
    }
    
    @Test
    public void testCheckLegality()
    {
	VoteType tv=new VoteType(1);
	assertTrue(tv.checkLegality("Support"));
	assertFalse(tv.checkLegality("Like"));
	assertFalse(tv.checkLegality(null));
	
	tv=new VoteType(2);
	assertTrue(tv.checkLegality("Object"));
	assertFalse(tv.checkLegality("DisLike"));
	assertFalse(tv.checkLegality(null));
	
	tv=new VoteType(3);
	assertTrue(tv.checkLegality("Like"));
	assertFalse(tv.checkLegality("Object"));
	assertFalse(tv.checkLegality(null));
    }
    
    @Test
    public void testGetScoreByOption()
    {
	VoteType tv=new VoteType(1);
	assertEquals(1, tv.getScoreByOption("Support"));
	assertEquals(-1, tv.getScoreByOption("Object"));
	assertEquals(-2, tv.getScoreByOption("Like"));
	assertEquals(-2, tv.getScoreByOption(null));
	
	tv=new VoteType(2);
	assertEquals(1, tv.getScoreByOption("Support"));
	assertEquals(-1, tv.getScoreByOption("Object"));
	assertEquals(-2, tv.getScoreByOption("Dislike"));
	assertEquals(-2, tv.getScoreByOption(null));
	
	tv=new VoteType(3);
	assertEquals(2, tv.getScoreByOption("Like"));
	assertEquals(0, tv.getScoreByOption("Dislike"));
	assertEquals(-2, tv.getScoreByOption("Object"));
	assertEquals(-2, tv.getScoreByOption(null));
    }
}

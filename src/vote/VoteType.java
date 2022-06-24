package vote;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// immutable
public class VoteType
{
    // key为选项名、value为选项名对应的分数
    private Map<String, Integer> options=new HashMap<>();
    
    // Rep Invariants
    // 不可以添加选项，映射确定以后不可以新增或更改
    // Abstract Function
    // 记录某种计票方式选项名和对应分数的映射
    // Safety from Rep Exposure
    // 防御式拷贝
    private void checkRep()
    {
	assert true;
    }
    
    /**
     * 创建一个投票类型对象
     * 
     * @param cho
     *                构造选项的类型数字，只能为1、2、3
     */
    public VoteType(int cho)
    {
	// 商业表决和代表选举
	if(cho==1 || cho==2)
	{
	    // Support, Object, Waiver：对应于1、−1、0
	    options.put("Support", 1); // 支持=1
	    options.put("Object", -1); // 反对=-1
	    options.put("Waiver", 0); // 弃权=0
	}
	// 聚餐点菜
	else if(cho==3)
	{
	    // Like, Dislike, Irrelevant：对应于2、0、1
	    options.put("Like", 2); // 喜欢
	    options.put("Dislike", 0); // 不喜欢
	    options.put("Irrelevant", 1); // 不喜欢
	}
	else
	{
	    System.out.println("输入错误");
	    //System.exit(-1);
	}
    }
    
    /**
     * 根据满足特定语法规则的字符串，创建一个投票类型对象
     * 
     * @param regex
     *                  遵循特定语法的、包含投票类型信息的字符串
     */
    public VoteType(String regex)
    {
	//判断输入是否正确
	int flag=0;
	
	// 商业表决和代表选举
	String reg11="\"(Support)\"\\((1)\\)\\|\"(Object)\"\\((-1)\\)\\|\"(Waiver)\"\\((0)\\)";
	String reg12="\"(Support)\"\\((1)\\)\\|\"(Waiver)\"\\((0)\\)|\"(Object)\\\"\\((-1)\\)";
	String reg13="\"(Object)\"\\((-1)\\)\\|\"(Support)\"\\((1)\\)\\|\"(Waiver)\"\\((0)\\)";
	String reg14="\"(Object)\"\\((-1)\\)\\|\"(Waiver)\"\\((0)\\)\\|\"(Support)\"\\((1)\\)";
	String reg15="\"(Waiver)\"\\((0)\\)\\|\"(Object)\"\\((-1)\\)\\|\"(Support)\"\\((1)\\)";
	String reg16="\"(Waiver)\"\\((0)\\)\\|\"(Support)\"\\((1)\\)\\|\"(Object)\"\\((-1)\\)";
	Pattern pat11=Pattern.compile(reg11);
	Pattern pat12=Pattern.compile(reg12);
	Pattern pat13=Pattern.compile(reg13);
	Pattern pat14=Pattern.compile(reg14);
	Pattern pat15=Pattern.compile(reg15);
	Pattern pat16=Pattern.compile(reg16);
	Matcher mat11=pat11.matcher(regex);
	Matcher mat12=pat12.matcher(regex);
	Matcher mat13=pat13.matcher(regex);
	Matcher mat14=pat14.matcher(regex);
	Matcher mat15=pat15.matcher(regex);
	Matcher mat16=pat16.matcher(regex);
	if(mat11.matches() || mat12.matches() || mat13.matches() || mat14.matches() || mat15.matches() || mat16.matches())
	{
	    flag=1;
	    options.put("Support", 1); // 支持=1
	    options.put("Object", -1); // 反对=-1
	    options.put("Waiver", 0); // 弃权=0
	}
	
	// 聚餐点菜
	String reg21="\"(Like)\"\\((2)\\)\\|\"(Dislike)\"\\((0)\\)\\|\"(Irrelevant)\"\\((1)\\)";
	String reg22="\"(Like)\"\\((2)\\)\\|\"(Irrelevant)\"\\((1)\\)\\|\"(Dislike)\"\\((0)\\)";
	String reg23="\"(Dislike)\"\\((0)\\)\\|\"(Like)\"\\((2)\\)\\|\"(Irrelevant)\"\\((1)\\)";
	String reg24="\"(Dislike)\"\\((0)\\)\\|\"(Irrelevant)\"\\((1)\\)\\|\"(Like)\"\\((2)\\)";
	String reg25="\"(Irrelevant)\"\\((1)\\)\\|\"(Dislike)\"\\((0)\\)\\|\"(Like)\"\\((2)\\)";
	String reg26="\"(Irrelevant)\"\\((1)\\)\\|\"(Like)\"\\((2)\\)\\|\"(Dislike)\"\\((1)\\)";
	Pattern pat21=Pattern.compile(reg21);
	Pattern pat22=Pattern.compile(reg22);
	Pattern pat23=Pattern.compile(reg23);
	Pattern pat24=Pattern.compile(reg24);
	Pattern pat25=Pattern.compile(reg25);
	Pattern pat26=Pattern.compile(reg26);
	Matcher mat21=pat21.matcher(regex);
	Matcher mat22=pat22.matcher(regex);
	Matcher mat23=pat23.matcher(regex);
	Matcher mat24=pat24.matcher(regex);
	Matcher mat25=pat25.matcher(regex);
	Matcher mat26=pat26.matcher(regex);
	if(mat21.matches() || mat22.matches() || mat23.matches() || mat24.matches() || mat25.matches() || mat26.matches())
	{
	    flag=1;
	    options.put("Like", 2); // 喜欢
	    options.put("Dislike", 0); // 不喜欢
	    options.put("Irrelevant", 1); // 不喜欢
	}
	
	if(flag==0)
	{
	    System.out.println("输入错误");
	    //System.exit(-1);
	}
    }
    
    /**
     * 判断一个投票选项是否合法（用于Poll中对选票的合法性检查）
     * 
     * @param option
     *                   一个投票选项
     * 
     * @return 合法则true，否则false
     */
    public boolean checkLegality(String option)
    {
	if(options.containsKey(option))
	    return true;
	return false;
    }
    
    /**
     * 根据一个投票选项，得到其对应的分数
     * 
     * @param option
     *                   一个投票项取值
     * 
     * @return 该选项所对应的分数
     */
    public int getScoreByOption(String option)
    {
	int value=-2;
	if(options.containsKey(option))
	{
	    value=options.get(option);
	}
	return value;
    }
    
    @Override
    public int hashCode()
    {
	int result=options.hashCode();
	return result;
    }
    
    @Override
    public boolean equals(Object obj)
    {
	if(this==obj)
	    return true;
	if(obj==null)
	    return false;
	if(getClass()!=obj.getClass())
	    return false;
	VoteType other=(VoteType) obj;
	if(!options.equals(other.options))
	    return false;
	return true;
    }
}

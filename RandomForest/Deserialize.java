import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.File;
import java.io.FileOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.String;

public class Deserialize
{
	public static int countOccurences(String s,char c)
	{
		int answer=0;
		for(int i=0;i<s.length();i++)
		{
			if(s.charAt(i)==c)
				answer++;
		}
		return answer;
	}
	public static void main(String[] args)
	{
		try
		{
			BufferedReader br = new BufferedReader(new FileReader("tree10.txt"));
			String Line;
			String lineInfo,valueInfo;
			String attribute="";
			String value="";
			int prevPipe=-1;
			int currPipe=0;
			while((Line=br.readLine())!=null)
			{
				currPipe = countOccurences(Line,'|');
				StringTokenizer st = new StringTokenizer(Line,"|");
				while(st.hasMoreElements())
				{
					lineInfo = st.nextElement().toString();
					if(lineInfo.charAt(0)==' ')
						lineInfo=lineInfo.substring(1,lineInfo.length());
					if(lineInfo.charAt(0)==' ')
						continue;
					if(lineInfo.charAt(0)=='0'||lineInfo.charAt(0)=='1')
					{
						attribute="Target";
						StringTokenizer st2 = new StringTokenizer(lineInfo," ");
						value=st2.nextElement().toString();
						
					}
					else
					{
						StringTokenizer st2 = new StringTokenizer(lineInfo,"(");
						attribute=st2.nextElement().toString();
						valueInfo = st2.nextElement().toString();
						StringTokenizer st3 = new StringTokenizer(valueInfo,")");
						value = st3.nextElement().toString();
					}
					while(currPipe<=prevPipe)
					{
						for(int i=1;i<=prevPipe;i++)
							System.out.print("\t");
						System.out.println("</node>");
						prevPipe--;
					}
					for(int i=1;i<=currPipe;i++)
						System.out.print("\t");
					System.out.println("<node attribute=\"" + attribute + "\" value=\"" + value+ "\">");
				}
				prevPipe=currPipe;
			}
			currPipe=0;
			while(currPipe<=prevPipe)
			{
				for(int i=1;i<=prevPipe;i++)
					System.out.print("\t");
				System.out.println("</node>");
				prevPipe--;
			}

		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

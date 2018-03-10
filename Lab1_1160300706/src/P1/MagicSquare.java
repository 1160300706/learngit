package P1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;

public class MagicSquare {
	public static void main(String[] args) throws NumberFormatException, IOException {
		MagicSquare K = new MagicSquare();//����һ�����󣬱��ڵ������еķ���
		boolean result;
		result = K.isLegalMagicSquare("src/P1/txt/1.txt");
		System.out.println("1  "+result);
		result = K.isLegalMagicSquare("src/P1/txt/2.txt");
		System.out.println("2  "+result);
		result = K.isLegalMagicSquare("src/P1/txt/3.txt");
		System.out.println("3  "+result);
		result = K.isLegalMagicSquare("src/P1/txt/4.txt");
		System.out.println("4  "+result);
		result = K.isLegalMagicSquare("src/P1/txt/5.txt");
		System.out.println("5  "+result);
		result = generateMagicSquare(7);
		if(result==true)
		{
		result = K.isLegalMagicSquare("src/P1/txt/6.txt");
		System.out.println("6  "+result);
		}
		
	}
	public boolean isLegalMagicSquare(String filename) throws NumberFormatException, IOException
	{
		FileInputStream fis = new FileInputStream(filename);//��ȡ�ļ�
		InputStreamReader isr = new InputStreamReader(fis);
		BufferedReader br = new BufferedReader(isr);
		String str ;
		String[] array;//�洢ÿһ�е�����
		int length = 0;
		int [][] Square = new int[1000][1000];  //�洢����ȡ����������
		try{
		int flag=0;
		while((str = br.readLine())!=null)
		{
			array = str.split("\t");
			length = array.length;
			for(int i=0;i<length;i++)
			{
				Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");  //ͨ��������ʽ���ж��Ƿ�Ϊ����
				if(pattern.matcher(array[i]).matches()==false)
					return false;
				else 
				{
					if(Integer.parseInt(array[i])>0)  //�ж��Ƿ�Ϊ����
						Square[flag][i]=Integer.parseInt(array[i]);
					else
						return false;
				}		
			}
		flag++;
		}
		int[] sum = new int[2*length];  //�洢ÿ��ÿ�еĺ�
		int temp=0,temp1=0;
		int dia=0,dia1=0;
		Arrays.fill(sum, 0);
		int count = 0;
		for(int i=0;i<length;i++)
		{
			for(int j=0;j<length;j++)
			{
				temp += Square[i][j];  //ÿ�еĺ�
				temp1 += Square[j][i]; //ÿ�еĺ�
				if(i==j)
					dia+=Square[i][j];  //�Խ��ߵĺ�
				if((i+j)==length-1)
					dia1+=Square[i][j];
			}
			sum[count] = temp;
			count++;
			sum[count] = temp1;	
			count++;
			temp=temp1=0;
		}
		if(dia!=dia1)   //�ж��Ƿ����
				return false;
		else
		{
			for(int i=0;i<2*length;i++)
			{
				if(dia!=sum[i])
					return false;
			}
		}
	}finally {
			 try {
				    br.close();
				    isr.close();
				    fis.close();
				   } catch (IOException e) {
				    e.printStackTrace();
				   }
				  }
		return true;
     }
	public static boolean generateMagicSquare(int n) throws IOException
	{
		if(n<=0||n%2==0)
		{
			System.out.println("The number of n is illegal.");
			return false;
		}
		else
		{
			int magic[][] = new int[n][n];
			int row = 0,col = n / 2,i,j,square = n * n;
				for(i=1;i<=square;i++)
				{
					magic[row][col] = i;
					if(i % n == 0)
						row++;
					else{
						if(row == 0)
							row = n-1;
						else
							row--;
						if(col == (n - 1))
							col = 0;
						else
							col++;
					}
				}
				FileWriter fw = new FileWriter("src/P1/txt/6.txt");
				BufferedWriter bw = new BufferedWriter(fw);
				for(i=0;i<n;i++)
				{
					for(j=0;j<n;j++)
					{
						bw.write(magic[i][j] + "\t");
					}
					bw.newLine();
				}
				bw.flush();
				bw.close();
				return true;
		}
	}
		
}
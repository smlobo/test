import java.io.*;
class ShellSort
{
void ShellSort(int array[], int number_of_elements)
{
        int iter, jter, increment, temp,i,j;
        for(increment = number_of_elements/2;increment > 0; increment /= 2)
        {
                for(i = increment; i<number_of_elements; i++)
                {
                        temp = array[i];
                        for(j = i; j >= increment ;j-=increment)
                        {
                                if(temp < array[j-increment])
                                {
                                        array[j] = array[j-increment];
                                }
                                else
                                {
                                        break;
                                }
                        }
                        array[j] = temp;
                }
        }
}
int main()throws IOException
{
    BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        int number_of_elements;
        System.out.println("Enter the number of elements");
        number_of_elements=Integer.parseInt(in.readLine());
        int array[]=new int[number_of_elements];
        int iter;
         System.out.println("Enter the elements one by one");
        for(iter = 0;iter < number_of_elements;iter++)
        {
                array[iter]=Integer.parseInt(in.readLine());;
        }
        /* Calling this functions sorts the array */
        ShellSort(array,number_of_elements);
        for(iter = 0;iter < number_of_elements;iter++)
        {
               System.out.print(array[iter]+"\t");
        }
        System.out.print("\n");
        return 0;
}
}
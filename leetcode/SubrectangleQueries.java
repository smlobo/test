import java.util.*;

/*
Implement the class SubrectangleQueries which receives a rows x cols rectangle 
as a matrix of integers in the constructor and supports two methods:

1. updateSubrectangle(int row1, int col1, int row2, int col2, int newValue)

Updates all values with newValue in the subrectangle whose upper left coordinate 
is (row1,col1) and bottom right coordinate is (row2,col2).

2. getValue(int row, int col)

Returns the current value of the coordinate (row,col) from the rectangle.
*/

class SubrectangleQueries {
    private int[][] rectangle;

    public SubrectangleQueries(int[][] rectangle) {
        this.rectangle = rectangle;
    }
    
    public void updateSubrectangle(int row1, int col1, int row2, int col2, 
        int newValue) {
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                rectangle[i][j] = newValue;
            }
        }        
    }
    
    public int getValue(int row, int col) {
        return rectangle[row][col];
    }

    public String toString() {
        StringBuilder retString = new StringBuilder(rectangle.length * 
            (rectangle[0].length+1));
        for (int i = 0; i < rectangle.length; i++) {
            retString.append("[");
            for (int j = 0; j < rectangle[i].length; j++) {
                retString.append(rectangle[i][j]);
                retString.append(",");
            }
            retString.append("]\n");
        }
        return retString.toString();
    }

    public static void main(String[] args) {

        // Test 1
        int[][] test1Init = new int[][] {
            {1,2,1},
            {4,3,4},
            {3,2,1},
            {1,1,1}
        };
        int[][] t1GetValues = new int[][] {
            {0,2},
            {0,2},
            {3,1},
            {3,1},
            {0,2}
        };
        int[][] t1UpdateSubrectangle = new int[][] {
            {0,0,3,2,5},
            {3,0,3,2,10}
        };
        SubrectangleQueries t1Obj = new SubrectangleQueries(test1Init);
        System.out.println("t1 = " + t1Obj);
        System.out.println("val: " + 
            t1Obj.getValue(t1GetValues[0][0], t1GetValues[0][1]));
        t1Obj.updateSubrectangle(t1UpdateSubrectangle[0][0],
            t1UpdateSubrectangle[0][1], t1UpdateSubrectangle[0][2],
            t1UpdateSubrectangle[0][3], t1UpdateSubrectangle[0][4]);
        System.out.println("u1 t1 = " + t1Obj);
        System.out.println("val: " + 
            t1Obj.getValue(t1GetValues[1][0], t1GetValues[1][1]));
        System.out.println("val: " + 
            t1Obj.getValue(t1GetValues[2][0], t1GetValues[2][1]));
        t1Obj.updateSubrectangle(t1UpdateSubrectangle[1][0],
            t1UpdateSubrectangle[1][1], t1UpdateSubrectangle[1][2],
            t1UpdateSubrectangle[1][3], t1UpdateSubrectangle[1][4]);
        System.out.println("u2 t1 = " + t1Obj);
        System.out.println("val: " + 
            t1Obj.getValue(t1GetValues[3][0], t1GetValues[3][1]));
        System.out.println("val: " + 
            t1Obj.getValue(t1GetValues[4][0], t1GetValues[4][1]));
    }
}

/**
 * Your SubrectangleQueries object will be instantiated and called as such:
 * SubrectangleQueries obj = new SubrectangleQueries(rectangle);
 * obj.updateSubrectangle(row1,col1,row2,col2,newValue);
 * int param_2 = obj.getValue(row,col);
 */
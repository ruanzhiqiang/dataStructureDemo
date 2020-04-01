package com.datastructure;

import java.io.*;

/**
 * @Description 二维数组转变为稀疏数组，
 * @Author ruanzhiqiang
 * @Date 2020/3/17 11:40
 * @ClassName SparseArray
 **/
public class SparseArray {
    public static void main(String[] args) throws IOException {
        int[][] intger = new int[11][12];
        intger[0][1] = 1;
        intger[3][2] = 2;
        intger[5][6] = 12;
        intger[4][9] = 132;
        System.out.println("原始的二维数组");
        for (int[] rows : intger) {
            for (int row :rows) {
                System.out.print("\t"+row);
            }
            System.out.println("");
        }

        //得到稀疏数组的大小
        int num = 0;
        for (int[] rows : intger) {
            for (int row :rows) {
                if(row != 0){
                    num++;
                }

            }
        }
        System.out.println("开始拼装稀疏数组");
        int[][] sparseArray = new int[num+1][3];
        sparseArray[0][0] = intger.length;
        sparseArray[0][1] = intger[0].length;
        sparseArray[0][2] = num;
        int count = 0;
        for (int i = 0; i < intger.length; i++) {
            for (int j = 0; j < intger[0].length; j++) {
               if(intger[i][j] != 0){
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = intger[i][j];
                }
            }
        }
        //打印稀疏数组
        System.out.println("打印稀疏数组");
        for (int[] rows : sparseArray) {
            for (int row :rows) {
                System.out.print("\t"+row);
            }
            System.out.println("");
        }
        //将稀疏数组存入磁盘中
        String path = "D://map.data";
        File inFile = new File(path);
        FileWriter fileWriter = new FileWriter(inFile);
        BufferedWriter out = null;
        try {
            if(!inFile.exists()){
                inFile.createNewFile();
            }
            out = new BufferedWriter(fileWriter);
            for (int i = 0; i < sparseArray.length; i++) {
                out.write(sparseArray[i][0]+" "+sparseArray[i][1]+" "+sparseArray[i][2]);
                out.newLine();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        //从磁盘中读取稀疏数组在回复到二维数组
        BufferedReader reader = new BufferedReader(new FileReader(inFile));

        String sparseString = "";
        while (reader.ready()){
            sparseString += reader.readLine()+",";
        }
        System.out.println(sparseString);
        //将字符串变为数组
        System.out.println("将稀疏Io数组恢复为二维数组");
        String[] sparseStringArray =  sparseString.split(",");
        String[] arraySize = sparseStringArray[0].split(" ");
        int[][] newArrayIo = new int[Integer.valueOf(arraySize[0])][Integer.valueOf(arraySize[1])];
        for (int i = 1; i < sparseStringArray.length; i++) {
            String[] arrayData = sparseStringArray[i].split(" ");
            newArrayIo[Integer.valueOf(arrayData[0])][Integer.valueOf(arrayData[1])] = Integer.valueOf(arrayData[2]);
        }

        System.out.println("打印由稀疏IO数组变为的二维数组");
        for (int[] rows : newArrayIo) {
            for (int row :rows) {
                System.out.print("\t"+row);
            }
            System.out.println("");
        }
        System.out.println("将稀疏数组恢复为二维数组");
        int[][] newArray = new int[sparseArray[0][0]][sparseArray[0][1]];


        for (int i = 1; i < sparseArray.length; i++) {
            newArray[sparseArray[i][0]][sparseArray[i][1]]=sparseArray[i][2];
        }
        System.out.println("打印由稀疏数组变为的二维数组");
        for (int[] rows : newArray) {
            for (int row :rows) {
                System.out.print("\t"+row);
            }
            System.out.println("");
        }
    }
}
